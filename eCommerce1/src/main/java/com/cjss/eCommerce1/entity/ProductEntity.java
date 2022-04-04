package com.cjss.eCommerce1.entity;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
@Validated
@Entity
@Table(name = "PRODUCTS_TABLE")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "native")
    @Column(name = "PRODUCT_CODE")
    private Integer productCode;
    @Column(name = "PRODUCT_NAME")
    @NotNull
    private String productName;
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.DETACH)
    private List<SKUEntity> skuEntities;

    public ProductEntity() {
    }

    public ProductEntity(Integer productId, String productName, String description) {
        this.productCode = productId;
        this.productName = productName;
        this.description = description;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
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
