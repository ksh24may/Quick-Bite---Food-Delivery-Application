package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // 🔹 Find views
        ImageView imageView = findViewById(R.id.detailImage);
        TextView nameView = findViewById(R.id.detailName);
        TextView descView = findViewById(R.id.detailDescription);
        TextView priceView = findViewById(R.id.detailPrice);
        Button btnAddToCart = findViewById(R.id.btnAddToCart);

        // 🔹 Get data from Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("dish_name");
        int price = intent.getIntExtra("dish_price", -1);
        String description = intent.getStringExtra("dish_description");
        int imageResId = intent.getIntExtra("dish_image", 0);

        // 🔹 Set values into UI
        if (name != null) nameView.setText(name);
        if (price >= 0) {
            priceView.setText("₹" + price);
        } else {
            priceView.setText("Price unavailable");
        }
        if (description != null) descView.setText(description);
        if (imageResId != 0) imageView.setImageResource(imageResId);

        // 🔹 Add to Cart button
        btnAddToCart.setOnClickListener(v -> {
            if (name != null && price >= 0) {
                CartItem cartItem = new CartItem(name, price, 1, imageResId);
                CartSingleton.getInstance().addItem(cartItem);

                // ✅ Show dialog: Back or Go to Cart
                new AlertDialog.Builder(this)
                        .setTitle("Added to Cart 🛒")
                        .setMessage(name + " has been added to your cart.")
                        .setPositiveButton("Go to Cart", (dialog, which) -> {
                            Intent cartIntent = new Intent(DetailsActivity.this, CartActivity.class);
                            startActivity(cartIntent);
                            finish();
                        })
                        .setNegativeButton("Back", (dialog, which) -> {
                            // Just close details page and go back
                            finish();
                        })
                        .show();
            }
        });
    }
}
