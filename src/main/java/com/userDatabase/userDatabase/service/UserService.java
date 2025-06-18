package com.userDatabase.userDatabase.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.userDatabase.userDatabase.exception.UserNotFoundException;
import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.GroupRepository;
import com.userDatabase.userDatabase.repository.MembershipRepository;
import com.userDatabase.userDatabase.repository.UserRepository;

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
        if (!user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<Group> getGroupsForUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
        	return List.of();
        }

        User user = userOptional.get();
        List<Membership> memberships = membershipRepository.findByUser(user);
        return memberships.stream()
                          .map(Membership::getGroup)
                          .toList();
    }

    public User getById(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found by Id");
        }
        return userOptional.get();
    }

    public void update(Long id, User updatedUser) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found for update");
        }

        User existingUser = userOptional.get();
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            String hashedPassword = passwordEncoder.encode(updatedUser.getPassword());
            existingUser.setPassword(hashedPassword);
        }
        userRepository.save(existingUser);
    }

    public User getByUsername(String name) throws UserNotFoundException {
        User user = userRepository.findTopByName(name);
        if (user == null) {
            throw new UserNotFoundException("User not found by username");
        }
        return user;
    }

    public User getByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not found by email");
        }
        return user;
    }

    public void delete(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User not found for deletion");
        }
        userRepository.deleteById(id);
    }

    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public boolean authenticateUser(String name, String password) {
        User user = userRepository.findTopByName(name);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }
}
