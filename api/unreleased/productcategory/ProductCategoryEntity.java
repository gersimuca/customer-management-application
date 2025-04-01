package com.gersimuca.erp.unreleased.productcategory;

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
@Table(name = "product_category")
public class ProductCategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private boolean removed = false;
  private boolean enabled = true;

  @Column(nullable = false)
  private String name;

  private String description;

  @Column(nullable = false)
  private String color;

  private boolean hasParentCategory = false;

  @ManyToOne
  @JoinColumn(name = "parent_category_id")
  private ProductCategoryEntity parentCategory;

  private String title;

  @ElementCollection private List<String> tags;

  private String icon;
  private String headerImage;
  private String photo;

  @ElementCollection private List<Image> images;

  @ElementCollection private List<File> files;

  @CreationTimestamp private Date created;

  @UpdateTimestamp private Date updated;

  private boolean isPublic = true;

  @PrePersist
  @PreUpdate
  private void trimFields() {
    if (color != null) color = color.trim().toLowerCase();
  }
}
