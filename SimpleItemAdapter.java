package com.example.restaurantapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SimpleItemAdapter extends RecyclerView.Adapter<SimpleItemAdapter.SimpleViewHolder> {

    public interface OnSimpleItemClickListener {
        void onSimpleItemClick(String itemName);
    }

    private final Context context;
    private final List<MenuItem> items;
    private final OnSimpleItemClickListener listener;

    public SimpleItemAdapter(Context context, List<MenuItem> items, OnSimpleItemClickListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        MenuItem item = items.get(position);
        holder.itemName.setText(item.getName());
        holder.itemImage.setImageResource(item.getImageResId());
        holder.itemView.setOnClickListener(v -> listener.onSimpleItemClick(item.getName()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class SimpleViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;

        public SimpleViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.simple_item_image);
            itemName = itemView.findViewById(R.id.simple_item_name);
        }
    }
}
