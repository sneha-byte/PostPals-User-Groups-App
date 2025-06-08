package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.MembershipRepository;
import com.userDatabase.userDatabase.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
    
    @Controller
    public class MyGroupsController {

        @Autowired
        private MembershipRepository membershipRepository;

        @GetMapping("/my-groups")
        public String showMyGroups(HttpSession session, Model model) {
            User user = (User) session.getAttribute("loggedInUser");
            if (user == null) {
                return "redirect:/login";
            }

            List<Membership> memberships = membershipRepository.findByUser(user);
            model.addAttribute("user", user);
            model.addAttribute("memberships", memberships);
            return "myGroups";
        }
    }
}
