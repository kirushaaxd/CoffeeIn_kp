package com.example.coffeein_kp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentAddresses extends Fragment {
    RecyclerView recycler;
    Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_addresses, container, false);

        recycler = (RecyclerView) v.findViewById(R.id.recycler);
        spinner = (Spinner) v.findViewById(R.id.spinner);

        AdapterCoffeeHouse adapter = new AdapterCoffeeHouse(StaticResources.coffeeHouses);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));




        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Москва");
        arrayList.add("Санкт-Петербург");
        arrayList.add("Сочи");

        ArrayAdapter<String> adapter2 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter2);
        spinner.setSelection(0);

        return v;
    }
}
