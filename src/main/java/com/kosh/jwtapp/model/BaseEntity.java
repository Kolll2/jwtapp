package com.kosh.jwtapp.model;

import com.kosh.jwtapp.model.enums.BaseEntityStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name="created")
    private Date created;
    @CreatedBy
    @Column(name="created_by")
    private String createdBy;
    @LastModifiedDate
    @Column(name="updated")
    private Date updated;
    @LastModifiedBy
    @Column(name="updated_by")
    private String updatedBy;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private BaseEntityStatus status;
}
