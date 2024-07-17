package com.example.mad_assessment_4.utils;

import com.example.mad_assessment_4.data.models.CartItem;
import com.example.mad_assessment_4.data.models.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;
    private List<Order> orders;

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
        Order order = new Order(userId, new Date(), cartItems, totalPrice);
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }
}
