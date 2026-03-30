package com.example.restaurantapp;

public class Address {
    private String name;
    private String phone;
    private String fullAddress;

    public Address(String name, String phone, String fullAddress) {
        this.name = name;
        this.phone = phone;
        this.fullAddress = fullAddress;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getFullAddress() { return fullAddress; }

    @Override
    public String toString() {
        return name + " (" + phone + ")\n" + fullAddress;
    }
}
