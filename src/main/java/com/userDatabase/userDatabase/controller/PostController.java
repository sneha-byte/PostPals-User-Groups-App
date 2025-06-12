package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.service.PostService;
import com.userDatabase.userDatabase.service.GroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public String createPost(@RequestParam Long userId,
                             @RequestParam Long groupId,
                             @RequestParam String content) {

        postService.createPost(userId, groupId, content);
        return "redirect:/groups/" + groupId;
    }
}
