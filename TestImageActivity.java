package com.example.restaurantapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TestImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image);

        // Match the IDs from XML
        ImageView foodImage = findViewById(R.id.test_image_food);
        ImageView launcherImage = findViewById(R.id.test_image_launcher);

        // Load your food image (pizza.png)
        foodImage.setImageResource(R.drawable.pizza);

        // Load default launcher as control
        launcherImage.setImageResource(R.mipmap.ic_launcher);
    }
}
