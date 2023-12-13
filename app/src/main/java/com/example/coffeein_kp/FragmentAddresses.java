package com.example.coffeein_kp;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.BreakIterator;
import java.util.ArrayList;

public class FragmentAddresses extends Fragment {
    RecyclerView recycler;
    Spinner spinner;
    static TextView openTime;
    static TextView name;
    static TextView location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_addresses, container, false);

        recycler = (RecyclerView) v.findViewById(R.id.recycler);
        spinner = (Spinner) v.findViewById(R.id.spinner);
        openTime = (TextView) v.findViewById(R.id.openTimeA);
        name = (TextView) v.findViewById(R.id.nameA);
        location = (TextView) v.findViewById(R.id.locationA);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                recycler.setAdapter(StaticResources.cities.get(position).getAdapter());
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (StaticResources.cities.size() == 0)
            LoadCities();
        else {
            ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, StaticResources.citiesNames);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setSelection(0);

            openTime.setText("Работает c " + StaticResources.selectedCoffeeHouse.getTimeOpen() + " до " + StaticResources.selectedCoffeeHouse.getTimeClose());
            name.setText(StaticResources.selectedCoffeeHouse.getName());
            location.setText(StaticResources.selectedCoffeeHouse.getAddress());
        }
        return v;
    }

    public void LoadCafes() {
        // Загрузка всех кофеен в каждом городе
        StaticResources.fBase.collection("Кофейни")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CoffeeHouse coffeeHouse = new CoffeeHouse(document.getString("Название"), document.getString("Адрес"),
                                        document.getDocumentReference("Город").getId(), document.getString("Время открытия"),
                                        document.getString("Время закрытия"), document.getId());

                                for (CoffeeCity city : StaticResources.cities)
                                    if (city.getDocId().equals(document.getDocumentReference("Город").getId()))
                                        city.AddCoffeeHouse(coffeeHouse);
                            }
                            for (CoffeeCity city : StaticResources.cities){
                                AdapterCoffeeHouse adapter = new AdapterCoffeeHouse(city.getCoffeeShops());
                                city.setAdapter(adapter);
                            }

                            StaticResources.selectedCoffeeHouse = StaticResources.cities.get(0).getCoffeeShops().get(0);
                            recycler.setAdapter(StaticResources.cities.get(0).getAdapter());
                            recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                        } else {
                            Log.i(TAG, "Error getting documents", task.getException());
                        }
                    }
                });
    }

    public void LoadCities() {
        // Загрузка всех городов
        StaticResources.fBase.collection("Города")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CoffeeCity city = new CoffeeCity(document.getString("Город"), document.getString("Область"), document.getId());
                                StaticResources.cities.add(city);
                                StaticResources.citiesNames.add(city.getCity());
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, StaticResources.citiesNames);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);

                            LoadCafes();
                        } else {
                            Log.i(TAG, "Error getting documents", task.getException());
                        }
                    }
                });
    }

    public static void ChangeCoffeeHouse(String timeT, String nameT, String locationT){
        // Изменение выбранного адреса для заказа
        openTime.setText(timeT);
        name.setText(nameT);
        location.setText(locationT);
    }
}
