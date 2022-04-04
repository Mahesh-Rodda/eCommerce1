package com.cjss.eCommerce1.entity;

import javax.persistence.*;

@Entity
@Table(name = "SKU_PRICE")
public class PriceEntity {
    @Id
    @Column(name = "SKU_CODE")
    private Integer skuCode;
    @Column(name = "SKU_PRICE")
    private double price;


    public PriceEntity() {
    }

    public PriceEntity(Integer skuCode, double price) {
        this.skuCode = skuCode;
        this.price = price;
    }

    public Integer getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(Integer skuCode) {
        this.skuCode = skuCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
