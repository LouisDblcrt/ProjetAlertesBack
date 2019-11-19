package epsi.myalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epsi.myalerts.exceptions.NotFoundException;
import epsi.myalerts.model.Administrator;
import epsi.myalerts.repository.AdministratorRepository;

/***
 * This class represent the REST API for the administrators.
 * 
 *
 */
@RestController
@RequestMapping("api/administrators")
public class AdministratorController {

	@Autowired
	AdministratorRepository administratorRepository;

	/**
	 * This method is called when you do a GET on /api/administrators.
	 * 
	 * @return List of administrators
	 */
	@GetMapping("")
	public List<Administrator> getAllAdministrators() {
		return administratorRepository.findAll();
	}

	/**
	 * This method is called when you do a GET on /api/administrators/{id} with
	 * administrator's ID
	 * 
	 * @param id : ID of the administrator
	 * @return informations of an administrator
	 */
	@GetMapping("/{id}")
	public Administrator getAdministratorById(@PathVariable(value = "id") Integer id) {
		return administratorRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Administrator createNewAdministrator(@RequestBody Administrator administrator) {
		return administratorRepository.save(administrator);
	}
	
	@PutMapping("")
	@ResponseStatus(HttpStatus.OK)
	public Administrator putAdministrator(@RequestBody Administrator administrator) {
		Administrator alreadyExist = administratorRepository.findById(administrator.getId()).orElseThrow(NotFoundException::new);
		alreadyExist.setLogin(administrator.getLogin());
		alreadyExist.setName(administrator.getName());
		alreadyExist.setPassword(administrator.getPassword());
		alreadyExist.setSurname(administrator.getSurname());
		return administratorRepository.save(alreadyExist);
	}
}
