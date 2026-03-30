package com.example.restaurantapp;

import java.util.ArrayList;
import java.util.List;

public class AddressSingleton {
    private static AddressSingleton instance;
    private final List<Address> addressList;

    private AddressSingleton() {
        addressList = new ArrayList<>();

        // ✅ Default addresses
        addressList.add(new Address("Shreya Gupta", "9876543210", "Flat 101, Green Park, Pune"));
        addressList.add(new Address("Rohan Sharma", "9123456789", "B-204, Sunshine Apartments, Mumbai"));
        addressList.add(new Address("Aditi Mehta", "9988776655", "12, Rose Villa, Delhi"));
    }

    public static AddressSingleton getInstance() {
        if (instance == null) {
            instance = new AddressSingleton();
        }
        return instance;
    }

    public List<Address> getAddresses() {
        return addressList;
    }

    public void addAddress(Address address) {
        addressList.add(address);
    }
}
