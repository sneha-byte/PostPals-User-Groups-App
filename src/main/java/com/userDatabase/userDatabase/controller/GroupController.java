package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.GroupRepository;
import com.userDatabase.userDatabase.repository.MembershipRepository;
import com.userDatabase.userDatabase.service.GroupService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @PostMapping("/create")
    public String createGroup(@RequestParam String groupName, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        Group newGroup = new Group();
        newGroup.setName(groupName);
        groupRepository.save(newGroup);

        // Auto-add creator as member
        Membership membership = new Membership();
        membership.setGroup(newGroup);
        membership.setUser(user);
        membership.setRole("member");
        membershipRepository.save(membership);

        return "redirect:/groups";
    }

    @GetMapping("")
    @ResponseBody
    public List<Group> getAllGroups() {
        return groupService.findAllGroups();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
}
