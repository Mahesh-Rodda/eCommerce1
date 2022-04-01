package com.cjss.eCommerce1.entity;

import javax.persistence.*;

@Entity
@Table(name = "SKU_PRICE")
public class PriceEntity {
    @Id
    @Column(name = "SKU_CODE")
    private String skuCode;
    @Column(name = "SKU_PRICE")
    private double price;


    public PriceEntity() {
    }

    public PriceEntity(String skuCode, double price) {
        this.skuCode = skuCode;
        this.price = price;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
