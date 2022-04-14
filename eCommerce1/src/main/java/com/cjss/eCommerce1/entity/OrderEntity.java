package com.cjss.eCommerce1.entity;

import javax.persistence.*;
import java.util.List;

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
    @OneToMany(mappedBy = "orderEntity")
    private List<OrderProducts> orderProducts;
    @ManyToOne(cascade = CascadeType.DETACH)
    private UserEntity userEntity;

    public OrderEntity() {
    }

    public OrderEntity(Integer quantity, String orderStatus, SKUEntity skuEntity, UserEntity userEntity) {
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.skuEntity = skuEntity;
        this.userEntity = userEntity;
    }

    public OrderEntity(String orderStatus) {
        this.orderStatus = orderStatus;
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

    public List<OrderProducts> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProducts> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
