// CartItem.java
package com.example.mad_assessment_4.data.models;

public class CartItem {
    private Pizza pizza;
    private int quantity;

    public CartItem(Pizza pizza, int quantity) {
        this.pizza = pizza;
        this.quantity = quantity;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
