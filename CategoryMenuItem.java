package com.example.restaurantapp;

import java.util.List;

public class CategoryMenuItem {
    private String categoryName;
    private List<MenuItem> items;

    public CategoryMenuItem(String categoryName, List<MenuItem> items) {
        this.categoryName = categoryName;
        this.items = items;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<MenuItem> getItems() {
        return items;
    }
}
