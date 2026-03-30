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

public class CuisineActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private TextView cuisineTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine);

        recyclerView = findViewById(R.id.recycler_view_cuisine);
        cuisineTitle = findViewById(R.id.cuisine_title);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get cuisine name from intent
        String cuisine = getIntent().getStringExtra("cuisine_name");

        if (cuisine != null) {
            List<MenuItem> filtered = MenuSingleton.getInstance().getItemsByCategory(cuisine);
            if (filtered.isEmpty()) {
                Toast.makeText(this, "No items found for " + cuisine, Toast.LENGTH_SHORT).show();
            } else {
                cuisineTitle.setText(cuisine + " Menu");
                adapter = new MenuAdapter(this, filtered, item -> {
                    CartItem cartItem = new CartItem(item.getName(), item.getPrice(), 1, item.getImageResId());
                    CartSingleton.getInstance().addItem(cartItem);
                    Toast.makeText(this, item.getName() + " added to cart", Toast.LENGTH_SHORT).show();
                });
                recyclerView.setAdapter(adapter);
            }
        }

        // 🔙 Back button
        Button backButton = findViewById(R.id.btn_back_cuisine);
        backButton.setOnClickListener(v -> finish());

        // 🛒 Go to Cart button
        Button cartButton = findViewById(R.id.btn_go_to_cart_cuisine);
        cartButton.setOnClickListener(v -> {
            Intent intent = new Intent(CuisineActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
