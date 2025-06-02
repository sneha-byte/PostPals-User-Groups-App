package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.service.MembershipService;
import com.userDatabase.userDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membership")
public class GroupMembershipController {

    @Autowired
    private UserService userService;
  
    @Autowired
    private MembershipService membershipService;

    

    @PostMapping("/add")
    public String addUserToGroup(
            @RequestParam Long userId,
            @RequestParam Long groupId,
            @RequestParam String role) {
        userService.addUserToGroupWithRole(userId, groupId, role);
        return "redirect:/groups/" + groupId; 
    }

    @PostMapping("/leave")
    public String leaveGroup(@RequestParam(value="userId") Long userId, @RequestParam(value="groupId") Long groupId, @RequestParam(value="role") String role) {
        userService.removeUserFromGroup(userId, groupId);
        return "User left group";
    }
}
