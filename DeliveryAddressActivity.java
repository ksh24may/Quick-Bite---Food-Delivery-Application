package com.example.restaurantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeliveryAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);

        EditText etName = findViewById(R.id.et_name);
        EditText etPhone = findViewById(R.id.et_phone);
        EditText etAddress = findViewById(R.id.et_address);
        Button btnSave = findViewById(R.id.btn_save_address);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            String newAddress = name + ": " + address + "\n📞 " + phone;

            // ✅ Return address to AddressListActivity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("NEW_ADDRESS", newAddress);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
