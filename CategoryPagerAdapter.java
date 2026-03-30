package com.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CategoryPagerAdapter extends FragmentStateAdapter {
    public CategoryPagerAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new CuisineFragment();
            case 1: return new DessertsFragment();
            default: return new BeveragesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
