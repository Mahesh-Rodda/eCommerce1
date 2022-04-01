package com.cjss.eCommerce1.entity;

import javax.persistence.*;

@Entity
@Table(name = "PACKING_DEPT")
public class PackingEntity {
    @Id
    private String orderCode;
    private String status;
    public PackingEntity() {
    }

    public PackingEntity(String orderCode, String status) {
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
