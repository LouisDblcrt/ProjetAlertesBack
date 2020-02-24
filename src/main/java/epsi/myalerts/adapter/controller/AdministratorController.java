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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epsi.myalerts.domain.entity.Administrator;
import epsi.myalerts.domain.exception.NotFoundException;
import epsi.myalerts.usecase.db.AdministratorRepository;

/***
 * This class represent the REST API for the administrators. You add/delete/modify informations on
 * administrators. These informations are stored in a database.
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
	/**
	 * This method is called when you do a POST on /api/administrators.
	 * It create a new administrator on the database
	 * @param administrator administrator's information
	 * @return the new object administrator 
	 */
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Administrator createNewAdministrator(@RequestBody Administrator administrator) {
		return administratorRepository.save(administrator);
	}
	/**
	 * This method is called when you do a PUT on /api/administrators, it modify the informations of an administrator in the database
	 * @param administrator new administrator's informations
	 * @return administrator's informations updated 
	 */
	@PutMapping("/{id}")
	public Administrator putAdministrator(@RequestBody Administrator administrator,@PathVariable(name="id") Integer id) {
		return administratorRepository.findById(id).map(adminFind ->{
			adminFind.setLogin(administrator.getLogin());
			adminFind.setName(administrator.getName());
			adminFind.setPassword(administrator.getPassword());
			adminFind.setSurname(administrator.getSurname());
			return administratorRepository.save(adminFind);
		}).orElseGet(()->{
			return administratorRepository.save(administrator);
		});
	}
	/**
	 * This method is called when you do a DELETE on /api/administrators/{id} with the ID of an administrator.
	 * This method delete an administrator from the database
	 * @param id administrator's id who want to be deleted 
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAdministrator(@PathVariable(name="id") Integer id) {
		administratorRepository.deleteById(id);
	}
}
