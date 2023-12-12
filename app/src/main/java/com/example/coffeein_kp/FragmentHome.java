package com.example.coffeein_kp;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FragmentHome extends Fragment {
    TabLayout tab_layout;
    RecyclerView recycler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        tab_layout = (TabLayout) v.findViewById(R.id.tab_layout);

        tab_layout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(new ViewPager(getContext())){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                if (tab.getText().equals("Все товары")){
                    recycler.setAdapter(StaticResources.allProductsAdapter);
                }
                else if (tab.getText().equals("Акции")){
                    recycler.setAdapter(StaticResources.promoAdapter);
                }
                else if (tab.getText().equals("Напитки")){
                    recycler.setAdapter(StaticResources.drinksAdapter);
                }
                else if (tab.getText().equals("Десерты")){
                    recycler.setAdapter(StaticResources.dessertsAdapter);
                }
                recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });

        recycler = (RecyclerView) v.findViewById(R.id.recycler);
        if (StaticResources.allProducts.size() == 0)
            LoadProducts();
        else{
            recycler.setAdapter(StaticResources.allProductsAdapter);
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        return v;
    }

    public void LoadProducts(){
        // Загрузка списка всех товаров
        StaticResources.fBase.collection("Товары")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Product product = new Product(document.getString("Название"), document.getString("Описание"),
                                        document.getLong("Стоимость").intValue(), document.getString("Калорийность"),
                                        document.getString("Изображение"), document.getString("Состав"),
                                        document.getString("Категория"), document.getId());
                                if (document.getString("Категория").equals("Акции"))
                                    StaticResources.promoProducts.add(product);
                                if (document.getString("Категория").equals("Напитки"))
                                    StaticResources.drinksProducts.add(product);
                                if (document.getString("Категория").equals("Десерты"))
                                    StaticResources.dessertsProducts.add(product);
                            }
                            StaticResources.allProducts.addAll(StaticResources.promoProducts);
                            StaticResources.allProducts.addAll(StaticResources.drinksProducts);
                            StaticResources.allProducts.addAll(StaticResources.dessertsProducts);

                            StaticResources.allProductsAdapter = new AdapterDishes(StaticResources.allProducts);
                            StaticResources.promoAdapter = new AdapterDishes(StaticResources.promoProducts);
                            StaticResources.drinksAdapter = new AdapterDishes(StaticResources.drinksProducts);
                            StaticResources.dessertsAdapter = new AdapterDishes(StaticResources.dessertsProducts);

                            recycler.setAdapter(StaticResources.allProductsAdapter);
                            recycler.setLayoutManager(new LinearLayoutManager(getContext()));

                            MainActivityApp.LoadClientsOrders();
                        }
                        else {
                            Log.i(TAG, "Error getting documents", task.getException());
                        }
                    }
                });
    }
}
