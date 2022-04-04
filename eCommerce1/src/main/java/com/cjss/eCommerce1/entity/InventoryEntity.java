package com.cjss.eCommerce1.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INVENTORY")
public class InventoryEntity {
    @Id
    private Integer skuCode;
    private Integer quantity;

    public InventoryEntity() {

    }

    public InventoryEntity(Integer skuCode, Integer quantity) {
        this.skuCode = skuCode;
        this.quantity = quantity;
    }

    public Integer getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(Integer skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
