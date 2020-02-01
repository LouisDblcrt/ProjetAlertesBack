package epsi.myalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import epsi.myalerts.model.AlertOwner;
import epsi.myalerts.repository.AlertOwnerRepository;

/***
 * 
 * This class represent the REST API for the alert owner. You can
 * add/delete/modify informations on alerts owner. These informations are stored
 * in a database.
 */
@RestController
@RequestMapping("api/alertOwners")
public class AlertOwnerController {

	@Autowired
	AlertOwnerRepository alertOwnerRepository;

	/**
	 * This method is called when you do a GET on /api/alertOwners. It returns the
	 * list of alert owners
	 * 
	 * @return list of alert owners
	 */
	// @CrossOrigin(origins = "http://localhost:8100")
	@GetMapping("")
	public List<AlertOwner> getAllAlertOwner() {
		return alertOwnerRepository.findAll();
	}

	/**
	 * This method is called when you do a GET on /api/alertOwners/{id}. It returns
	 * informations on the alert owner
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public AlertOwner getAlertOwnerById(@PathVariable(name = "id") Integer id) {
		return alertOwnerRepository.findById(id).orElseThrow(NotFoundException::new);
	}

	/**
	 * This method is called when you do a POST on /api/alertOwners/ This method
	 * create an alert owner in the database.
	 * 
	 * @param alertOwner informations on the alert owner
	 * @return alert owner created
	 */
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public AlertOwner createAlertOwner(@RequestBody AlertOwner alertOwner) {
		return alertOwnerRepository.save(alertOwner);
	}

	@PutMapping("/{id}")
	public AlertOwner modifyAlertOwner(@RequestBody AlertOwner alertOwner, @PathVariable(name = "id") Integer id) {
		return alertOwnerRepository.findById(id).map(alertFind -> {
			alertFind.setId(id);
			alertFind.setDescription(alertOwner.getDescription());
			alertFind.setEnterprise(alertOwner.getEnterprise());
			alertFind.setPriorityMax(alertOwner.getPriorityMax());
			return alertOwnerRepository.save(alertFind);
		}).orElseGet(() -> {
			return alertOwnerRepository.save(alertOwner);
		});
	}
	@CrossOrigin
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAlertOwner(@PathVariable(name = "id") Integer id) {
		alertOwnerRepository.deleteById(id);
	}

	@GetMapping("/notSubs/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<AlertOwner> getAlertOwnersNotSub(@PathVariable(name = "id") Integer id) {
		return alertOwnerRepository.getAlertsOwnerWithoutSub(id);
	}

	@GetMapping("/subs/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<AlertOwner> getAlertOwnersSub(@PathVariable(name = "id") Integer id) {
		return alertOwnerRepository.getAlertsOwnerWithSub(id);
	}
}
