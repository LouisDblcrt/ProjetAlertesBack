package epsi.myalerts.adapter.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epsi.myalerts.domain.entity.AlertOwner;
import epsi.myalerts.domain.exception.NotFoundException;
import epsi.myalerts.usecase.db.AlertOwnerRepository;

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

	@GetMapping("")
	@ResponseStatus(HttpStatus.OK)
	public List<AlertOwner> getAlertOwnersByUser(@RequestParam(name = "user") Integer id,
			@RequestParam(name = "search",required=false) String search, @RequestParam(name = "sub") boolean sub) {
		if (sub) {
			return this.alertOwnerRepository.getAlertsOwnerWithSub(id);
		} else {
			if (search==null) {
				return this.alertOwnerRepository.getAlertsOwnerWithoutSub(id);
			}
			System.out.println(this.alertOwnerRepository.getAlertsOwnerForSearch(id, search));
			return this.alertOwnerRepository.getAlertsOwnerForSearch(id, search);
		}

	}
}
