package com.cjss.eCommerce1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRODUCT_SKU")
public class SKUEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "native")
    @Column(name = "SKU_CODE")
    private Integer skuCode;
    @Column(name = "SKU_SIZE")
    private String skuSize;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "PRODUCT_CODE")
    private ProductEntity productEntity;
    @OneToMany(mappedBy = "skuEntity",cascade = CascadeType.DETACH)
    private List<CartEntity> cartEntity;
    @OneToMany(mappedBy = "skuEntity", cascade = CascadeType.DETACH)
    private List<OrderEntity> orderEntities;

    public SKUEntity() {
    }

    public SKUEntity(Integer skuCode, String size, ProductEntity productEntity) {
        this.skuCode = skuCode;
        this.skuSize = size;
        this.productEntity = productEntity;
    }

    public Integer getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(Integer skuCode) {
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
