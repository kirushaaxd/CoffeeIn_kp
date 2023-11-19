package com.example.coffeein_kp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivityApp extends AppCompatActivity {
    NavigationBarView navigationBar;
    FrameLayout host;

     FragmentHome fragmentHome;
     FragmentOrder fragmentOrder;
     FragmentAddresses fragmentAddresses;
     FragmentProfile fragmentProfile;
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

        navigation = (NavigationBarView) findViewById(R.id.navigation);
        navigation.setOnItemSelectedListener(new com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.Home){
                    ///fragmentHome = new FragmentHome();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(host.getId(), fragmentHome);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                if (item.getItemId() == R.id.Order){
                    //fragmentOrder = new FragmentOrder();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(host.getId(), fragmentOrder);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                if (item.getItemId() == R.id.Address){
                    //fragmentAddresses = new FragmentAddresses();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                    transaction.replace(host.getId(), fragmentAddresses);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                }
                if (item.getItemId() == R.id.Profile){
                    //fragmentProfile = new FragmentProfile();
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