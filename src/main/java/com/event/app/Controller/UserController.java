package com.event.app.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.app.Events.AuditEventPublisher;
import com.event.app.model.Users;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private AuditEventPublisher publisher;

	private List<Users> users = new ArrayList<>();

	@GetMapping
	public List<Users> getUsers() {
		try {
			users.add(Users.builder().id(1).name("ashish").email("test.gamil.com").build());
			users.add(Users.builder().id(2).name("sandesh").email("test2.gmail.com").build());
			publisher.publishEvent("here are all the users!");
			return users;
		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping
	public ResponseEntity<Users> addUsers(@RequestBody Users user) {
		if (user.getEmail() == null || !user.getEmail().contains("@")) {
			return ResponseEntity.badRequest().build(); // Basic email validation
		}
		users.add(Users.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).build());
		publisher.publishEvent("User added successfully: " + user.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

}
