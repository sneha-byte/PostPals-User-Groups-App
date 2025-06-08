package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.service.MembershipService;
import com.userDatabase.userDatabase.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/membership")
public class GroupMembershipController {

    @Autowired
    private MembershipService membershipService;

    @Autowired 
    private UserService userService;
    
    @PostMapping("/add")
    public String addMembership(@RequestParam Long groupId,
                                @RequestParam String role,
                                HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        membershipService.addUserToGroupWithRole(user.getId(), groupId, role);
        return "redirect:/my-groups";
    }

    @PostMapping("/leave")
    public String leaveGroup(@RequestParam Long groupId,
                             HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) return "redirect:/login";

        membershipService.removeUserFromGroup(user.getId(), groupId);
        return "redirect:/my-groups";
    }
}
