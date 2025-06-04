package com.userDatabase.userDatabase.controller;

import java.util.List;

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

    // Home page (requires login)
    @GetMapping("/home")
    public String getHomePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
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
                        Model model) {

        User user = userRepository.findTopByName(name);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("loggedInUser", user);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
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
                               Model model) {

        if (userService.getByUsername(name) != null) {
            model.addAttribute("error", "Username already exists!");
            return "signup";
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password)); // ✅ Hashed password

        userService.create(newUser);
        model.addAttribute("success", "User registered successfully! Please log in.");
        return "login";
    }

    // Show all groups
    @GetMapping("/groups")
    public String showGroupsPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        List<Group> groups = groupService.findAllGroups();
        model.addAttribute("groups", groups);
        model.addAttribute("user", user);  // ✅ Makes ${user.id} available
        return "groups";
    }

    // Show all members
    @GetMapping("/members")
    public String showMembersPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("user", user);
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

    // Show groups for a user
    @GetMapping("/my-groups")
    public String viewUserGroups(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        List<Membership> memberships = membershipRepository.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("memberships", memberships);
        return "my-groups";
    }
}
