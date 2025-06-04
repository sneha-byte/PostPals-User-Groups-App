package com.userDatabase.userDatabase.controller;

import com.userDatabase.userDatabase.model.Group;
import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.repository.MembershipRepository;
import com.userDatabase.userDatabase.repository.UserRepository;
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
    private UserRepository userRepository;
    
    @Autowired
    private MembershipRepository membershipRepository;

    @GetMapping("/my-groups")
    public String viewMyGroups(@RequestParam Long userId, Model sample) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        List<Membership> memberships = membershipRepository.findByUser(user);

        sample.addAttribute("user", user);
        sample.addAttribute("memberships", memberships);
        return "my-groups";
    }

}
