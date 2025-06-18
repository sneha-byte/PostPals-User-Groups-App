package com.userDatabase.userDatabase.service;

import com.userDatabase.userDatabase.exception.GroupNotFoundException;
import com.userDatabase.userDatabase.exception.UserNotFoundException;
import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.GroupRepository;
import com.userDatabase.userDatabase.repository.MembershipRepository;
import com.userDatabase.userDatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    // Creates a new membership using user group and role and using membershipRepo.save 
    public void addUserToGroupWithRole(Long userId, Long groupId, String role)
            throws UserNotFoundException, GroupNotFoundException {        
    	try {
            Optional<User> userOpt = userRepository.findById(userId);
            if (!userOpt.isPresent()) {
                throw new UserNotFoundException("User not found with ID: " + userId);
            }
            User user = userOpt.get();

            Optional<Group> groupOpt = groupRepository.findById(groupId);
            if (!groupOpt.isPresent()) {
                throw new GroupNotFoundException("Group not found with ID: " + groupId);
            }
            Group group = groupOpt.get();

            boolean exists = membershipRepository.findByUserAndGroup(user, group).isPresent();
            if (exists) {
                return;
            }

            Membership membership = new Membership();
            membership.setUser(user);
            membership.setGroup(group);
            membership.setRole(role);

            membershipRepository.save(membership);
            
        } catch (Exception e) {
            throw new RuntimeException("Error adding user to group", e);
        }
    }

    // Deletes membership using membership repo.delete 
    public void removeUserFromGroup(Long userId, Long groupId) {
        try {
            Optional<User> userOpt = userRepository.findById(userId);
            if (!userOpt.isPresent()) {
                throw new UserNotFoundException("User not found with ID: " + userId);
            }
            User user = userOpt.get();

            Optional<Group> groupOpt = groupRepository.findById(groupId);
            if (!groupOpt.isPresent()) {
                throw new GroupNotFoundException("Group not found with ID: " + groupId);
            }
            Group group = groupOpt.get();

            Optional<Membership> membershipOpt = membershipRepository.findByUserAndGroup(user, group);
            if (!membershipOpt.isPresent()) {
                throw new RuntimeException("Membership not found for user and group");
            }

            membershipRepository.delete(membershipOpt.get());
            
        } catch (Exception e) {
            throw new RuntimeException("Error removing user from group", e);
        }
    }

    // Returns the list of memberships a user has 
    public List<Membership> getGroupsForUser(Long userId) {
        try {
            Optional<User> userOpt = userRepository.findById(userId);
            if (!userOpt.isPresent()) {
                throw new UserNotFoundException("User not found with ID: " + userId);
            }
            User user = userOpt.get();
            return membershipRepository.findByUser(user);
            
        } catch (Exception e) {
            throw new RuntimeException("Error getting groups for user", e);
        }
    }
}
