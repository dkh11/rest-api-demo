package com.example.demo.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exception.EntityNotFoundException;

@RestController
public class UserController {

	@Autowired
	UserDaoService userService;

	// get all users
	@GetMapping("/users")
	public List<User> allUsers() {
		return userService.allUsers();
	}
	
	// get one user
	@GetMapping("/users/{id}")
	public User oneUser(@PathVariable int id) {
		User foundUser = userService.oneUser(id);
	
//		if (foundUser == null) {
//			throw new AuthorNotFoundException("Author with id: " + id + " not found");
//		}
		
		return foundUser;
	}
	
	// create new user
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.saveUser(user);
		if (savedUser == null) {
			throw new EntityNotFoundException("User with id: " + savedUser.getId() + " Not Found");
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	// delete one user
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}


}



































