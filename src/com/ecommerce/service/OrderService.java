package com.ecommerce.service;

import com.ecommerce.model.*;
import java.util.*;

public class OrderService {

    private Map<Integer, Order> orders = new HashMap<>();
    private int orderCounter = 1;

    private PaymentService paymentService = new PaymentService();

    public void placeOrder(int userId, Cart cart) {

        if (cart.getItems().isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        // Step 1: Calculate total
        double total = 0;
        for (CartItem item : cart.getItems().values()) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }

        // Step 2: Create Order
        Order order = new Order(orderCounter++, new HashMap<>(cart.getItems()), total);
        order.setStatus(OrderStatus.PENDING_PAYMENT);

        System.out.println("🧾 Order Created. Total: ₹" + total);
     // Apply discount
        if (total > 1000) {
            total = total * 0.9;
            System.out.println("🎉 10% discount applied");
        }
        // Step 3: Payment
        boolean paymentSuccess = paymentService.processPayment(total);

        if (paymentSuccess) {
            order.setStatus(OrderStatus.PAID);
            orders.put(order.getOrderId(), order);

            cart.clearCart(); // clear cart

            System.out.println("✅ Order placed successfully");
        } else {
            // 🔥 ROLLBACK
            order.setStatus(OrderStatus.FAILED);

            for (CartItem item : cart.getItems().values()) {
                Product p = item.getProduct();
                p.setStock(p.getStock() + item.getQuantity()); // restore stock
            }

            System.out.println("⚠️ Order failed. Stock restored.");
        }
    }

    public void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found");
            return;
        }

        for (Order o : orders.values()) {
            System.out.println("Order ID: " + o.getOrderId() +
                    " | Status: " + o.getStatus() +
                    " | Total: ₹" + o.getTotal());
        }
    }
    public void cancelOrder(int orderId) {

        Order order = orders.get(orderId);

        if (order == null) {
            System.out.println("❌ Order not found");
            return;
        }

        if (order.getStatus() == OrderStatus.CANCELLED) {
            System.out.println("❌ Already cancelled");
            return;
        }
    

        // restore stock
        for (CartItem item : order.getItems().values()) {
            Product p = item.getProduct();
            p.setStock(p.getStock() + item.getQuantity());
        }

        order.setStatus(OrderStatus.CANCELLED);

        System.out.println("✅ Order cancelled & stock restored");
    }
}