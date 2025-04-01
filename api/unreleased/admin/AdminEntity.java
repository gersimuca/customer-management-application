package com.gersimuca.erp.unreleased.admin;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class AdminEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Boolean removed = false;

  @Column(nullable = false)
  private Boolean enabled = false;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String name;

  private String surname;

  private String photo;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created = new Date();

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AdminRole role = AdminRole.OWNER;
}
