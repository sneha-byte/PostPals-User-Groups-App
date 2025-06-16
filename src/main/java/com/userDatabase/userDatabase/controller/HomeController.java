package com.userDatabase.userDatabase.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.userDatabase.userDatabase.model.*;
import com.userDatabase.userDatabase.repository.*;
import com.userDatabase.userDatabase.service.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Home page 
    @GetMapping("/")
    public String showHome(HttpSession session) {
        if (session.getAttribute("loggedInUser") != null) {
            return "redirect:/my-groups";
        }
        return "home"; 
    }

    // Show login form
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Handle login
    @PostMapping("/login")
    public String login(@RequestParam String name,
                        @RequestParam String password,
                        HttpSession session,
                        Model sample) {
        User user = userRepository.findTopByName(name);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/my-groups";
        } else {
            sample.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "home";  
    }

    // Show signup form
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup";
    }

    // Handle signup
    @PostMapping("/signup")
    public String handleSignup(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model sample) {

        if (userService.getByUsername(name) != null) {
            sample.addAttribute("error", "Username already exists!");
            return "signup";
        }
        
        if (userService.getByEmail(email) != null) {
            sample.addAttribute("error", "Email already exists!");
            return "signup";
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));

        userService.create(newUser);
        sample.addAttribute("success", "User registered successfully! Please log in.");
        return "login";
    }
}
