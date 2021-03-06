package epsi.myalerts.adapter.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epsi.myalerts.domain.entity.Alert;
import epsi.myalerts.domain.exception.NotFoundException;
import epsi.myalerts.usecase.db.AlertRepository;

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
	 * This method is called when you do a GET on /api/alerts/{id}.
	 * It returns informations of an alert according to the id in parameter
	 * @param id alert's id
	 * @return informations of this alert
	 */
	@GetMapping("/{id}")
	public Alert getAlertById(@PathVariable(name="id") Integer id ) {
		return alertRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	/**
	 * This method is called when you do a POST on /api/alerts.
	 * This method creates a new alert. 
	 * @param alert alert's informations
	 * @return alert created previously
	 */
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Alert createNewAlert(@RequestBody Alert alert) {
		System.out.println(alert.getAlert_date());
		return alertRepository.save(alert);
	}
	/**
	 * This method is called when you do a PUT on /api/alerts
	 * @param alert alert's informations
	 * @return alert modified 
	 */
	@PutMapping("/{id}")
	public Alert updateAlert(@RequestBody Alert alert,@PathVariable(name="id")Integer id) {
		return alertRepository.findById(id).map(alertFind ->{
			alertFind.setName(alert.getName());
			alertFind.setLieu(alert.getLieu());
			alertFind.setDescription(alert.getDescription());
			alertFind.setAlert_date(alert.getAlert_date());
			alertFind.setAlert_owner(alert.getAlert_owner());
			alertFind.setCriticite(alert.getCriticite());
			alertFind.setLat(alert.getLat());
			alertFind.setLng(alert.getLng());
			return alertRepository.save(alertFind);
		}).orElseGet(()->{
			return alertRepository.save(alert);
		});
		
	}
	/**
	 * This method is called when you do a DELETE on /api/alerts
	 * @param id alert's id who you want to be deleted 
	 */
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAlert(@PathVariable(name="id") Integer id ) {
		alertRepository.deleteById(id);
	}
	
	@GetMapping("")
	public List<Alert> getAlertsByUser(@RequestParam("user") Integer id ) {
		if(id==null) {
			return alertRepository.findAll();
		}
		return alertRepository.selectAlertFromUser(id);
	}
}
