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

import lombok.Data;
import com.src.microservice.testing.model.Project;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "system")
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long system_id;

    @Column(nullable = false)
    private String system_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false, foreignKey = @ForeignKey(name = "fk_project"))
    private Project project;
    
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

	public void setSystem_id(String system_id) {
		this.system_name = system_id;
	}
	
	public void setSystem_name(String system_name) {
		this.system_name = system_name;
	}
    
	/*public void setProject(Project project){
		this.project = project;
	}*/
	
	 /*public void addProject(Project project) {
	        this.project = project;
	        project.getSystems().add(this);
	 }*/
}