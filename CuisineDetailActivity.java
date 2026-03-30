package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CuisineDetailActivity extends AppCompatActivity {

    private String cuisine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisine_detail);

        // 🔹 Get cuisine name from intent
        cuisine = getIntent().getStringExtra("cuisine");

        // 🔹 Setup RecyclerView
        RecyclerView rv = findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<com.example.restaurantapp.MenuItem> items =
                MenuSingleton.getInstance().getItemsByCategory(cuisine);

        MenuAdapter adapter = new MenuAdapter(this, items, item -> {
            CartItem cartItem = new CartItem(
                    item.getName(),
                    item.getPrice(),
                    1,
                    item.getImageResId()
            );
            CartSingleton.getInstance().addItem(cartItem);
        });

        rv.setAdapter(adapter);

        // 🔹 Setup buttons
        Button btnBack = findViewById(R.id.btn_back);
        Button btnGoToCart = findViewById(R.id.btn_go_to_cart);

        btnBack.setOnClickListener(v -> {
            onBackPressed(); // return to cuisine grid
        });

        btnGoToCart.setOnClickListener(v -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });
    }
}
