package com.userDatabase.userDatabase.model;
import com.userDatabase.userDatabase.model.Group;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name; 
	private String email;
	
	@ManyToMany
    @JoinTable(
        name = "membership",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
	 
	private Set<Group> groups = new HashSet<>();
	 
	public Set<Group> getGroups() {
	    return groups;
	}

	public void setGroups(Set<Group> groups) {
	    this.groups = groups;
	}
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
}
