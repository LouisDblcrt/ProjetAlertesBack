package epsi.myalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsi.myalerts.model.Administrator;
import epsi.myalerts.repository.AdministratorRepository;

@RestController
@RequestMapping("api/administrator")
public class AdministratorController {
	
	@Autowired
	AdministratorRepository administratorRepository;
	
	@GetMapping("")
	public List<Administrator> getAllAdministrators() {
		return administratorRepository.findAll();
	}

}
