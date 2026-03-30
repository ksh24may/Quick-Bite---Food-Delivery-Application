package com.example.restaurantapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CuisineDetailFragment extends Fragment {

    private static final String ARG_CUISINE = "cuisine";
    private String cuisine;

    public static CuisineDetailFragment newInstance(String cuisine) {
        CuisineDetailFragment fragment = new CuisineDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CUISINE, cuisine);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cuisine = getArguments().getString(ARG_CUISINE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView rv = v.findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        // Load dishes of selected cuisine
        List<MenuItem> items = MenuSingleton.getInstance().getItemsByCategory(cuisine);

        MenuAdapter adapter = new MenuAdapter(getContext(), items, item -> {
            CartItem cartItem = new CartItem(item.getName(), item.getPrice(), 1, item.getImageResId());
            CartSingleton.getInstance().addItem(cartItem);
        });

        rv.setAdapter(adapter);

        return v;
    }
}
