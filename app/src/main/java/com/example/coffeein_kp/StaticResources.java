package com.example.coffeein_kp;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class StaticResources {
    static ArrayList<Product> products;
    static ArrayList<Product> promoProducts;
    static ArrayList<Product> drinksProducts;
    static ArrayList<Product> dessertsProducts;

    static AdapterDishes allProductsAdapter;
    static AdapterDishes promoAdapter;
    static AdapterDishes drinksAdapter;
    static AdapterDishes dessertsAdapter;



    static ArrayList<Object> coffeeHouses;
    static ArrayList<Object> orders;
    static Client currentClient;

    static FirebaseFirestore fBase;
    static FirebaseAuth mAuth;

    public static void initialize(){
        fBase = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        products = new ArrayList<>();
        promoProducts = new ArrayList<>();
        drinksProducts = new ArrayList<>();
        dessertsProducts = new ArrayList<>();

    }

}
