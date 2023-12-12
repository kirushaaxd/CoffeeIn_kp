package com.example.coffeein_kp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivityApp extends AppCompatActivity {
    NavigationBarView navigationBar;
    FrameLayout host;

     static FragmentHome fragmentHome;
     static FragmentOrder fragmentOrder;
     static FragmentAddresses fragmentAddresses;
     static FragmentProfile fragmentProfile;

     NavigationBarView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        host = findViewById(R.id.host);

        fragmentHome = new FragmentHome();
        fragmentOrder = new FragmentOrder();
        fragmentAddresses = new FragmentAddresses();
        fragmentProfile = new FragmentProfile();

        StaticResources.fragmentManager = getSupportFragmentManager();

        navigation = (NavigationBarView) findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(new com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.Home){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(host.getId(), fragmentHome);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                if (item.getItemId() == R.id.Order){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(host.getId(), fragmentOrder);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                if (item.getItemId() == R.id.Address){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(host.getId(), fragmentAddresses);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                if (item.getItemId() == R.id.Profile){
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(host.getId(), fragmentProfile);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                return false;
            }

        });

        navigation.setSelectedItemId(R.id.Home);
    }

    static public void LoadClientsOrders(){
        // Загрузка списка всех заказов авторизованного клиента
        StaticResources.fBase.collection("Заказы клиентов")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getDocumentReference("Клиент").getId().equals(StaticResources.currentClient.getDocId())){
                                    StaticResources.clientOrders = new ClientOrders(StaticResources.currentClient.getDocId());
                                    ArrayList<DocumentReference> orders = (ArrayList<DocumentReference>) document.get("Заказы");
                                    for (DocumentReference order : orders){
                                        String orderId = order.getId();
                                        StaticResources.clientOrders.addOrder(orderId);
                                    }
                                    LoadClientsOrdersInfo();
                                    break;
                                }
                            }
                        }
                        else {
                            Log.i(TAG, "Error getting documents", task.getException());
                        }
                    }
                });
    }

    static public void LoadClientsOrdersInfo(){
        // Загрузка информации по каждому заказу авторизованного клиента
        StaticResources.fBase.collection("Заказы")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (StaticResources.clientOrders.getOrdersId().contains(document.getId())){
                                    Order order = new Order(document.getDocumentReference("Адрес кофейни").getId(),
                                            document.getString("Время заказа"), document.getString("Дата заказа"),
                                            document.getLong("Итоговая стоимость").intValue());
                                    List<HashMap<String, Object>> orderProducts = (List<HashMap<String, Object>>) document.get("Товары в заказе");
                                    for (HashMap<String, Object> orderProduct : orderProducts)
                                        for (Product product : StaticResources.allProducts)
                                            if (product.getDocumentID().equals(((DocumentReference) orderProduct.get("Название товара")).getId()))
                                                order.addProduct(new OrderProduct(((Long) orderProduct.get("Кол-во товара")).intValue(), product, ((Long) orderProduct.get("Стоимость позиций")).intValue()));
                                    StaticResources.ordersStory.add(order);
                                }
                            }
                        }
                        else {
                            Log.i(TAG, "Error getting documents", task.getException());
                        }
                    }
                });
    }
}