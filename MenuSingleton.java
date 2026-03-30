package com.example.restaurantapp;

import java.util.ArrayList;
import java.util.List;

public class MenuSingleton {

    private static MenuSingleton instance;
    private final List<MenuItem> menuItems;

    // Category constants
    public static final String ITALIAN = "Italian";
    public static final String INDIAN = "Indian";
    public static final String JAPANESE = "Japanese";
    public static final String CHINESE = "Chinese";
    public static final String MEXICAN = "Mexican";
    public static final String AMERICAN = "American";
    public static final String THAI = "Thai";
    public static final String MEDITERRANEAN = "Mediterranean";
    public static final String DESSERTS = "Desserts";
    public static final String BEVERAGES = "Beverages";

    public static final String CUISINES = "Cuisines";

    private MenuSingleton() {
        menuItems = new ArrayList<>();

        // 🍕 Italian
        menuItems.add(new MenuItem("Pizza", 250, R.drawable.pizza, ITALIAN,
                "Classic Italian-style pizza topped with cheese and tomato sauce."));
        menuItems.add(new MenuItem("Pasta", 200, R.drawable.italian, ITALIAN,
                "Authentic pasta tossed in creamy sauce with fresh herbs."));

        // 🍛 Indian
        menuItems.add(new MenuItem("Butter Chicken", 300, R.drawable.butter_chicken, INDIAN,
                "Tender chicken in rich, creamy tomato gravy with spices."));
        menuItems.add(new MenuItem("Paneer Tikka", 220, R.drawable.paneer_tikka, INDIAN,
                "Paneer cubes marinated in yogurt and spices, grilled to perfection."));
        menuItems.add(new MenuItem("Chole Bhature", 180, R.drawable.chole_bhature, INDIAN,
                "Fried bread with spicy chickpea curry."));
        menuItems.add(new MenuItem("Biryani", 250, R.drawable.biryani, INDIAN,
                "Fragrant rice cooked with spices and chicken/veggies."));
        menuItems.add(new MenuItem("Masala Dosa", 150, R.drawable.masala_dosa, INDIAN,
                "Crispy dosa stuffed with spiced potato, served with chutney."));
        menuItems.add(new MenuItem("Palak Paneer", 200, R.drawable.palak_paneer, INDIAN,
                "Spinach puree cooked with paneer cubes in spiced gravy."));
        menuItems.add(new MenuItem("Dal Tadka", 160, R.drawable.dal_tadka, INDIAN,
                "Yellow lentils tempered with ghee, cumin, and garlic."));

        // 🍣 Japanese
        menuItems.add(new MenuItem("Sushi", 350, R.drawable.sushi, JAPANESE,
                "Rice rolls with fresh fish and vegetables."));
        menuItems.add(new MenuItem("Tuna Roll", 280, R.drawable.tuna_roll, JAPANESE,
                "Sushi roll filled with tuna and seasoned rice."));
        menuItems.add(new MenuItem("Ramen", 270, R.drawable.curry, JAPANESE,
                "Japanese noodle soup with broth and veggies."));

        // 🥡 Chinese
        menuItems.add(new MenuItem("Fried Rice", 200, R.drawable.chinese, CHINESE,
                "Stir-fried rice with vegetables, eggs, and soy sauce."));
        menuItems.add(new MenuItem("Spring Rolls", 150, R.drawable.spring_rolls, CHINESE,
                "Crispy rolls with veggie filling."));
        menuItems.add(new MenuItem("Chow Mein", 220, R.drawable.chow_mein, CHINESE,
                "Stir-fried noodles with vegetables and sauces."));

        // 🌮 Mexican
        menuItems.add(new MenuItem("Tacos", 200, R.drawable.tacos, MEXICAN,
                "Corn tortillas filled with meat, lettuce, and cheese."));
        menuItems.add(new MenuItem("Burrito", 250, R.drawable.burrito, MEXICAN,
                "Flour tortilla stuffed with rice, beans, and filling."));
        menuItems.add(new MenuItem("Nachos", 180, R.drawable.nachos, MEXICAN,
                "Tortilla chips topped with cheese and jalapeños."));

        // 🍔 American
        menuItems.add(new MenuItem("Cheeseburger", 220, R.drawable.american, AMERICAN,
                "Beef patty topped with cheese, lettuce, and tomato."));
        menuItems.add(new MenuItem("Hotdog", 150, R.drawable.hotdog, AMERICAN,
                "Grilled sausage in a bun with sauces."));
        menuItems.add(new MenuItem("Fries", 120, R.drawable.fries, AMERICAN,
                "Golden crispy fries with ketchup."));

        // 🍜 Thai
        menuItems.add(new MenuItem("Pad Thai", 300, R.drawable.pad_thai, THAI,
                "Stir-fried noodles with shrimp, tofu, and peanuts."));
        menuItems.add(new MenuItem("Green Curry", 280, R.drawable.green_curry, THAI,
                "Coconut-based curry with chicken and vegetables."));
        menuItems.add(new MenuItem("Tom Yum Soup", 260, R.drawable.tom_yum, THAI,
                "Hot and sour soup with shrimp and lemongrass."));

        // 🥗 Mediterranean
        menuItems.add(new MenuItem("Falafel", 180, R.drawable.falafel, MEDITERRANEAN,
                "Fried chickpea balls served with hummus."));
        menuItems.add(new MenuItem("Hummus & Pita", 160, R.drawable.hummus, MEDITERRANEAN,
                "Creamy hummus with pita bread."));
        menuItems.add(new MenuItem("Greek Salad", 200, R.drawable.greek_salad, MEDITERRANEAN,
                "Salad with cucumber, tomato, olives, feta cheese."));

        // 🍨 Desserts
        menuItems.add(new MenuItem("Ice Cream", 120, R.drawable.icecream, DESSERTS,
                "Frozen dessert in many flavors."));
        menuItems.add(new MenuItem("Waffle", 150, R.drawable.waffle, DESSERTS,
                "Crispy waffle with syrup and cream."));
        menuItems.add(new MenuItem("Pancake", 180, R.drawable.pancake, DESSERTS,
                "Fluffy pancakes with butter and syrup."));
        menuItems.add(new MenuItem("Sundae", 200, R.drawable.sundae, DESSERTS,
                "Ice cream with syrup and nuts."));

        // 🥤 Beverages
        menuItems.add(new MenuItem("Coffee", 100, R.drawable.coffee, BEVERAGES,
                "Hot brewed coffee."));
        menuItems.add(new MenuItem("Tea", 80, R.drawable.tea, BEVERAGES,
                "Refreshing chai or green tea."));
        menuItems.add(new MenuItem("Smoothie", 150, R.drawable.smoothie, BEVERAGES,
                "Fruit smoothie with yogurt."));
        menuItems.add(new MenuItem("Juice", 120, R.drawable.juice, BEVERAGES,
                "Fresh fruit juice."));
    }

    public static MenuSingleton getInstance() {
        if (instance == null) {
            instance = new MenuSingleton();
        }
        return instance;
    }

    public List<MenuItem> getItemsByCategory(String category) {
        List<MenuItem> filtered = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getCategory().equalsIgnoreCase(category)) {
                filtered.add(item);
            }
        }
        return filtered;
    }

    public List<MenuItem> getAllCuisines() {
        List<MenuItem> cuisines = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (!item.getCategory().equalsIgnoreCase(DESSERTS) &&
                    !item.getCategory().equalsIgnoreCase(BEVERAGES)) {
                cuisines.add(item);
            }
        }
        return cuisines;
    }

    public List<MenuItem> searchItems(String query) {
        List<MenuItem> result = new ArrayList<>();
        for (MenuItem item : menuItems) {
            if (item.getName().toLowerCase().contains(query.toLowerCase()) ||
                    item.getDescription().toLowerCase().contains(query.toLowerCase())) {
                result.add(item);
            }
        }
        return result;
    }

    public List<MenuItem> getAllItems() {
        return new ArrayList<>(menuItems);
    }

    // ✅ NEW: Exact getter by name
    public MenuItem getItemByName(String name) {
        for (MenuItem item : menuItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }
}
