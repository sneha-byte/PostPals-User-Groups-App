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

@Controller
public class GroupViewController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private PostService postService;
    
    @Autowired
    private UserService userService; 

    @GetMapping("/groups/{groupId}")
    public String viewGroup(@PathVariable Long groupId, 
                            @RequestParam Long userId, 
                            Model sample) {
        Group group = groupService.getGroupById(groupId);
        List<Post> posts = postService.getPostsByGroup(group); 
        sample.addAttribute("group", group);
        sample.addAttribute("posts", posts);
        sample.addAttribute("user", userService.getById(userId));
        return "group-view";
    }
}

