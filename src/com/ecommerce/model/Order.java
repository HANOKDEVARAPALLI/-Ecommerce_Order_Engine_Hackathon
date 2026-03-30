package com.ecommerce.model;

import java.util.Map;

public class Order {

    private int orderId;
    private Map<Integer, CartItem> items;
    private double total;
    private OrderStatus status;

    public Order(int orderId, Map<Integer, CartItem> items, double total) {
        this.orderId = orderId;
        this.items = items;
        this.total = total;
        this.status = OrderStatus.CREATED;
    }

    public int getOrderId() { return orderId; }
    public Map<Integer, CartItem> getItems() { return items; }
    public double getTotal() { return total; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}