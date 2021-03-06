package com.cjss.eCommerce1.entity;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_CRT")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartCode;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne(cascade = CascadeType.DETACH)
    private SKUEntity skuEntity;
    @ManyToOne(cascade = CascadeType.DETACH)
    private UserEntity userEntity;

    public CartEntity() {
    }

    public CartEntity(Integer quantity, SKUEntity skuEntity, UserEntity userEntity) {
        this.quantity = quantity;
        this.skuEntity = skuEntity;
        this.userEntity = userEntity;
    }

    public Integer getCartCode() {
        return cartCode;
    }

    public void setCartCode(Integer cartCode) {
        this.cartCode = cartCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public SKUEntity getSkuEntity() {
        return skuEntity;
    }

    public void setSkuEntity(SKUEntity skuEntity) {
        this.skuEntity = skuEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "cartCode='" + cartCode + '\'' +
                ", quantity=" + quantity +
                ", skuEntity=" + skuEntity +
                '}';
    }
}
