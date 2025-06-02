package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.model.Post;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.service.PostService;
import com.userDatabase.userDatabase.service.UserService;
import com.userDatabase.userDatabase.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public Post createPost(@RequestParam Long userId, @RequestParam Long groupId, @RequestParam String content) {
        User user = userService.getById(userId);
        Group group = groupService.getGroupById(groupId);
        Post post = new Post();
        post.setAuthor(user);
        post.setGroup(group);
        post.setContent(content);
        return postService.savePost(post);
    }

    @GetMapping("/group/{groupId}")
    public List<Post> getPostsByGroup(@PathVariable Long groupId) {
        Group group = groupService.getGroupById(groupId);
        return postService.getPostsByGroup(group);
    }
}
