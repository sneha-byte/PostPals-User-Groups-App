package com.userDatabase.userDatabase.controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userDatabase.userDatabase.model.*;
import com.userDatabase.userDatabase.service.*;

@Controller
@RequestMapping("")
public class HomeController {

	@Autowired
	private UserService userService; 
	
	@Autowired
	private GroupService groupService; 
	
    @GetMapping("/home")
    public String getHomePage(ModelMap sample) {
    	sample.addAttribute("key", userService.getByUsername("sample-user")); 
        return "home";
    }
    
    @PostMapping("/login")
    public String handleLogin(@RequestParam String name, @RequestParam String password, ModelMap sample) {

        boolean isAuthenticated = userService.authenticateUser(name, password);

        if (isAuthenticated) {
            sample.addAttribute("key", userService.getByUsername(name));
            return "redirect:/groups";  
        } else {
            sample.addAttribute("error", "Invalid username or password");
            return "login";  
        }
    }


    
    @GetMapping("/groups")
    public String showGroupsPage(ModelMap sample) {
        List<Group> groups = groupService.findAllGroups();
        sample.addAttribute("groups", groups);
        return "groups";
    }
    
    @GetMapping("/members")
    public String showMembersPage(ModelMap sample) {
        List<User> users = userService.findAllUsers();
        sample.addAttribute("users", users);
        return "members";
    }
}
