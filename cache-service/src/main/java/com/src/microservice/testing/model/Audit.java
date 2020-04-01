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
import com.src.microservice.testing.model.Interface;
import com.src.microservice.testing.model.Project;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long audit_id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interface_id", nullable = false, foreignKey = @ForeignKey(name = "fk_interface"))
    private Interface interface1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_system_id", nullable = false, foreignKey = @ForeignKey(name = "fk_source_system"))
    private System srcSystem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_system_id", nullable = false, foreignKey = @ForeignKey(name = "fk_target_system"))
    private System tgtSystem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false, foreignKey = @ForeignKey(name = "fk_project"))
    private Project project;
    
    @Column(nullable = false)
    private String source_reference_id;
    
    @Column(nullable = false)
    private LocalDateTime created_ts;

    @Column(nullable = false)
    private LocalDateTime updated_ts;
    
    @Column(nullable = false)
    private String status;

    @PrePersist
    public void onPrePersist() {
    	created_ts = updated_ts = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
    	updated_ts = LocalDateTime.now();
    }

	/*public void setStatus(String status) {
		this.status = status;
	}*/
    public void setSource_reference_id(String source_reference_id) {
		this.source_reference_id = source_reference_id;
	}
}