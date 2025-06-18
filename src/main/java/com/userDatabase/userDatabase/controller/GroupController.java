package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.exception.GroupNotFoundException;
import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.GroupRepository;
import com.userDatabase.userDatabase.repository.MembershipRepository;
import com.userDatabase.userDatabase.repository.UserRepository;
import com.userDatabase.userDatabase.service.GroupService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String createGroup(@RequestParam String groupName, HttpSession session, Model sample) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            Group newGroup = new Group();
            newGroup.setName(groupName);
            groupRepository.save(newGroup);

            Membership membership = new Membership();
            membership.setGroup(newGroup);
            membership.setUser(user);
            membership.setRole("creator");
            membershipRepository.save(membership);

            return "redirect:/group/groups";

        } catch (Exception e) {
            sample.addAttribute("errorMessage", "Error creating group: " + e.getMessage());
            return "groupCreateForm"; // or any error page you have
        }
    }

    @GetMapping("")
    @ResponseBody
    public List<Group> getAllGroups() {
        return groupService.findAllGroups();
    }

    // Delete group using group service delete function
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteGroup(@PathVariable Long id) {
        try {
            groupService.deleteGroup(id);
            return ResponseEntity.ok("Group deleted successfully");
            
        } catch (GroupNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting group");
        }
    }

}
