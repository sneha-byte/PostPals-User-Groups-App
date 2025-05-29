package com.userDatabase.userDatabase.repository;

import com.userDatabase.userDatabase.model.Membership;
import com.userDatabase.userDatabase.model.User;
import com.userDatabase.userDatabase.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    List<Membership> findByUser(User user);
    List<Membership> findByGroup(Group group);
    Optional<Membership> findByUserAndGroup(User user, Group group);
}
