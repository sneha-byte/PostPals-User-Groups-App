package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.GroupRepository;
import com.userDatabase.userDatabase.repository.MembershipRepository;
import com.userDatabase.userDatabase.repository.UserRepository;
import com.userDatabase.userDatabase.service.MembershipService;
import com.userDatabase.userDatabase.service.UserService;

import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/membership")
public class GroupMembershipController {

    @Autowired
    private MembershipService membershipService;

    @Autowired 
    private UserService userService;
    
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private UserRepository userRepository;
    
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
        if (user == null) {
        	return "redirect:/login";
        }

        membershipService.removeUserFromGroup(user.getId(), groupId);
        return "redirect:/my-groups";
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
