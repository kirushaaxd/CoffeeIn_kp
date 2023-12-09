package com.example.coffeein_kp;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class StaticResources {
    static List<Object> dishes;
    static List<Object> coffeeHouses;
    static List<Object> orders;
    static Client currentClient;

    static FirebaseFirestore fBase;
    static FirebaseAuth mAuth;

    public static void initialize(){
        fBase = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

}
