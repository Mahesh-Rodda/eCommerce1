package com.cjss.eCommerce1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RETURN_TABLE")
public class ReturnEntity {
    @Id
    private Integer orderCode;
    private String status;

    public ReturnEntity() {
    }

    public ReturnEntity(Integer orderCode, String status) {
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
