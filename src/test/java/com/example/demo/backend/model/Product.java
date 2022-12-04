package com.example.demo.backend.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "product")

@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_name", unique = true)
    private String productName;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

}

