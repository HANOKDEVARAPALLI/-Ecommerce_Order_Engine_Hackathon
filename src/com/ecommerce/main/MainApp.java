package com.ecommerce.main;

import com.ecommerce.model.*;
import com.ecommerce.service.*;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ProductService productService = new ProductService();
        CartService cartService = new CartService();
        OrderService orderService = new OrderService();

        int userId = 1;

        while (true) {

            System.out.println("\n===== MENU =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Add to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
            System.out.println("7. Cancel Order");
            System.out.println("8. Low Stock Alert");
            System.out.println("0. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Name: ");
                    String name = sc.next();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    System.out.print("Enter Stock: ");
                    int stock = sc.nextInt();

                    productService.addProduct(
                            new Product(id, name, price, stock)
                    );
                    break;

                case 2:
                    productService.viewProducts();
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    int pid = sc.nextInt();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    Product p = productService.getProduct(pid);

                    if (p == null) {
                        System.out.println("❌ Product not found");
                    } else {
                        cartService.addToCart(userId, p, qty);
                    }
                    break;

                case 4:
                    cartService.viewCart(userId);
                    break;

                case 5:
                    orderService.placeOrder(userId, cartService.getCart(userId));
                    break;

                case 6:
                    orderService.viewOrders();
                    break;
                case 7:
                    System.out.print("Enter Order ID: ");
                    int oid = sc.nextInt();
                    orderService.cancelOrder(oid);
                    break;
                case 8:
                    productService.lowStockAlert();
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}