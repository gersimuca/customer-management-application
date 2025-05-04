package com.gersimuca.erp.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AuditedEntity {
  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private OffsetDateTime createdAt;

  @CreatedBy
  @Column(name = "created_user_id", nullable = false, updatable = false)
  private Long createdBy;

  @UpdateTimestamp
  @Column(name = "last_updated_at", nullable = false)
  private OffsetDateTime lastUpdatedAt;

  @LastModifiedBy
  @Column(name = "last_updated_user_id", nullable = false)
  private Long lastModifiedBy;
}
