package com.gersimuca.erp.unreleased.lead;

import com.gersimuca.erp.unreleased.admin.AdminEntity;
import com.gersimuca.erp.unreleased.company.CompanyEntity;
import com.gersimuca.erp.unreleased.media.MediaEntity;
import com.gersimuca.erp.unreleased.offer.OfferEntity;
import com.gersimuca.erp.unreleased.people.PeopleEntity;
import com.gersimuca.erp.unreleased.product.ProductEntity;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "lead")
public class LeadEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Boolean removed = false;

  @Column(nullable = false)
  private Boolean enabled = true;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private LeadType type;

  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id")
  private CompanyEntity company;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "people_id")
  private PeopleEntity people;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "lead_product",
      joinColumns = @JoinColumn(name = "lead_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id"))
  private List<ProductEntity> interestedIn;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "lead_offer",
      joinColumns = @JoinColumn(name = "lead_id"),
      inverseJoinColumns = @JoinColumn(name = "offer_id"))
  private List<OfferEntity> offer;

  @Column(nullable = false)
  private Boolean converted = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "created_by")
  private AdminEntity createdBy;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assigned_id")
  private AdminEntity assigned;

  private Double subTotal;
  private Double taxTotal;
  private Double total;
  private Double discount;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "lead")
  private List<MediaEntity> images;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "lead")
  private List<MediaEntity> files;

  private String category;
  private String status;
  private String notes;
  private String source;

  @Column(nullable = false)
  private Boolean approved = false;

  @ElementCollection
  @CollectionTable(name = "lead_tags", joinColumns = @JoinColumn(name = "lead_id"))
  @Column(name = "tag")
  private List<String> tags;

  @Temporal(TemporalType.TIMESTAMP)
  private Date created = new Date();

  @Temporal(TemporalType.TIMESTAMP)
  private Date updated = new Date();
}
