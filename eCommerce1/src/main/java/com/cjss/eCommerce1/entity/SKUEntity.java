package com.cjss.eCommerce1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SKU")
public class SKUEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "native")
    @Column(name = "SKU_CODE")
    private String skuCode;
    @Column(name = "SKU_SIZE")
    private String skuSize;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "PRODUCT_COE")
    private ProductEntity productEntity;
    @JsonManagedReference
    @OneToMany(mappedBy = "skuEntity", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CartEntity> cartEntity;
    @JsonIgnore
    @OneToMany(mappedBy = "skuEntity", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderEntity> orderEntities;

    public SKUEntity() {
    }

    public SKUEntity(String skuCode, String size, ProductEntity productEntity) {
        this.skuCode = skuCode;
        this.skuSize = size;
        this.productEntity = productEntity;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuSize() {
        return skuSize;
    }

    public void setSkuSize(String skuSize) {
        this.skuSize = skuSize;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public List<CartEntity> getCartEntity() {return cartEntity;}

    public List<OrderEntity> getOrderEntities() {return orderEntities;}
}
