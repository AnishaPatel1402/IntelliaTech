package com.info.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.entity.User;
import com.info.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService service;
	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User dbUser = service.createUser(user);
        return ResponseEntity.ok(dbUser);
    }
	
	 @GetMapping("/{id}")
	    public ResponseEntity<User> getUserById(@PathVariable int id) {
	        User user = service.getUserById(id);
	        return ResponseEntity.ok(user);
	    }

	    @GetMapping
	    public ResponseEntity<List<User>> getAllUsers() {
	        List<User> users = service.getAllUsers();
	        return ResponseEntity.ok(users);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
	        user.setId(id); 
	        User updatedUser = service.updateUser(user);
	        return ResponseEntity.ok(updatedUser);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<User> deleteUser(@PathVariable int id) {
	        return ResponseEntity.ok(service.deleteUser(id));
	    }
}
