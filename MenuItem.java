package com.example.restaurantapp;

public class MenuItem {
    private final String name;
    private final int price;
    private final int imageResId;
    private final String category;
    private final String description;

    public MenuItem(String name, int price, int imageResId, String category, String description) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.category = category;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    // ✅ Needed so AutoCompleteTextView shows dish names
    @Override
    public String toString() {
        return name;
    }
}
