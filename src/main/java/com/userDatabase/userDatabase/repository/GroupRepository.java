package com.userDatabase.userDatabase.repository;

import com.userDatabase.userDatabase.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
