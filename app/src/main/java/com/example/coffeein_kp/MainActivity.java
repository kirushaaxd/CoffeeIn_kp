package com.example.coffeein_kp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    static FragmentAuthorization fragmentAuthorization;
    static FragmentRegistration fragmentRegistration;
    static FrameLayout host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        host = (FrameLayout) findViewById(R.id.host);

        StaticResources.initialize();

        fragmentAuthorization = new FragmentAuthorization();
        fragmentRegistration = new FragmentRegistration();

        loadAuthFragment();
    }

    public void loadAuthFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(host.getId(), fragmentAuthorization);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}