package epsi.myalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsi.myalerts.exceptions.NotFoundException;
import epsi.myalerts.model.User;
import epsi.myalerts.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable(name="id") Integer id) {
		return userRepository.findById(id).orElseThrow(NotFoundException::new);
	}
	
	@PostMapping("")
	public User createNewUser(@RequestBody User user) {
		return userRepository.save(user);
	}
}
