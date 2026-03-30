package com.example.restaurantapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    public interface OnItemClickListener {
        void onAddToCartClick(MenuItem item);
    }

    private final Context context;
    private final List<MenuItem> menuItems;
    private final OnItemClickListener listener;

    public MenuAdapter(Context context, List<MenuItem> menuItems, OnItemClickListener listener) {
        this.context = context;
        this.menuItems = menuItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem item = menuItems.get(position);

        // Bind data
        holder.itemName.setText(item.getName());
        holder.itemDescription.setText(item.getDescription());
        holder.itemPrice.setText("₹" + item.getPrice());

        if (item.getImageResId() != 0) {
            holder.itemImage.setImageResource(item.getImageResId());
        } else {
            holder.itemImage.setImageResource(R.mipmap.ic_launcher); // fallback
        }

        // Add to cart
        holder.addToCartButton.setOnClickListener(v -> listener.onAddToCartClick(item));

        // Card click → open DetailsActivity
        holder.itemView.setOnClickListener(v -> openDishDetail(item));

        // Image click → open DetailsActivity
        holder.itemImage.setOnClickListener(v -> openDishDetail(item));
    }

    private void openDishDetail(MenuItem item) {
        Intent intent = new Intent(context, DetailsActivity.class); // ✅ always DetailsActivity
        intent.putExtra("dish_name", item.getName());
        intent.putExtra("dish_price", item.getPrice());
        intent.putExtra("dish_description", item.getDescription());
        intent.putExtra("dish_image", item.getImageResId());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName, itemDescription, itemPrice;
        Button addToCartButton;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            itemName = itemView.findViewById(R.id.item_name);
            itemDescription = itemView.findViewById(R.id.item_description);
            itemPrice = itemView.findViewById(R.id.item_price);
            addToCartButton = itemView.findViewById(R.id.btn_add_to_cart);
        }
    }
}
