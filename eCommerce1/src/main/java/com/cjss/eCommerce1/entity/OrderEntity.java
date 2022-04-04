package com.cjss.eCommerce1.entity;

import javax.persistence.*;

@Entity
@Table(name = "TABLE_ORD")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderCode;
    private Integer quantity;
    private String orderStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    private SKUEntity skuEntity;

    public OrderEntity() {
    }

    public OrderEntity(Integer quantity, String orderStatus, SKUEntity skuEntity) {
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.skuEntity = skuEntity;
    }

    public Integer getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public SKUEntity getSkuEntity() {
        return skuEntity;
    }

    public void setSkuEntity(SKUEntity skuEntity) {
        this.skuEntity = skuEntity;
    }
}
