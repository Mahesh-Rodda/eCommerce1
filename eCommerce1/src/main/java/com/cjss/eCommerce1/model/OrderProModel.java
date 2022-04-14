package com.cjss.eCommerce1.model;

public class OrderProModel {
    private Integer SkuCode;
    private Integer quantity;

    public OrderProModel(Integer skuCode, Integer quantity) {
        SkuCode = skuCode;
        this.quantity = quantity;
    }

    public Integer getSkuCode() {
        return SkuCode;
    }

    public void setSkuCode(Integer skuCode) {
        SkuCode = skuCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
