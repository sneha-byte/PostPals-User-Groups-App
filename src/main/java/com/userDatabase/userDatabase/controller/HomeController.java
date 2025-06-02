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
import com.userDatabase.userDatabase.repository.GroupRepository;
import com.userDatabase.userDatabase.repository.MembershipRepository;
import com.userDatabase.userDatabase.repository.UserRepository;
import com.userDatabase.userDatabase.service.*;

@Controller
@RequestMapping("")
public class HomeController {
	
	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MembershipRepository membershipRepository;
	
    @GetMapping("/home")
    public String getHomePage(ModelMap sample) {
    	sample.addAttribute("key", UserService.getByUsername("sample-user")); 
        return "home";
    }
    
    //GET method because user needs to use /login to see the login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; 
    }

    //POST method because the form sends a post request to /login
    @PostMapping("/login")
    public String handleLogin(@RequestParam String name, @RequestParam String password,ModelMap sample) {

        boolean isAuthenticated = UserService.authenticateUser(name, password);

        if (isAuthenticated) {
        	sample.addAttribute("key", UserService.getByUsername(name));
            return "redirect:/groups"; 
        } else {
        	sample.addAttribute("error", "Invalid username or password");
            return "login"; 
        }
    }
    
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String handleSignup(@RequestParam String name, @RequestParam String email,@RequestParam String password,ModelMap sample) {

        if (UserService.getByUsername(name) != null) {
            sample.addAttribute("error", "Username already exists!");
            return "signup";
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password); 

        UserService.create(newUser);
        sample.addAttribute("success", "User registered successfully! Please log in.");
        return "login";
    }
    
    @GetMapping("/groups")
    public String showGroupsPage(ModelMap sample) {
        List<Group> groups = GroupService.findAllGroups();
        sample.addAttribute("groups", groups);
        return "groups";
    }
    
    @GetMapping("/members")
    public String showMembersPage(ModelMap sample) {
        List<User> users = UserService.findAllUsers();
        sample.addAttribute("users", users);
        return "members";
    }
    
    @PostMapping("/groups/join")
    public String joinGroup(@RequestParam Long groupId, @RequestParam Long userId) {
        Group group = groupRepository.findById(groupId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (group != null && user != null) {
            Membership membership = new Membership();
            membership.setGroup(group);
            membership.setUser(user);
            membership.setRole("member");
            membershipRepository.save(membership);
        }

        return "redirect:/groups/" + groupId;
    }

}
