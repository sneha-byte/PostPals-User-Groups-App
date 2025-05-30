package com.userDatabase.userDatabase.service;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.GroupRepository;
import com.userDatabase.userDatabase.repository.MembershipRepository;
import com.userDatabase.userDatabase.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private GroupRepository groupRepository;

    public void create(User user) {
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
    	Optional<User> userOptional = userRepository.findById(id);
    	if (userOptional.isEmpty()) {
    		return null; 
    	}
        return userOptional.get();
    }

    public void update(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            userRepository.save(existingUser);
        }
    }
    
    public User getByUsername(String name) {
        return userRepository.findTopByName(name);
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }
    
    // return all users 
    public List<User> findAllUsers() {
    	return userRepository.findAll();
    }
    
    public boolean authenticateUser(String name, String password) {
        User user = userRepository.findTopByName(name);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    
    public void addUserToGroupWithRole(Long userId, Long groupId, String role) {
        
        try {
        	User user = userRepository.findById(userId).get(); 
        	Group group = groupRepository.findById(groupId).get(); 
        	Membership membership = new Membership();
            membership.setUser(user);
            membership.setGroup(group);
            membership.setRole(role);
            
            membershipRepository.save(membership);  
        }
        catch(Exception e) {
        	new RuntimeException("Group or user not found");
        }        
    }

    public void removeUserFromGroup(Long userId, Long groupId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Membership membership = membershipRepository.findByUserAndGroup(user, group)
                .orElseThrow(() -> new RuntimeException("Membership not found"));

        membershipRepository.delete(membership);
    }
}
