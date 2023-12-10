package com.example.coffeein_kp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

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
}