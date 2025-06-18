package com.userDatabase.userDatabase.service;

import com.userDatabase.userDatabase.model.Post;
import com.userDatabase.userDatabase.controller.UserController;
import com.userDatabase.userDatabase.exception.GroupNotFoundException;
import com.userDatabase.userDatabase.exception.PostNotFoundException;
import com.userDatabase.userDatabase.exception.UserNotFoundException;
import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.PostRepository;
import com.userDatabase.userDatabase.repository.UserRepository;
import com.userDatabase.userDatabase.repository.GroupRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
	
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository; 

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    // Creates and saves post to postRepo and makes sure user and group exist
    public void createPost(Long userId, Long groupId, String content) {
        try {
            User user = userRepository.findById(userId).orElse(null);
            if (user == null) {
                throw new UserNotFoundException("User not found with ID: " + userId);
            }

            Group group = groupRepository.findById(groupId).orElse(null);
            if (group == null) {
                throw new GroupNotFoundException("Group not found with ID: " + groupId);
            }

            Post post = new Post();
            post.setAuthor(user);
            post.setGroup(group);
            post.setContent(content);
            post.setCreatedAt(LocalDateTime.now());

            postRepository.save(post);

        } catch (UserNotFoundException | GroupNotFoundException e) {
            logger.error("Error creating post", e);
            throw new RuntimeException(e.getMessage()); 
        }
    }

    // Deletes post only if user is the one who wrote it 
    public boolean deletePostIfAuthorized(Long postId, Long userId) {
        try {
            Post post = postRepository.findById(postId).orElse(null);
            if (post == null) {
                throw new PostNotFoundException("Post not found with ID: " + postId);
            }

            if (!post.getAuthor().getId().equals(userId)) {
                logger.warn("Unauthorized delete attempt by user: {}", userId);
                return false;
            }

            postRepository.deleteById(postId);
            return true;

        } catch (PostNotFoundException e) {
            logger.error("Error deleting post", e);
            return false;
        }
    }

    // Returns list of all posts in a group
    public List<Post> getPostsByGroup(Group group) {
        return postRepository.findByGroup(group);
    }
}

