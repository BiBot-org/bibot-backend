package com.coderecipe.global.constant.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@Setter
public class BaseImmutableEntity extends BaseImmutableTimeEntity {
    @CreatedBy
    @Column(updatable = false)
    private UUID createdBy;

}