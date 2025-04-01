package com.gersimuca.erp.unreleased.offer;

import com.gersimuca.erp.unreleased.admin.AdminEntity;
import com.gersimuca.erp.unreleased.item.ItemEntity;
import com.gersimuca.erp.unreleased.lead.LeadEntity;
import com.gersimuca.erp.unreleased.media.MediaEntity;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "offer")
public class OfferEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Boolean removed = false;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "created_by", nullable = false)
  private AdminEntity createdBy;

  @Column(nullable = false)
  private Boolean converted = false;

  @Column(nullable = false)
  private Integer number;

  @Column(nullable = false)
  private Integer year;

  private String content;

  @Column(nullable = false)
  @Temporal(TemporalType.DATE)
  private Date date;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lead_id", nullable = false)
  private LeadEntity lead;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "offer")
  private List<ItemEntity> items;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OfferCurrency currency = OfferCurrency.NA;

  private Double taxRate;
  private Double subTotal;
  private Double subOfferTotal;
  private Double taxTotal;
  private Double total;
  private Double discount = 0.0;
  private String notes;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OfferStatus status = OfferStatus.DRAFT;

  @Column(nullable = false)
  private Boolean approved = false;

  @Column(nullable = false)
  private Boolean isExpired = false;

  private String pdf;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "offer")
  private List<MediaEntity> files;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  private Date created = new Date();

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false)
  private Date updated = new Date();
}
