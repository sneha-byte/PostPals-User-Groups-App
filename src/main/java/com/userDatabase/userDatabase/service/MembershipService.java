package com.userDatabase.userDatabase.service;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.GroupRepository;
import com.userDatabase.userDatabase.repository.MembershipRepository;
import com.userDatabase.userDatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {

    @Autowired
    private MembershipRepository membershipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    public void addUserToGroupWithRole(Long userId, Long groupId, String role) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        boolean exists = membershipRepository.findByUserAndGroup(user, group).isPresent();
        if (exists) {
        	return;
        }
        
        Membership membership = new Membership();
        membership.setUser(user);
        membership.setGroup(group);
        membership.setRole(role);

        membershipRepository.save(membership);
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

    public List<Membership> getGroupsForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return membershipRepository.findByUser(user);
    }
}
