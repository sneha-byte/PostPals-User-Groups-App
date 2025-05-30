package com.userDatabase.userDatabase.controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.service.UserService;

@Controller
@RequestMapping("")
public class HomeController {

	@Autowired
	private UserService userService; 
	
    @GetMapping("/home")
    public String getHomePage(ModelMap sample) {
    	sample.addAttribute("key", userService.getByUsername("sample-user")); 
        return "home";
    }
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }
    
    
    @GetMapping("/members")
    public String showMembersPage(ModelMap sample) {
        List<User> users = userService.findAllUsers();
        sample.addAttribute("users", users);
        return "members";
    }
}
