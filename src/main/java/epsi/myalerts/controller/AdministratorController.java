package epsi.myalerts.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsi.myalerts.model.Administrator;
import epsi.myalerts.repository.AdministratorRepository;

@RestController
@RequestMapping("api/administrators")
public class AdministratorController {

	@Autowired
	AdministratorRepository administratorRepository;

	@GetMapping("")
	public List<Administrator> getAllAdministrators() {
		return administratorRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Administrator> getAdministratorById(@PathVariable(value = "id") Integer id) {
		return administratorRepository.findById(id);
	}
	
	

}
