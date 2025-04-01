package com.gersimuca.erp.unreleased.company;

import com.gersimuca.erp.unreleased.people.PeopleEntity;
import com.gersimuca.erp.unreleased.shared.CustomField;
import com.gersimuca.erp.unreleased.shared.File;
import com.gersimuca.erp.unreleased.shared.Image;
import com.gersimuca.erp.unreleased.shared.Location;
import com.gersimuca.erp.unreleased.shared.SocialMedia;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@Table(name = "company")
public class CompanyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private boolean removed = false;
  private boolean enabled = true;

  @Column(nullable = false)
  private String name;

  @Column private String legalName;

  private boolean hasParentCompany = false;

  @ManyToOne
  @JoinColumn(name = "parent_company_id")
  private CompanyEntity parentCompany;

  private boolean isClient = false;

  @OneToMany
  @JoinColumn(name = "company_id")
  private List<PeopleEntity> peoples;

  @ManyToOne
  @JoinColumn(name = "main_contact_id")
  private PeopleEntity mainContact;

  @Column private String icon;

  @Column private String logo;

  private String imageHeader;

  @Column private String bankName;

  @Column private String bankIban;

  @Column private String bankSwift;

  @Column private String bankNumber;

  @Column private String bankRouting;

  @Column private String bankCountry;

  @Column private String companyRegNumber;

  @Column private String companyTaxNumber;

  @Column private String companyTaxId;

  @Column private String companyRegId;

  private String securitySocialNbr;

  @ElementCollection private List<CustomField> customField;

  @Embedded private Location location;

  private String address;
  private String city;
  private String state;
  private Integer postalCode;

  @Column private String country;

  @Column private String phone;

  @ElementCollection
  @CollectionTable(name = "company_other_phone", joinColumns = @JoinColumn(name = "company_id"))
  @Column(name = "phone")
  private List<String> otherPhone;

  @Column private String fax;

  @Column private String email;

  @ElementCollection
  @CollectionTable(name = "company_other_email", joinColumns = @JoinColumn(name = "company_id"))
  @Column(name = "email")
  private List<String> otherEmail;

  @Column private String website;

  @Embedded private SocialMedia socialMedia;

  @ElementCollection
  @CollectionTable(name = "company_image", joinColumns = @JoinColumn(name = "company_id"))
  @Column(name = "image")
  private List<Image> images;

  @ElementCollection
  @CollectionTable(name = "company_file", joinColumns = @JoinColumn(name = "company_id"))
  @Column(name = "file")
  private List<File> files;

  private String category;
  private boolean approved = true;
  private Boolean verified;
  private String notes;

  @ElementCollection
  @CollectionTable(name = "company_tag", joinColumns = @JoinColumn(name = "company_id"))
  @Column(name = "tag")
  private List<String> tags;

  @CreationTimestamp private Date created;

  @UpdateTimestamp private Date updated;

  private boolean isPublic = false;

  @PrePersist
  @PreUpdate
  private void trimFields() {
    if (name != null) name = name.trim();
    if (legalName != null) legalName = legalName.trim();
    if (icon != null) icon = icon.trim();
    if (logo != null) logo = logo.trim();
    if (bankName != null) bankName = bankName.trim();
    if (bankIban != null) bankIban = bankIban.trim();
    if (bankSwift != null) bankSwift = bankSwift.trim();
    if (bankNumber != null) bankNumber = bankNumber.trim();
    if (bankRouting != null) bankRouting = bankRouting.trim();
    if (bankCountry != null) bankCountry = bankCountry.trim();
    if (companyRegNumber != null) companyRegNumber = companyRegNumber.trim();
    if (companyTaxNumber != null) companyTaxNumber = companyTaxNumber.trim();
    if (companyTaxId != null) companyTaxId = companyTaxId.trim();
    if (companyRegId != null) companyRegId = companyRegId.trim();
    if (country != null) country = country.trim();
    if (phone != null) phone = phone.trim();
    if (fax != null) fax = fax.trim();
    if (email != null) email = email.trim().toLowerCase();
    if (website != null) website = website.trim().toLowerCase();
  }
}
