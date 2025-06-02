package com.userDatabase.userDatabase.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.userDatabase.userDatabase.model.Post;
import java.util.*;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByGroupId(Long groupId);
}
