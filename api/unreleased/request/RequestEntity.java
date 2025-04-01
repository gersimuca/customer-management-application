package com.gersimuca.erp.unreleased.request;

import com.gersimuca.erp.feature.user.UserEntity;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;

@Entity
@Table(name = "requests")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestEntity {
  @Id
  @SequenceGenerator(name = "primary_key_seq", sequenceName = "primary_key_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "primary_key_seq")
  @Column(name = "request_id")
  private UUID requestId;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private RequestStatus status;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;
}
