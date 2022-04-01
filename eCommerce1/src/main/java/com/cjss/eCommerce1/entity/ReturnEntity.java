package com.cjss.eCommerce1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RETURN")
public class ReturnEntity {
    @Id
    private String orderCode;
    private String status;

    public ReturnEntity() {
    }

    public ReturnEntity(String orderCode, String status) {
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
