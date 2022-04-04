package com.cjss.eCommerce1.model;

public class SKUModel {
    private Integer skuCode;
    private String skuSize;
    private double skuPrice;
    private Integer productCode;
    private Integer availableQuantity;

    public SKUModel(Integer skuCode, String skuSize, double skuPrice, Integer quantity) {
        this.skuCode = skuCode;
        this.skuSize = skuSize;
        this.skuPrice = skuPrice;
        this.availableQuantity =quantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public double getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(double skuPrice) {
        this.skuPrice = skuPrice;
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

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    @Override
    public String toString() {
        return "SKUModel{" +
                "skuCode='" + skuCode + '\'' +
                ", skuSize='" + skuSize + '\'' +
                ", skuPrice=" + skuPrice +
                ", productCode='" + productCode + '\'' +
                ", availableQuantity=" + availableQuantity +
                '}';
    }
}
