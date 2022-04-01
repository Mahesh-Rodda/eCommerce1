package com.cjss.eCommerce1.model;

public class OrderModel {
    private String orderCode;
    private String productName;
    private String skuCode;
    private String skuSize;
    private Integer quantity;
    private double price;
    private String orderStatus;

    public OrderModel(String orderCode, String productName, String skuCode, String skuSize, Integer quantity, double price, String orderStatus) {
        this.orderCode = orderCode;
        this.productName = productName;
        this.skuCode = skuCode;
        this.skuSize = skuSize;
        this.quantity = quantity;
        this.price = price;
        this.orderStatus = orderStatus;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuSize() {
        return skuSize;
    }

    public void setSkuSize(String skuSize) {
        this.skuSize = skuSize;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
