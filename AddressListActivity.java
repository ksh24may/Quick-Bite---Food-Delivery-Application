package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddressListActivity extends AppCompatActivity {

    List<String> addresses;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);

        ListView listView = findViewById(R.id.list_addresses);
        Button btnAddAddress = findViewById(R.id.btn_add_address);

        // ✅ Default Addresses
        addresses = new ArrayList<>();
        addresses.add("🏠 Home: 123 Main Street, Pune - 411001\n📞 9876543210");
        addresses.add("🏢 Office: 456 IT Park, Hinjewadi, Pune - 411057\n📞 9123456789");
        addresses.add("📍 Hostel: Room 21, MIT-WPU Campus, Pune\n📞 9988776655");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, addresses);
        listView.setAdapter(adapter);

        // ✅ Select Address from List
        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedAddress = addresses.get(position);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("SELECTED_ADDRESS", selectedAddress);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // ✅ Add New Address
        btnAddAddress.setOnClickListener(v -> {
            Intent intent = new Intent(AddressListActivity.this, DeliveryAddressActivity.class);
            startActivityForResult(intent, 200);
        });
    }

    // ✅ Handle New Address from DeliveryAddressActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            String newAddress = data.getStringExtra("NEW_ADDRESS");
            if (newAddress != null) {
                addresses.add(newAddress);
                adapter.notifyDataSetChanged();

                // Return immediately after adding
                Intent resultIntent = new Intent();
                resultIntent.putExtra("SELECTED_ADDRESS", newAddress);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        }
    }
}
