// Order.java
package com.example.mad_assessment_4.data.models;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private String date;
    private double totalPrice;
    private List<CartItem> cartItems;

    // Constructor, getters and setters
    public Order(int orderId, int userId, String date, double totalPrice, List<CartItem> cartItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.totalPrice = totalPrice;
        this.cartItems = cartItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
