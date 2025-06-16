package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.GroupRepository;
import com.userDatabase.userDatabase.repository.MembershipRepository;
import com.userDatabase.userDatabase.repository.UserRepository;
import com.userDatabase.userDatabase.service.GroupService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private UserRepository userRepository;

    //Show all groups with users groups at the top
    @GetMapping("/groups")
    public String showGroupsPage(HttpSession session, Model sample) {
        User sessionUser = (User) session.getAttribute("loggedInUser"); 

        if (sessionUser == null) {
            return "redirect:/login"; 
        }

        // Re-fetch user from DB so memberships is loaded in Hibernate session
        User user = userRepository.findById(sessionUser.getId()).orElse(null);
        if (user == null) {
            return "redirect:/login"; // fallback
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
        membership.setRole("creator");
        membershipRepository.save(membership);

        return "redirect:/group/groups";
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
