package com.example.demo.backend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "product_transaction")
public class TransactionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    @Column(name = "payment_status", nullable = false)
    private boolean paymentStatus;

    private int quantity;

    @Column(name = "shipping_fee", nullable = false)
    private Double shippingFee;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "arrival_date", nullable = false)
    private LocalDate arrivalDate;

    @Column(name = "payment_currency", nullable = false)
    private String paymentCurrency;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public boolean getPaymentStatus() {
        return paymentStatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }

    @Override
    public String toString() {
        return "TransactionProduct{" +
                "transactionId=" + transactionId +
                ", paymentStatus=" + paymentStatus +
                ", quantity=" + quantity +
                ", shippingFee=" + shippingFee +
                ", totalPrice=" + totalPrice +
                ", arrivalDate=" + arrivalDate +
                ", paymentCurrency='" + paymentCurrency + '\'' +
                ", product=" + product +
                '}';
    }
}