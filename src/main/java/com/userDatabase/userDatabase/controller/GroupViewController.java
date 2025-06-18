package com.userDatabase.userDatabase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Post;
import com.userDatabase.userDatabase.service.GroupService;
import com.userDatabase.userDatabase.service.PostService;
import com.userDatabase.userDatabase.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class GroupViewController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PostService postService;
    
    @Autowired
    private UserService userService; 

    // Returns a view of the group page by getting the posts from that group and showing it in the perspective
    // of the logged in user. 
    @GetMapping("/groups/{groupId}")
    public String viewGroup(@PathVariable Long groupId, Model sample, HttpSession session) {
        var user = session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        try {
            Group group = groupService.getGroupById(groupId);
            List<Post> posts = postService.getPostsByGroup(group);

            sample.addAttribute("group", group);
            sample.addAttribute("posts", posts);
            sample.addAttribute("user", user);

            return "group-view";

        } catch (com.userDatabase.userDatabase.exception.GroupNotFoundException e) {
            sample.addAttribute("errorMessage", "Group not found");
            return "error"; 
        } catch (Exception e) {
            sample.addAttribute("errorMessage", "An unexpected error occurred");
            return "error";
        }
    }

    
}

