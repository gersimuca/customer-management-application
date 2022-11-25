package com.example.demo.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_request;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "status")
    private String status = "pending".toUpperCase();

    public Long getId_request() {
        return id_request;
    }

    public void setId_request(Long id_request) {
        this.id_request = id_request;
    }

    public String getProduct() {
        return productName.toUpperCase();
    }

    public void setProduct(String product) {
        this.productName = product.toUpperCase();
    }

    public Integer getQuality() {
        return quantity;
    }

    public void setQuality(Integer quality) {
        this.quantity = quality;
    }

    public String getStatus() {
        return status.toUpperCase();
    }

    public void setStatus(String status) {
        this.status = status.toUpperCase();
    }
}
