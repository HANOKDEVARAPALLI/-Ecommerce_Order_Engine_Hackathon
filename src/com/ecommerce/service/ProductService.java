package com.ecommerce.service;

import com.ecommerce.model.Product;
import java.util.HashMap;
import java.util.Map;

public class ProductService {

    private Map<Integer, Product> productMap = new HashMap<>();

    // Add Product
    public void addProduct(Product product) {
        if (productMap.containsKey(product.getId())) {
            System.out.println("❌ Product ID already exists!");
            return;
        }
        productMap.put(product.getId(), product);
        System.out.println("✅ Product added successfully");
    }

    // View Products
    public void viewProducts() {
        if (productMap.isEmpty()) {
            System.out.println("No products available");
            return;
        }

        for (Product p : productMap.values()) {
            System.out.println(p);
        }
    }

    // Get product
    public Product getProduct(int id) {
        return productMap.get(id);
    }
    public void lowStockAlert() {

        for (Product p : productMap.values()) {
            if (p.getStock() <= 5) {
                System.out.println("⚠️ Low stock: " + p.getName());
            }
        }
    }
}