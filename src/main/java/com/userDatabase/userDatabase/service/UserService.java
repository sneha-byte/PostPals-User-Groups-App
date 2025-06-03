package com.userDatabase.userDatabase.service;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.GroupRepository;
import com.userDatabase.userDatabase.repository.MembershipRepository;
import com.userDatabase.userDatabase.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private GroupRepository groupRepository;

    public void create(User user) {
        // Check if password is already encrypted 
        if (!user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<Group> getGroupsForUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return List.of();
        
        List<Membership> memberships = membershipRepository.findByUser(user);
        return memberships.stream()
                          .map(Membership::getGroup)
                          .toList();
    }
    
    public User getById(Long id) {
    	Optional<User> userOptional = userRepository.findById(id);
    	if (userOptional.isEmpty()) {
    		return null; 
    	}
        return userOptional.get();
    }

    public void update(Long id, User updatedUser) {
    	try {
    		User existingUser = userRepository.findById(id).get();

            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());

            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                String hashedPassword = passwordEncoder.encode(updatedUser.getPassword());
                existingUser.setPassword(hashedPassword);
            }
            userRepository.save(existingUser);
    	}
    	
    	catch(Exception e) {
        	new RuntimeException("Group or user not found");
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
        return user != null && passwordEncoder.matches(password, user.getPassword());
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
    	try {
    		User user = userRepository.findById(userId).get();
	        Group group = groupRepository.findById(groupId).get();
	        Membership membership = membershipRepository.findByUserAndGroup(user, group).get();
	        membershipRepository.delete(membership);
    	}
    	catch(Exception e) {
        	new RuntimeException("Group or user not found");
    	}
    }
}