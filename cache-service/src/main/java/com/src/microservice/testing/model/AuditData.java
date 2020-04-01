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

import com.src.microservice.testing.model.Audit;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "audit_data")
public class AuditData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long audit_data_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "audit_id", nullable = false, foreignKey = @ForeignKey(name = "fk_audit"))
    private Audit audit;
    
    @Column(nullable = false)
    private String source_reference_id;
    
    @Column(columnDefinition="TEXT")
    private String request_data;
    
    @Column(columnDefinition="TEXT")
    private String response_data;
    
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
}