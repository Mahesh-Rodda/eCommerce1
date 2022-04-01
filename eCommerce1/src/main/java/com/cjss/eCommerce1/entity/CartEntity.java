package com.cjss.eCommerce1.entity;

import javax.persistence.*;

@Entity
@Table(name = "CART")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cartCode;
    private Integer quantity;
    @ManyToOne(cascade = CascadeType.DETACH)
    private SKUEntity skuEntity;

    public CartEntity() {
    }

    public CartEntity(Integer quantity, SKUEntity skuEntity) {
        this.quantity = quantity;
        this.skuEntity = skuEntity;
    }

    public String getCartCode() {
        return cartCode;
    }

    public void setCartCode(String cartCode) {
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

    @Override
    public String toString() {
        return "CartEntity{" +
                "cartCode='" + cartCode + '\'' +
                ", quantity=" + quantity +
                ", skuEntity=" + skuEntity +
                '}';
    }
}
