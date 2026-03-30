package com.example.restaurantapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private RadioGroup paymentOptions;
    private TextView selectedAddress, tvSubtotal, tvDiscount, tvDelivery, tvTotal;
    private LinearLayout cardForm, upiForm;
    private EditText etCardNumber, etExpiry, etCvv, etUpi;
    private ImageView upiQr;

    private double subtotal = 0;
    private double discount = 0;
    private double deliveryFee = 30;
    private double finalTotal = 0;

    private String chosenAddress = "";

    private static final String PREFS_NAME = "QuickBitePrefs";
    private static final String KEY_ADDRESS = "saved_address";

    private final String[] defaultAddresses = {
            "Home: 123 Main Street, Pune\nName: Shreya\nPhone: 9876543210",
            "Office: 456 Business Park, Mumbai\nName: Shreya\nPhone: 9876543210",
            "Other: 789 Apartment Complex, Delhi\nName: Shreya\nPhone: 9876543210"
    };

    private final String[] discountCards = {
            "1111111111111111", "2222222222222222", "3333333333333333", "4444444444444444"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        paymentOptions = findViewById(R.id.payment_options);
        selectedAddress = findViewById(R.id.selected_address);
        tvSubtotal = findViewById(R.id.tv_subtotal);
        tvDiscount = findViewById(R.id.tv_discount);
        tvDelivery = findViewById(R.id.tv_delivery);
        tvTotal = findViewById(R.id.tv_total);

        cardForm = findViewById(R.id.card_form);
        upiForm = findViewById(R.id.upi_form);

        etCardNumber = findViewById(R.id.et_card_number);
        etExpiry = findViewById(R.id.et_expiry);
        etCvv = findViewById(R.id.et_cvv);
        etUpi = findViewById(R.id.et_upi);
        upiQr = findViewById(R.id.upi_qr);

        Button btnSelectAddress = findViewById(R.id.btn_select_address);
        Button btnConfirmPayment = findViewById(R.id.btn_confirm_payment);
        Button btnBackToMenu = findViewById(R.id.btn_back_to_menu);

        subtotal = CartSingleton.getInstance().getTotalPrice();
        updateBill();

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        chosenAddress = prefs.getString(KEY_ADDRESS, "");
        if (!chosenAddress.isEmpty()) {
            selectedAddress.setText("Deliver to:\n" + chosenAddress);
        }

        btnSelectAddress.setOnClickListener(v -> showAddressDialog());

        paymentOptions.setOnCheckedChangeListener((group, checkedId) -> {
            cardForm.setVisibility(checkedId == R.id.payment_card ? LinearLayout.VISIBLE : LinearLayout.GONE);
            upiForm.setVisibility(checkedId == R.id.payment_upi ? LinearLayout.VISIBLE : LinearLayout.GONE);
        });

        btnConfirmPayment.setOnClickListener(v -> confirmPayment());

        btnBackToMenu.setOnClickListener(v -> {
            startActivity(new Intent(PaymentActivity.this, MenuActivity.class));
            finish();
        });
    }

    private void confirmPayment() {
        int selectedId = paymentOptions.getCheckedRadioButtonId();
        if (selectedId == -1) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(chosenAddress)) {
            Toast.makeText(this, "Please select a delivery address", Toast.LENGTH_SHORT).show();
            return;
        }

        // Reset discount
        discount = 0;

        // ✅ Free delivery if subtotal > 500
        deliveryFee = subtotal > 500 ? 0 : 30;

        if (selectedId == R.id.payment_card) {
            String card = etCardNumber.getText().toString().trim();
            String expiry = etExpiry.getText().toString().trim();
            String cvv = etCvv.getText().toString().trim();

            if (card.isEmpty() || expiry.isEmpty() || cvv.isEmpty()) {
                Toast.makeText(this, "Fill all card details", Toast.LENGTH_SHORT).show();
                return;
            }

            // ✅ Apply 10% discount if card matches
            for (String c : discountCards) {
                if (c.equals(card)) {
                    discount = subtotal * 0.1;
                    break;
                }
            }

            finalTotal = subtotal - discount + deliveryFee;
            updateBill();
            Toast.makeText(this, "Payment Successful via Card", Toast.LENGTH_LONG).show();

        } else if (selectedId == R.id.payment_upi) {
            String upi = etUpi.getText().toString().trim();
            if (upi.isEmpty()) {
                Toast.makeText(this, "Enter UPI ID", Toast.LENGTH_SHORT).show();
                return;
            }

            finalTotal = subtotal - discount + deliveryFee;
            updateBill();

            // 🔹 Show static QR
            upiQr.setImageResource(R.drawable.qr_upi);
            upiQr.setVisibility(View.VISIBLE);

            Toast.makeText(this, "Scan this QR to pay via UPI", Toast.LENGTH_LONG).show();

        } else { // COD
            finalTotal = subtotal - discount + deliveryFee;
            updateBill();
            Toast.makeText(this, "Payment Successful via COD", Toast.LENGTH_LONG).show();
        }

        CartSingleton.getInstance().clearCart();

        // ✅ Auto redirect after 3s
        new Handler().postDelayed(() -> {
            startActivity(new Intent(PaymentActivity.this, MenuActivity.class));
            finish();
        }, 3000);
    }

    private void updateBill() {
        tvSubtotal.setText("₹" + subtotal);

        if (discount > 0) {
            tvDiscount.setVisibility(View.VISIBLE);
            tvDiscount.setText("-₹" + discount);
        } else {
            tvDiscount.setVisibility(View.GONE); // hide if no discount
        }

        tvDelivery.setText("₹" + deliveryFee);
        tvTotal.setText("₹" + finalTotal);
    }

    private void showAddressDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Address");
        String[] options = new String[defaultAddresses.length + 1];
        System.arraycopy(defaultAddresses, 0, options, 0, defaultAddresses.length);
        options[options.length - 1] = "➕ Add New Address";

        builder.setItems(options, (dialog, which) -> {
            if (which < defaultAddresses.length) {
                chosenAddress = options[which];
                saveAddress(chosenAddress);
                selectedAddress.setText("Deliver to:\n" + chosenAddress);
            } else {
                showAddAddressDialog();
            }
        });
        builder.show();
    }

    private void showAddAddressDialog() {
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50,20,50,10);

        EditText name = new EditText(this);
        name.setHint("Full Name");

        EditText phone = new EditText(this);
        phone.setHint("Phone");
        phone.setInputType(android.text.InputType.TYPE_CLASS_PHONE);

        EditText address = new EditText(this);
        address.setHint("Full Address");

        layout.addView(name);
        layout.addView(phone);
        layout.addView(address);

        new AlertDialog.Builder(this)
                .setTitle("Enter Delivery Details")
                .setView(layout)
                .setPositiveButton("Save", (d,w)->{
                    String nameText = name.getText().toString().trim();
                    String phoneText = phone.getText().toString().trim();
                    String addressText = address.getText().toString().trim();

                    if (!nameText.isEmpty() && !phoneText.isEmpty() && !addressText.isEmpty()) {
                        chosenAddress = "Name: " + nameText +
                                "\nPhone: " + phoneText +
                                "\nAddress: " + addressText;

                        saveAddress(chosenAddress);
                        selectedAddress.setText("Deliver to:\n" + chosenAddress);

                    } else {
                        Toast.makeText(this,"Fill all fields",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel",(d,w)->d.dismiss())
                .show();
    }

    private void saveAddress(String address) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(KEY_ADDRESS, address);
        editor.apply();
    }
}
