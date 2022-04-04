package com.cjss.eCommerce1.entity;

import javax.persistence.*;

@Entity
@Table(name = "PACKING_DEPT")
public class PackingEntity {
    @Id
    private Integer orderCode;
    private String status;
    public PackingEntity() {
    }

    public PackingEntity(Integer orderCode, String status) {
        this.orderCode = orderCode;
        this.status = status;
    }

    public Integer getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
