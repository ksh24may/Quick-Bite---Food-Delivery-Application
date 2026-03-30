package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private TextView totalPriceText;
    private Button proceedToPayment, continueShopping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recycler_view_cart);
        totalPriceText = findViewById(R.id.total_price);
        proceedToPayment = findViewById(R.id.btn_proceed_payment);
        continueShopping = findViewById(R.id.btn_continue_shopping);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<CartItem> cartItems = CartSingleton.getInstance().getItems();

        if (cartItems.isEmpty()) {
            Toast.makeText(this, "Your cart is empty", Toast.LENGTH_SHORT).show();
        }

        // ✅ Setup adapter
        adapter = new CartAdapter(this, cartItems, this::updateTotalPrice);
        recyclerView.setAdapter(adapter);

        // ✅ Show total
        updateTotalPrice();

        // ✅ Proceed to Payment
        proceedToPayment.setOnClickListener(v -> {
            double totalPrice = CartSingleton.getInstance().getTotalPrice();
            if (totalPrice <= 0) {
                Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
            intent.putExtra("TOTAL_PRICE", totalPrice);
            startActivity(intent);
        });

        // ✅ Continue Shopping → Back to Menu
        continueShopping.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void updateTotalPrice() {
        double total = CartSingleton.getInstance().getTotalPrice();
        totalPriceText.setText("Total: ₹" + total);
    }
}
