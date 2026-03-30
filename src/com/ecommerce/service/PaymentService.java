package com.ecommerce.service;

import java.util.Random;

public class PaymentService {

    public boolean processPayment(double amount) {

        System.out.println("Processing payment of ₹" + amount);

        // Random success/failure
        boolean success = new Random().nextBoolean();

        if (success) {
            System.out.println("✅ Payment Successful");
        } else {
            System.out.println("❌ Payment Failed");
        }

        return success;
    }
}