package com.userDatabase.userDatabase.service;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.GroupRepository;
import com.userDatabase.userDatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
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
    
    /** return all users
     * @return array of users 
     */
    public List<User> findAllUsers() {
    	return userRepository.findAll();
    }
    
    public void addUserToGroup(Long userId, Long groupId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        user.getGroups().add(group);
        userRepository.save(user);
    }


    public void removeUserFromGroup(Long userId, Long groupId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        user.getGroups().remove(group);
        userRepository.save(user);
    }
}
