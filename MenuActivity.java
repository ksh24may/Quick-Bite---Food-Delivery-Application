package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private TextView menuTitle;
    private AutoCompleteTextView searchDishes;

    private final String[] titles = {"🍽️ Cuisines", "🍰 Desserts", "🥤 Beverages"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuTitle = findViewById(R.id.menu_title);
        searchDishes = findViewById(R.id.search_dishes);

        // 🔍 Search setup using MenuItem objects directly
        List<MenuItem> allItems = MenuSingleton.getInstance().getAllItems();
        ArrayAdapter<MenuItem> autoAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, allItems);
        searchDishes.setAdapter(autoAdapter);

        searchDishes.setOnItemClickListener((parent, view, position, id) -> {
            MenuItem selected = (MenuItem) parent.getItemAtPosition(position);
            showSelectedDish(selected);
        });

        // ⭐ Popular food click (must match MenuSingleton names!)
        findViewById(R.id.popular_pizza).setOnClickListener(v -> showSelectedDish(
                MenuSingleton.getInstance().getItemByName("Pizza")));
        findViewById(R.id.popular_burger).setOnClickListener(v -> showSelectedDish(
                MenuSingleton.getInstance().getItemByName("Cheeseburger")));
        findViewById(R.id.popular_sushi).setOnClickListener(v -> showSelectedDish(
                MenuSingleton.getInstance().getItemByName("Sushi")));

        // 🔹 Tabs + Slider
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        viewPager.setAdapter(new CategoryPagerAdapter(this));

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, pos) -> tab.setText(titles[pos])
        ).attach();

        // 🔙 Back button
        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> finish());

        // 🛒 Go to cart
        Button goToCartButton = findViewById(R.id.btn_go_to_cart);
        goToCartButton.setOnClickListener(v ->
                startActivity(new Intent(MenuActivity.this, CartActivity.class)));
    }

    // ✅ This version receives a MenuItem directly
    private void showSelectedDish(MenuItem found) {
        if (found != null) {
            System.out.println("✅ Opening: " + found.getName() + " | Price=" + found.getPrice());

            Intent intent = new Intent(MenuActivity.this, DetailsActivity.class);
            intent.putExtra("dish_name", found.getName());
            intent.putExtra("dish_price", found.getPrice());
            intent.putExtra("dish_description", found.getDescription());
            intent.putExtra("dish_image", found.getImageResId());
            startActivity(intent);
        } else {
            System.out.println("❌ Dish not found");
            menuTitle.setText("Dish not found");
        }
    }
}
