package com.userDatabase.userDatabase.service;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    
    public List<Group> findAllGroups() {
    	return groupRepository.findAll();
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }
}
