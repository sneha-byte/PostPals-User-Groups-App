package com.userDatabase.userDatabase.repository;

import com.userDatabase.userDatabase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	
    List<User> findByName(String name);

    public User findTopByName(String name);
    
    public User findByEmail(String email);
    
    List<User> findByNameContainingIgnoreCase(String name);

}
