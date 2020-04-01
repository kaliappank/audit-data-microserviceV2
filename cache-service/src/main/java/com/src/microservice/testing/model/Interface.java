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
import com.src.microservice.testing.model.System;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "interface")
public class Interface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interface_id;

    @Column(nullable = false)
    private String interface_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id", nullable = false, foreignKey = @ForeignKey(name = "fk_system"))
    private System system;
    
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

	/*public void setName(String name) {
		this.name = name;
	}*/
}