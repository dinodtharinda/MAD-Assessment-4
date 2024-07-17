package com.example.mad_assessment_4.utils;

import android.util.Log;

import com.example.mad_assessment_4.data.models.CartItem;
import com.example.mad_assessment_4.data.models.Order;
import com.example.mad_assessment_4.data.models.Pizza;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class OrderManager {
    private static OrderManager instance;
    private List<Order> orders;
    private int nextOrderId = 1; // Start with orderId 1 and increment

    private OrderManager() {
        orders = new ArrayList<>();
    }

    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void placeOrder(int userId, List<CartItem> cartItems, double totalPrice) {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

        // Create the order
        Order order = new Order(nextOrderId++, userId, currentDate, totalPrice, cartItems);

        // Add order to the list
        orders.add(order);


        Log.e("Oder id length",String.valueOf(orders.size()));

        // You can optionally save the order to a database or perform other operations here

        // Clear the cart after placing the order
        CartManager.getInstance().clearCart();
    }

    public List<Order> getOrdersByUserId(int userId) {
        // Filter orders by userId
        return orders.stream()
                .filter(order -> order.getUserId() == userId)
                .collect(Collectors.toList());
    }

    public List<Order> getOrders() {
        return orders;
    }
}
