package com.cjss.eCommerce1.model;

public class CartModel {
    private String cartCode;
    private String productName;
    private String size;
    private double itemPrice;
    private double price;
    private Integer quantityAdded;
    private Integer availableQuantity;
    private double totalPrice;

    public CartModel(String cartCode, String productName, String size, double itemPrice,double price, Integer quantity, Integer availableQuantity, double totalPrice) {
        this.cartCode = cartCode;
        this.productName = productName;
        this.size = size;
        this.itemPrice = itemPrice;
        this.price = price;
        this.quantityAdded = quantity;
        this.availableQuantity = availableQuantity;
        this.totalPrice = totalPrice;
    }

    public String getCartCode() {
        return cartCode;
    }

    public void setCartCode(String cartCode) {
        this.cartCode = cartCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantityAdded() {
        return quantityAdded;
    }

    public void setQuantityAdded(Integer quantityAdded) {
        this.quantityAdded = quantityAdded;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "cartCode='" + cartCode + '\'' +
                ", productName='" + productName + '\'' +
                ", size='" + size + '\'' +
                ", itemPrice=" + itemPrice +
                ", price=" + price +
                ", quantityAdded=" + quantityAdded +
                ", availableQuantity=" + availableQuantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
