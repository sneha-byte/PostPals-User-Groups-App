package com.userDatabase.userDatabase.service;

import com.userDatabase.userDatabase.model.Post;
import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getPostsByGroup(Group group) {
        return postRepository.findByGroup(group);
    }
}
