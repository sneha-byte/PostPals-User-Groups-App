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

    //Show all groups with users groups at the top
    @GetMapping("/groups")
    public String showGroupsPage(HttpSession session, Model sample) {
        User user = (User) session.getAttribute("loggedInUser"); 

        if (user == null) {
            return "redirect:/login"; 
        }

        List<Group> allGroups = groupRepository.findAll();
        Set<Group> userGroups = user.getMemberships()
                                    .stream()
                                    .map(Membership::getGroup)
                                    .collect(Collectors.toSet());

        List<Group> joinedGroups = new ArrayList<>();
        List<Group> otherGroups = new ArrayList<>();

        for (Group group : allGroups) {
            if (userGroups.contains(group)) {
                joinedGroups.add(group);
            } else {
                otherGroups.add(group);
            }
        }

        sample.addAttribute("joinedGroups", joinedGroups);
        sample.addAttribute("otherGroups", otherGroups);
        sample.addAttribute("user", user);
        return "groups";
    }


    // Show all members
    @GetMapping("/members")
    public String showMembersPage(HttpSession session, Model sample) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
        	return "redirect:/login";
        }

        List<User> users = userService.findAllUsers();
        sample.addAttribute("users", users);
        sample.addAttribute("user", user);
        return "members";
    }

    // Join a group
    @PostMapping("/groups/join")
    public String joinGroup(@RequestParam Long groupId,
                            @RequestParam Long userId) {

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
