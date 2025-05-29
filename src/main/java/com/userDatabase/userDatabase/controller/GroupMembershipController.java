package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membership")
public class GroupMembershipController {

    @Autowired
    private UserService userService;

    @PostMapping("/join")
    public String joinGroup(@RequestParam Long userId, @RequestParam Long groupId) {
        userService.addUserToGroup(userId, groupId);
        return "User joined group";
    }

    @PostMapping("/leave")
    public String leaveGroup(@RequestParam Long userId, @RequestParam Long groupId) {
        userService.removeUserFromGroup(userId, groupId);
        return "User left group";
    }
}
