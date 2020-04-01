package com.src.microservice.testing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

//import com.src.microservice.testing.model.System;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_id;

    @Column(nullable = false)
    private String project_name;

    //@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    //private Set<System> systems = new LinkedHashSet<>();
    
    @Column(nullable = false)
    private LocalDateTime created_ts;

    @Column(nullable = false)
    private LocalDateTime updated_ts;

    @PrePersist
    public void onPrePersist() {
        created_ts = updated_ts = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        updated_ts = LocalDateTime.now();
    }

/*	public void setProject_id(String project_id) {
		this.project_ = project_id;
	}
	
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}*/
    
    
}