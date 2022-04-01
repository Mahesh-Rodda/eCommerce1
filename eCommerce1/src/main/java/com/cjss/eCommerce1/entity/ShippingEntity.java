package com.cjss.eCommerce1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHIPPING_DEPT")
public class ShippingEntity {
    @Id
    private String orderCode;
    private String status;

    public ShippingEntity() {
    }

    public ShippingEntity(String orderCode, String status) {
        this.orderCode = orderCode;
        this.status = status;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
