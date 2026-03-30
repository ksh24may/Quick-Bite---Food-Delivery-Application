package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // ✅ Load slide-up animation
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        ImageView logo = findViewById(R.id.splash_logo);
        TextView appName = findViewById(R.id.splash_appname);
        TextView tagline = findViewById(R.id.splash_tagline);

        logo.startAnimation(slideUp);
        appName.startAnimation(slideUp);
        tagline.startAnimation(slideUp);

        // ✅ After delay, go straight to MenuActivity
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MenuActivity.class));
            finish();
        }, SPLASH_TIME);
    }
}
