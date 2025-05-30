package com.userDatabase.userDatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public String showMembersPage(@RequestParam(required = false) String search, ModelMap sample) {
        if (search != null && !search.isEmpty()) {
            sample.addAttribute("users", userService.findByName(search));
        } else {
            sample.addAttribute("users", userService.findAllUsers());
        }
        return "members";
    }
}
