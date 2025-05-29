package com.userDatabase.userDatabase.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    String creator; 

    @ManyToMany(mappedBy = "groups")
    private Set<User> members = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Set<User> getMembers() {
		return members;
	}

	public void setMembers(Set<User> members) {
		this.members = members;
	}    
    
}

