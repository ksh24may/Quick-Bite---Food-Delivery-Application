package com.example.restaurantapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CuisineAdapter extends RecyclerView.Adapter<CuisineAdapter.CuisineViewHolder> {

    private final Context context;
    private final List<CuisineItem> cuisines;

    public CuisineAdapter(Context context, List<CuisineItem> cuisines) {
        this.context = context;
        this.cuisines = cuisines;
    }

    @NonNull
    @Override
    public CuisineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cuisine_item, parent, false);
        return new CuisineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CuisineViewHolder holder, int position) {
        CuisineItem item = cuisines.get(position);

        holder.cuisineName.setText(item.getName());
        holder.cuisineImage.setImageResource(item.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CuisineDetailActivity.class);
            intent.putExtra("cuisine", item.getName()); // Pass cuisine name
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return cuisines.size();
    }

    public static class CuisineViewHolder extends RecyclerView.ViewHolder {
        ImageView cuisineImage;
        TextView cuisineName;

        public CuisineViewHolder(@NonNull View itemView) {
            super(itemView);
            cuisineImage = itemView.findViewById(R.id.cuisine_image);
            cuisineName = itemView.findViewById(R.id.cuisine_name);
        }
    }
}
