package com.cjss.eCommerce1.model;

import java.util.List;

public class OrderModel {
    private Integer orderCode;
    private String productName;
    private Integer skuCode;
    private String skuSize;
    private Integer quantity;
    private double price;
    private String orderStatus;
    private List<OrderProModel> orderProModels;

    public OrderModel() {
    }

    public OrderModel(Integer orderCode, String productName, Integer skuCode, String skuSize, Integer quantity, double price, String orderStatus,List<OrderProModel> orderProModels) {
        this.orderProModels = orderProModels;
        this.orderCode = orderCode;
        this.productName = productName;
        this.skuCode = skuCode;
        this.skuSize = skuSize;
        this.quantity = quantity;
        this.price = price;
        this.orderStatus = orderStatus;
//        this.orderProModels = orderProModels;
    }

    public Integer getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Integer orderCode) {
        this.orderCode = orderCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(Integer skuCode) {
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

    public List<OrderProModel> getOrderProModels() {
        return orderProModels;
    }

    public void setOrderProModels(List<OrderProModel> orderProModels) {
        this.orderProModels = orderProModels;
    }
}
