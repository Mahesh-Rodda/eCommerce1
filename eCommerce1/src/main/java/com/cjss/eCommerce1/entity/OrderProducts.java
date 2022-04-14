package com.cjss.eCommerce1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ordered_Items")
public class OrderProducts {
    @Id
    private Integer SkuCode;
    private Integer quantity;
    @ManyToOne
    private OrderEntity orderEntity;

    public Integer getSkuCode() {
        return SkuCode;
    }

    public void setSkuCode(Integer skuCode) {
        SkuCode = skuCode;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
