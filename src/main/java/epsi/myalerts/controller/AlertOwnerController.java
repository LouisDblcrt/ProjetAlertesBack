package epsi.myalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsi.myalerts.model.AlertOwner;
import epsi.myalerts.repository.AlertOwnerRepository;

@RestController
@RequestMapping("api/alertOwners")
public class AlertOwnerController {

	@Autowired
	AlertOwnerRepository alertOwnerRepository;
	
	@GetMapping("")
	public List<AlertOwner> getAllAlertOwner(){
		return alertOwnerRepository.findAll();
	}
}
