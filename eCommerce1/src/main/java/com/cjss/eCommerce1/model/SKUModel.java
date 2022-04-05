package com.cjss.eCommerce1.model;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
@Validated
public class SKUModel {
    private Integer skuCode;
    @Pattern(regexp = "^(XS|S|M|L|XL|XXL|XXXL)$" , message = "It should be like : XS/S/M/L/XL/XXL/XXXL")
    private String skuSize;
    @Min(value = 0)
    private double skuPrice;
    private Integer productCode;
    @Min(value = 0)
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
