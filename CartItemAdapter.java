package com.example.restaurantapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartViewHolder> {

    public interface OnCartChangeListener {
        void onCartUpdated();
    }

    private final Context context;
    private final List<CartItem> cartItems;
    private final OnCartChangeListener listener;

    public CartItemAdapter(Context context, List<CartItem> cartItems, OnCartChangeListener listener) {
        this.context = context;
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);

        holder.itemName.setText(item.getName());
        holder.itemPrice.setText("₹" + item.getPrice());
        holder.itemQuantity.setText(String.valueOf(item.getQuantity()));
        holder.itemImage.setImageResource(item.getImageResId());

        // Remove item
        holder.removeButton.setOnClickListener(v -> {
            CartSingleton.getInstance().removeItem(item);
            notifyDataSetChanged();
            listener.onCartUpdated();
        });

        // Increase quantity
        holder.increaseButton.setOnClickListener(v -> {
            item.setQuantity(item.getQuantity() + 1);
            holder.itemQuantity.setText(String.valueOf(item.getQuantity()));
            listener.onCartUpdated();
        });

        // Decrease quantity
        holder.decreaseButton.setOnClickListener(v -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                holder.itemQuantity.setText(String.valueOf(item.getQuantity()));
            } else {
                CartSingleton.getInstance().removeItem(item);
                notifyDataSetChanged();
            }
            listener.onCartUpdated();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName, itemPrice, itemQuantity;
        Button removeButton, increaseButton, decreaseButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.cart_item_image);
            itemName = itemView.findViewById(R.id.cart_item_name);
            itemPrice = itemView.findViewById(R.id.cart_item_price);
            itemQuantity = itemView.findViewById(R.id.cart_item_quantity);
            removeButton = itemView.findViewById(R.id.btn_remove_item);
            increaseButton = itemView.findViewById(R.id.btn_increase);
            decreaseButton = itemView.findViewById(R.id.btn_decrease);
        }
    }
}
