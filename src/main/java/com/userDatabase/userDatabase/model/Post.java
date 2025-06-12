package com.userDatabase.userDatabase.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    // Add column in post table for the user that created the post
    // Many to one because one user can have many posts 
    // Maps author field in the Post to user_id in database
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

	// Getters and Setters
    public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

    public Long getId() { 
    	return id; 
    }
    
    public void setId(Long id) { 
    	this.id = id; 
    }

    public String getContent() { 
    	return content; 
    }
    
    public void setContent(String content) { 
    	this.content = content; 
    }

    public LocalDateTime getCreatedAt() { 
    	return createdAt;
    }

    public User getAuthor() { 
    	return author; 
    }
    
    public void setAuthor(User author) { 
    	this.author = author; 
    }

    public Group getGroup() { 
    	return group;
    }
    
    public void setGroup(Group group) { 
    	this.group = group; 
    }
}
