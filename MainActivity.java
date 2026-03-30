package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnViewMenu, btnGoToCart;
    private AutoCompleteTextView searchBar;

    // Master list of all dish names
    private final List<String> allNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewMenu = findViewById(R.id.btn_view_menu);
        btnGoToCart = findViewById(R.id.btn_go_to_cart);
        searchBar = findViewById(R.id.search_bar);

        // Collect unique names from MenuSingleton
        LinkedHashSet<String> unique = new LinkedHashSet<>();
        for (MenuItem mi : MenuSingleton.getInstance().getAllItems()) {
            unique.add(mi.getName());
        }
        allNames.addAll(unique);

        // Adapter for dynamic suggestions
        ArrayList<String> current = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                current
        );
        searchBar.setAdapter(adapter);
        searchBar.setThreshold(1);

        // Update suggestions while typing
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String q = s == null ? "" : s.toString().trim().toLowerCase();
                current.clear();

                if (!q.isEmpty()) {
                    for (String name : allNames) {
                        if (name.toLowerCase().startsWith(q)) {
                            current.add(name);
                        }
                    }
                }

                adapter.notifyDataSetChanged();

                if (!current.isEmpty() && searchBar.hasFocus()) {
                    searchBar.showDropDown();
                } else {
                    searchBar.dismissDropDown();
                }
            }

            @Override public void afterTextChanged(Editable s) {}
        });

        // 🔥 Debugging search click
        searchBar.setOnItemClickListener((parent, view, position, id) -> {
            String selected = (String) parent.getItemAtPosition(position);

            // Debug log to confirm what’s happening
            Log.d("DEBUG_SEARCH", "Clicked search suggestion: " + selected);

            // Find MenuItem
            MenuItem found = null;
            for (MenuItem mi : MenuSingleton.getInstance().getAllItems()) {
                if (mi.getName().equalsIgnoreCase(selected)) {
                    found = mi;
                    break;
                }
            }

            if (found != null) {
                Log.d("DEBUG_SEARCH", "Found item → opening DetailsActivity for " + found.getName());

                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("dish_name", found.getName());
                intent.putExtra("dish_price", found.getPrice());
                intent.putExtra("dish_description", found.getDescription());
                intent.putExtra("dish_image", found.getImageResId());
                startActivity(intent);
            } else {
                Log.e("DEBUG_SEARCH", "⚠️ No matching MenuItem found for: " + selected);
            }
        });

        // Buttons
        btnViewMenu.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
        });

        btnGoToCart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
