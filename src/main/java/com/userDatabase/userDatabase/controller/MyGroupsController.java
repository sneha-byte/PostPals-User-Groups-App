package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MyGroupsController {

    @Autowired
    private UserService userService;

    @GetMapping("/myGroups")
    public String showMyGroups(@RequestParam Long userId, Model model) {
        User user = userService.getById(userId);

        List<Group> myGroups = user.getMemberships()
                                   .stream()
                                   .map(Membership::getGroup)
                                   .collect(Collectors.toList());

        model.addAttribute("myGroups", myGroups);
        model.addAttribute("user", user); 
        return "my-groups";
    }
}
