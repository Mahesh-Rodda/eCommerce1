package com.cjss.eCommerce1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "ORDERS")
@Validated
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "native")
    @Column(name = "order_code")
    private String orderCode;
    private Integer quantity;
    @Pattern(regexp = "^(RECEIVED|PROCESSING|PACKING|SHIPPING|DELIVERED|RETURNED|RETURN INITIATED|RETURN ACCEPTED)$",message = "Invalid input")
    private String orderStatus;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    private SKUEntity skuEntity;

    public OrderEntity() {
    }

    public OrderEntity(Integer quantity, String orderStatus, SKUEntity skuEntity) {
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.skuEntity = skuEntity;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
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
