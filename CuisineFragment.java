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

import java.util.ArrayList;
import java.util.List;

public class CuisineFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView rv = v.findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext())); // ✅ vertical scroll

        // ✅ Build list of cuisines (excluding desserts & beverages)
        List<CuisineItem> cuisines = new ArrayList<>();
        cuisines.add(new CuisineItem(MenuSingleton.ITALIAN, R.drawable.italian));
        cuisines.add(new CuisineItem(MenuSingleton.INDIAN, R.drawable.biryani));
        cuisines.add(new CuisineItem(MenuSingleton.JAPANESE, R.drawable.sushi));
        cuisines.add(new CuisineItem(MenuSingleton.CHINESE, R.drawable.chinese));
        cuisines.add(new CuisineItem(MenuSingleton.MEXICAN, R.drawable.tacos));
        cuisines.add(new CuisineItem(MenuSingleton.AMERICAN, R.drawable.american));
        cuisines.add(new CuisineItem(MenuSingleton.THAI, R.drawable.pad_thai));
        cuisines.add(new CuisineItem(MenuSingleton.MEDITERRANEAN, R.drawable.greek_salad));

        // ✅ Attach adapter
        CuisineAdapter adapter = new CuisineAdapter(getContext(), cuisines);
        rv.setAdapter(adapter);

        return v;
    }
}
