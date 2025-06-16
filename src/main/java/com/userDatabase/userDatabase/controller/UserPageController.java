package com.userDatabase.userDatabase.controller;

import java.util.List;

import com.userDatabase.userDatabase.model.User; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.userDatabase.userDatabase.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class UserPageController {

    @Autowired
    private UserService userService;

    // Show all members
    @GetMapping("/members")
    public String showMembersPage(HttpSession session, Model sample) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
        	return "redirect:/login";
        }

        List<com.userDatabase.userDatabase.model.User> users = userService.findAllUsers();
        sample.addAttribute("users", users);
        sample.addAttribute("user", user);
        return "members";
    }
}
	
