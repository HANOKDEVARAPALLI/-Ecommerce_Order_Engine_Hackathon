package com.ecommerce.service;

import com.ecommerce.model.*;
import java.util.HashMap;
import java.util.Map;

public class CartService {

    private Map<Integer, Cart> userCarts = new HashMap<>();

    public Cart getCart(int userId) {
        return userCarts.computeIfAbsent(userId, k -> new Cart());
    }

    // Add to Cart
    public void addToCart(int userId, Product product, int qty) {

        if (product.getStock() < qty) {
            System.out.println("❌ Not enough stock");
            return;
        }

        Cart cart = getCart(userId);
        Map<Integer, CartItem> items = cart.getItems();

        CartItem item = items.get(product.getId());

        if (item == null) {
            items.put(product.getId(), new CartItem(product, qty));
        } else {
            item.setQuantity(item.getQuantity() + qty);
        }

        // 🔥 STOCK RESERVATION
        product.setStock(product.getStock() - qty);

        System.out.println("✅ Added to cart & stock reserved");
    }

    // View Cart
    public void viewCart(int userId) {
        Cart cart = getCart(userId);

        if (cart.getItems().isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }

        for (CartItem item : cart.getItems().values()) {
            System.out.println(
                item.getProduct().getName() +
                " x " + item.getQuantity()
            );
        }
    }
}