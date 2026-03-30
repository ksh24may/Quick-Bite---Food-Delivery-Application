package com.example.restaurantapp;

import java.util.ArrayList;
import java.util.List;

public class CartSingleton {
    private static CartSingleton instance;
    private final List<CartItem> cartItems;

    private CartSingleton() {
        cartItems = new ArrayList<>();
    }

    public static CartSingleton getInstance() {
        if (instance == null) {
            instance = new CartSingleton();
        }
        return instance;
    }

    // ✅ Get all cart items
    public List<CartItem> getItems() {
        return new ArrayList<>(cartItems);
    }

    // ✅ Add item (merge if same item already exists)
    public void addItem(CartItem item) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getName().equalsIgnoreCase(item.getName())) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        cartItems.add(item);
    }

    // ✅ Remove specific item
    public void removeItem(CartItem item) {
        cartItems.remove(item);
    }

    // ✅ Clear all items
    public void clearCart() {
        cartItems.clear();
    }

    // ✅ Calculate total price
    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}
