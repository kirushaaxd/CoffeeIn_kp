package com.example.coffeein_kp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivityApp extends AppCompatActivity {
    NavigationBarView navigationBar;
    FrameLayout host;


    private NavigationBarView.OnItemSelectedListener onItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
        // Обработка нажатий нижнего меню
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.Home){

                return true;
            }
            if (item.getItemId() == R.id.Order){

                return true;
            }
            if (item.getItemId() == R.id.Address){

                return true;
            }
            if (item.getItemId() == R.id.Profile){

                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        host = findViewById(R.id.host);
    }
}