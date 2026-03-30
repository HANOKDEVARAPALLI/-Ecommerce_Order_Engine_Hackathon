package com.ecommerce.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LockManager {

    private static final ConcurrentHashMap<Integer, ReentrantLock> locks = new ConcurrentHashMap<>();

    public static ReentrantLock getLock(int productId) {
        return locks.computeIfAbsent(productId, k -> new ReentrantLock());
    }
}