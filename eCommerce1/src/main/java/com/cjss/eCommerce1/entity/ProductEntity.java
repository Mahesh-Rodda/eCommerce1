package com.cjss.eCommerce1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "DESCRIPTION")
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "productEntity")
    private List<SKUEntity> skuEntities;

    public ProductEntity() {
    }

    public ProductEntity(String productId, String productName, String description) {
        this.productCode = productId;
        this.productName = productName;
        this.description = description;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SKUEntity> getSkuEntities() {
        return skuEntities;
    }

    public void setSkuEntities(List<SKUEntity> skuEntities) {
        this.skuEntities = skuEntities;
    }
}
