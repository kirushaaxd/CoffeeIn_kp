package com.example.coffeein_kp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);

        Intent intent = new Intent(this, MainActivity.class);
        new Handler().postDelayed(() -> {
            startActivity(intent);
            overridePendingTransition(android. R.anim. fade_in, android. R.anim. fade_out);
            finish();
        }, 1500);
    }
}