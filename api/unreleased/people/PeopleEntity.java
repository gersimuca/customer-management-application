package com.gersimuca.erp.unreleased.people;

import com.gersimuca.erp.unreleased.company.CompanyEntity;
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
@Table(name = "people")
public class PeopleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private boolean removed = false;
  private boolean enabled = true;

  @Column(nullable = false)
  private String firstname;

  @Column(nullable = false)
  private String lastname;

  private boolean isClient = false;

  @ManyToOne
  @JoinColumn(name = "company_id")
  private CompanyEntity company;

  private String bio;
  private String idCardNumber;
  private String idCardType;
  private String securitySocialNbr;
  private String taxNumber;
  private Date birthday;
  private String birthplace;

  @Enumerated(EnumType.STRING)
  private PeopleGender gender;

  private String photo;
  private String bankName;
  private String bankIban;
  private String bankSwift;
  private String bankNumber;
  private String bankRouting;

  @ElementCollection private List<CustomField> customField;

  @Embedded private Location location;

  private String address;
  private String city;
  private String state;
  private Integer postalCode;
  private String country;
  private String phone;

  @ElementCollection private List<String> otherPhone;

  private String email;

  @ElementCollection private List<String> otherEmail;

  @Embedded private SocialMedia socialMedia;

  private String website;

  @ElementCollection private List<Image> images;

  @ElementCollection private List<File> files;

  private String notes;
  private String category;
  private String status;
  private boolean approved;
  private Boolean verified;

  @ElementCollection private List<String> tags;

  @CreationTimestamp private Date created;

  @UpdateTimestamp private Date updated;

  private boolean isPublic = false;

  @PrePersist
  @PreUpdate
  private void trimFields() {
    if (firstname != null) firstname = firstname.trim();
    if (lastname != null) lastname = lastname.trim();
    if (idCardNumber != null) idCardNumber = idCardNumber.trim();
    if (bankName != null) bankName = bankName.trim();
    if (bankIban != null) bankIban = bankIban.trim();
    if (bankSwift != null) bankSwift = bankSwift.trim();
    if (bankNumber != null) bankNumber = bankNumber.trim();
    if (bankRouting != null) bankRouting = bankRouting.trim();
    if (country != null) country = country.trim();
    if (phone != null) phone = phone.trim();
    if (email != null) email = email.trim().toLowerCase();
    if (website != null) website = website.trim().toLowerCase();
  }
}
