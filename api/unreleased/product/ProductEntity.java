package com.gersimuca.erp.unreleased.product;

import com.gersimuca.erp.unreleased.productcategory.ProductCategoryEntity;
import com.gersimuca.erp.unreleased.shared.CustomField;
import com.gersimuca.erp.unreleased.shared.File;
import com.gersimuca.erp.unreleased.shared.Image;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@Table(name = "product")
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private boolean removed = false;
  private boolean enabled = true;

  @ManyToOne
  @JoinColumn(name = "product_category_id", nullable = false)
  private ProductCategoryEntity productCategory;

  //    @ManyToMany
  //    @JoinTable(
  //            name = "product_supplier",
  //            joinColumns = @JoinColumn(name = "product_id"),
  //            inverseJoinColumns = @JoinColumn(name = "supplier_id")
  //    )
  //    private List<Supplier> suppliers;

  @Column(nullable = false)
  private String name;

  private String description;

  private Integer number;

  private String title;

  @ElementCollection private List<String> tags;

  private String headerImage;

  private String photo;

  @ElementCollection private List<Image> images;

  @ElementCollection private List<File> files;

  private Double priceBeforeTax;

  private Double taxRate = 0.0;

  @Column(nullable = false)
  private Double price;

  @Column(nullable = false, length = 255)
  private String currency = "NA";

  @ElementCollection private List<CustomField> customField;

  @CreationTimestamp private Date created;

  @UpdateTimestamp private Date updated;

  private boolean isPublic = true;

  @PrePersist
  @PreUpdate
  private void trimFields() {
    if (name != null) name = name.trim();
    if (currency != null) currency = currency.trim().toUpperCase();
  }
}
