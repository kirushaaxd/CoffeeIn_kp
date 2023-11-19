package com.example.coffeein_kp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

public class FragmentHome extends Fragment {
    TabLayout tab_layout;
    RecyclerView recycler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        tab_layout = (TabLayout) v.findViewById(R.id.tab_layout);
        recycler = (RecyclerView) v.findViewById(R.id.recycler);

        AdapterDishes adapter = new AdapterDishes(StaticResources.dishes);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }
}
