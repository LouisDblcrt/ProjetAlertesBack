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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import epsi.myalerts.exceptions.NotAuthorized;
import epsi.myalerts.exceptions.NotFoundException;
import epsi.myalerts.model.User;
import epsi.myalerts.repository.UserRepository;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
/**
 * This class represent the REST API for the users. You add/delete/modify users.
 * These informations are stored in a database.
 *
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserRepository userRepository;

	/**
	 * This method is called when you do a GET on /api/users. It returns the list of
	 * users
	 * 
	 * @return list of users
	 */
	@GetMapping("")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * This method is called when you do a GET on /api/users/{id}. It returns
	 * informations of the users with this id.
	 * 
	 * @param id Id of an user
	 * @return informations on the user
	 */
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(name = "id") Integer id) {
		return userRepository.findById(id).orElseThrow(NotFoundException::new);
	}
/**
 * This method is called when you do a POST on /api/users.
 * It creates a user in the database
 * @param user informations of the new user
 * @return user created
 */
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public User createNewUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@PutMapping("/{id}")
	public User modifyUser(@RequestBody User user,@PathVariable(name="id")Integer id) {
		return userRepository.findById(user.getId()).map(userFind ->{
			userFind.setEmail(user.getEmail());
			userFind.setPhone_number(user.getPhone_number());
			userFind.setPassword(user.getPassword());
			return userRepository.save(userFind);
		}).orElseGet(()->{
			user.setId(id);
			return userRepository.save(user);
		});
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable(name="id") Integer id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/login")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public User login(@RequestBody User user) {
		User userToLog = userRepository.selectUserByEmail(user.getEmail()).orElseThrow(NotFoundException::new);
		
		if(userToLog.getPassword().equals(user.getPassword())) {
			return userToLog;
		}
		throw new NotAuthorized();
	}
}
