package com.gersimuca.erp.feature.customer;

import com.gersimuca.erp.common.AuditedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity extends AuditedEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "customer_id")
  private Long customerId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "company")
  private String company;

  @Column(name = "address_line")
  private String addressLine;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "postal_code")
  private String postalCode;

  @Column(name = "country")
  private String country;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 20)
  private Status status;

  @Column(name = "owner_id")
  private Long ownerId;

  @Column(name = "notes", columnDefinition = "text")
  private String notes;
}
