package com.example.restaurantapp;

public class CuisineItem {
    private String name;
    private int imageResId;

    public CuisineItem(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() { return name; }
    public int getImageResId() { return imageResId; }
}
