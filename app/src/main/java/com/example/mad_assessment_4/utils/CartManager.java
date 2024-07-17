package com.example.mad_assessment_4.utils;

import com.example.mad_assessment_4.data.models.CartItem;
import com.example.mad_assessment_4.data.models.Order;
import com.example.mad_assessment_4.data.models.Pizza;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItem> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Pizza pizza, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getPizza().getId() == pizza.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(pizza, quantity));
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }

    public void removeFromCart(Pizza pizza) {
        for (CartItem item : cartItems) {
            if (item.getPizza().getId() == pizza.getId()) {
                cartItems.remove(item);
                return;
            }
        }
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (CartItem item : cartItems) {
            totalPrice += item.getPizza().getPrice() * item.getQuantity();
        }
        return totalPrice;
    }


}
