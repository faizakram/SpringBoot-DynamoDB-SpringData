package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.UserCrudDAO;
import com.example.demo.repository.UserCrudServiceRepository;

@RestController
public class UserCrudController {
	@Autowired
	private UserCrudServiceRepository userCrudServiceRepository;
	@Autowired
	private UserCrudDAO userCrudDAO;

	@PostMapping(value = "/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User response = userCrudServiceRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

	@PutMapping(value = "/user")
	public ResponseEntity<User> updateeUser(@RequestBody User user) {
		User updateUser = userCrudServiceRepository.save(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateUser);

	}

	@GetMapping(value = "/user/{userId}")
	public ResponseEntity<Object> getUser(@PathVariable String userId) {
		Optional<User> response = userCrudServiceRepository.findById(userId);
		if (response.isPresent())
			return ResponseEntity.status(HttpStatus.FOUND).body(response.get());
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Uer Found");
	}

	@DeleteMapping(value = "/user/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable String userId) {
		userCrudServiceRepository.deleteById(userId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted Successfully");
	}

	@DeleteMapping(value = "/users")
	public ResponseEntity<Object> deleteUser() {
		userCrudServiceRepository.deleteAll();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted Successfully");
	}

	@GetMapping(value = "/users/{data}")
	public ResponseEntity<Object> getUsers(@PathVariable String data) {
		List<User> users = userCrudDAO.getFilterData(data);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(users);
	}
}
