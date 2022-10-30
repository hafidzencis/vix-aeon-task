package com.miniproject.aeon.domain.common;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public class BaseDao {

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;


    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;


    @PrePersist
    void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

    @PreRemove
    void onDelete(){
        this.deletedAt = LocalDateTime.now();
    }
}
