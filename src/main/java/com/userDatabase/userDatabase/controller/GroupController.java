package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("")
    public Group createGroup(@RequestBody Group group) {
        return groupService.createGroup(group);
    }

    @GetMapping("")
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }
}
