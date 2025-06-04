package com.userDatabase.userDatabase.service;

import com.userDatabase.userDatabase.model.Post;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.repository.GroupRepository;
import com.userDatabase.userDatabase.repository.PostRepository;
import com.userDatabase.userDatabase.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository; 
    
    @Autowired
    private GroupRepository groupRepository; 
    
    public Post savePost(Post post) {
        return postRepository.save(post);
    }
    
    public void createPost(Long userId, Long groupId, String content) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        Group group = groupRepository.findById(groupId)
            .orElseThrow(() -> new RuntimeException("Group not found"));

        Post post = new Post();
        post.setAuthor(user);
        post.setGroup(group);
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());

        postRepository.save(post);
    }

    public List<Post> getPostsByGroup(Group group) {
        return postRepository.findByGroup(group);
    }
}
