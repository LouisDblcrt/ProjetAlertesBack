package epsi.myalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epsi.myalerts.exceptions.NotFoundException;
import epsi.myalerts.model.Alert;
import epsi.myalerts.repository.AlertRepository;

/***
 * This class represent the REST API for the alerts. You add/delete/modify informations on
 * alerts. These informations are stored in a database.
 */
@RestController
@RequestMapping("api/alerts")
public class AlertController {

	@Autowired
	AlertRepository alertRepository;
	/**
	 * This method is called when you do a GET on /api/alerts
	 * @return All the alerts 
	 */
	@GetMapping("")
	public List<Alert> getAllAlerts(){
		return alertRepository.findAll();
	}
	/**
	 * This method is called when you do a GET on /api/alerts/{id}.
	 * It returns informations of an alert according to the id in parameter
	 * @param id alert's id
	 * @return informations of this alert
	 */
	@GetMapping("/{id}")
	public Alert getAlertById(@PathVariable(name="id") Integer id ) {
		return alertRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Alert createNewAlert(@RequestBody Alert alert) {
		return alertRepository.save(alert);
	}
	
	@PutMapping("")
	public Alert updateAlert(@RequestBody Alert alert) {
		Alert alreadyExist = alertRepository.findById(alert.getId()).orElseThrow(NotFoundException::new);
		alreadyExist.setAlert_owner(alert.getAlert_owner());
		alreadyExist.setAlertDate(alert.getAlertDate());
		alreadyExist.setDescription(alert.getDescription());
		alreadyExist.setLieu(alert.getLieu());
		alreadyExist.setName(alert.getName());
		return alertRepository.save(alert);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAlert(@PathVariable(name="id") Integer id ) {
		alertRepository.deleteById(id);
	}
}
