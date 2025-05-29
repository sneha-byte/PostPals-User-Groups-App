package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    

    // Create
    @PostMapping("")
    public String createUser(@RequestBody User user) {
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
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
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
