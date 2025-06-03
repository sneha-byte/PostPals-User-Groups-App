package com.userDatabase.userDatabase.repository;
import com.userDatabase.userDatabase.model.Post;
import com.userDatabase.userDatabase.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByGroup(Group group);
}
