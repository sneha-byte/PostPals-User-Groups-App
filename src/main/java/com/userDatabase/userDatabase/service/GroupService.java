package com.userDatabase.userDatabase.service;

import com.userDatabase.userDatabase.exception.GroupNotFoundException;
import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    // Save a new group to the group repo
    public Group createGroup(Group group) {
        try {
            return groupRepository.save(group);
        } catch (Exception e) {
            throw new RuntimeException("Error creating group", e);
        }
    }

    // Use group repo find all function to get list of all groups
    public List<Group> findAllGroups() {
        try {
            return groupRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving groups", e);
        }
    }

    // Uses id to delete the group and group repo function delete 
    public void deleteGroup(Long id) throws GroupNotFoundException {
        try {
            if (!groupRepository.existsById(id)) {
                throw new GroupNotFoundException("Group not found with ID: " + id);
            }
           
            groupRepository.deleteById(id);
        } catch (GroupNotFoundException e) {
            throw e; 
        } catch (Exception e) {
            throw new RuntimeException("Error deleting group with ID: " + id, e);
        }
    }

    // find the group with inputted id using group repo find by id function
    public Group getGroupById(Long id) throws GroupNotFoundException {
        try {
            Optional<Group> groupOpt = groupRepository.findById(id);

            if (!groupOpt.isPresent()) {
                throw new GroupNotFoundException("Group not found with ID: " + id);
            }

            return groupOpt.get();
        } catch (GroupNotFoundException e) {
            throw e; 
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving group with ID: " + id, e);
        }
    }

}
