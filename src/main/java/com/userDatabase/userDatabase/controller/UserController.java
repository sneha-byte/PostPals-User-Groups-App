package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.exception.UserNotFoundException;
import com.userDatabase.userDatabase.exception.UserResponse;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    // Create
    @PostMapping("")
    public String createUser(@RequestBody User user) {
    	logger.info("CreateUser(): begin");
        userService.create(user);
        return "success";
    }

    // Read all
    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    // Read one user by ID
    @GetMapping("/{id}")
    public ResponseEntity <Map<String, Object>> getUserById(@PathVariable Long id) {
    	Map<String, Object> response = new HashMap<>();
    	boolean success = false; 
        try {
        	User user = userService.getById(id);
        	response.put("user", user);
        	response.put("userResponse", new UserResponse(true, "User found successfully"));
        	return ResponseEntity.status(HttpStatus.OK).body(response);
        	
        } catch (UserNotFoundException e) {
        	logger.error("User not found by Id: {}", id, e);
        	response.put("userResponse", new UserResponse(false, "User not found"));
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Update one user
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        userService.update(id, updatedUser);
    }

    // Read one user by user name
    @GetMapping("/by-username")
    public User getByUsername(@RequestParam(name = "username") String username) {
        return userService.getByUsername(username);
    }

    // Read one user by email
    @GetMapping("/by-email")
    public User getByEmail(@RequestParam(name = "email") String email) {
        return userService.getByEmail(email);
    }
    
    // Delete one user
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    // Search all users by name
    @GetMapping("/search")
    public List<User> findByName(@RequestParam String name) {
        return userService.findByName(name);
    }
}
