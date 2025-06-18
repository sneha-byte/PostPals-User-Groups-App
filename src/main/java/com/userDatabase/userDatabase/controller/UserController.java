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
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    // Create
    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        try {
            userService.create(user);
            response.put("userResponse", new UserResponse(true, "User created successfully"));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            logger.error("Error creating user", e);
            response.put("userResponse", new UserResponse(false, "Failed to create user"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Read all
    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<User> users = userService.getAll();
            response.put("users", users);
            response.put("userResponse", new UserResponse(true, "All users fetched"));
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting all users", e);
            response.put("userResponse", new UserResponse(false, "Failed to fetch users"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Read one user by ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userService.getById(id);
            response.put("user", user);
            response.put("userResponse", new UserResponse(true, "User found successfully"));
            return ResponseEntity.ok(response);
            
        } catch (UserNotFoundException e) {
            logger.error("User not found by Id: {}", id, e);
            response.put("userResponse", new UserResponse(false, "User not found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Update one user
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Map<String, Object> response = new HashMap<>();
        try {
            userService.update(id, updatedUser);
            response.put("userResponse", new UserResponse(true, "User updated successfully"));
            return ResponseEntity.ok(response);
            
        } catch (UserNotFoundException e) {
            logger.error("User not found for update: {}", id, e);
            response.put("userResponse", new UserResponse(false, "User not found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            logger.error("Error updating user", e);
            response.put("userResponse", new UserResponse(false, "Failed to update user"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Read one user by username
    @GetMapping("/by-username")
    public ResponseEntity<Map<String, Object>> getByUsername(@RequestParam(name = "username") String username) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userService.getByUsername(username);
            response.put("user", user);
            response.put("userResponse", new UserResponse(true, "User found by username"));
            return ResponseEntity.ok(response);
            
        } catch (UserNotFoundException e) {
            logger.error("User not found by username: {}", username, e);
            response.put("userResponse", new UserResponse(false, "User not found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    
    // Read one user by email
    @GetMapping("/by-email")
    public ResponseEntity<Map<String, Object>> getByEmail(@RequestParam(name = "email") String email) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userService.getByEmail(email);
            response.put("user", user);
            response.put("userResponse", new UserResponse(true, "User found by email"));
            return ResponseEntity.ok(response);
            
        } catch (UserNotFoundException e) {
            logger.error("User not found by email: {}", email, e);
            response.put("userResponse", new UserResponse(false, "User not found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Delete one user
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            userService.delete(id);
            response.put("userResponse", new UserResponse(true, "User deleted successfully"));
            return ResponseEntity.ok(response);
            
        } catch (UserNotFoundException e) {
            logger.error("User not found for delete: {}", id, e);
            response.put("userResponse", new UserResponse(false, "User not found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            logger.error("Error deleting user", e);
            response.put("userResponse", new UserResponse(false, "Failed to delete user"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Search all users by name
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> findByName(@RequestParam String name) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<User> users = userService.findByName(name);
            response.put("users", users);
            response.put("userResponse", new UserResponse(true, "Users found with name: " + name));
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.error("Error searching users by name", e);
            response.put("userResponse", new UserResponse(false, "Failed to search users"));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
