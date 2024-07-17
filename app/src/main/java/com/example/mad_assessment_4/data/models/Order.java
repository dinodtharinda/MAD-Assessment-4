package com.example.mad_assessment_4.data.models;

import java.util.Date;
import java.util.List;

public class Order {
    private int userId;
    private Date orderDate;
    private List<CartItem> cartItems;
    private double totalPrice;

    public Order(int userId, Date orderDate, List<CartItem> cartItems, double totalPrice) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
