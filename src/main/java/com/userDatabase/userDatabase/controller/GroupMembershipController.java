package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.service.MembershipService;
import com.userDatabase.userDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membership")
public class GroupMembershipController {

    @Autowired
    private MembershipService membershipService;

    @PostMapping("/add")
    public String addMembership(@RequestParam Long userId,
                                @RequestParam Long groupId,
                                @RequestParam String role) {
        membershipService.addUserToGroupWithRole(userId, groupId, role);
        return "redirect:/myGroups?userId=" + userId;
    }

    @PostMapping("/leave")
    public String leaveGroup(@RequestParam Long userId,
                              @RequestParam Long groupId) {
        membershipService.removeUserFromGroup(userId, groupId);
        return "User left group";
    }
}
