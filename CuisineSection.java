package com.example.restaurantapp;

import java.util.List;

public class CuisineSection {
    private String category;
    private List<MenuItem> items;
    private boolean expanded;

    public CuisineSection(String category, List<MenuItem> items) {
        this.category = category;
        this.items = items;
        this.expanded = false;
    }

    public String getCategory() { return category; }
    public List<MenuItem> getItems() { return items; }
    public boolean isExpanded() { return expanded; }
    public void setExpanded(boolean expanded) { this.expanded = expanded; }
}
