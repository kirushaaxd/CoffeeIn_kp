package com.example.coffeein_kp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class StaticResources {
    // Данные обо всем ассортименте + категории
    static ArrayList<Product> allProducts;
    static ArrayList<Product> promoProducts;
    static ArrayList<Product> drinksProducts;
    static ArrayList<Product> dessertsProducts;

    // Готовое отображение ассортимента по категориям
    static AdapterDishes allProductsAdapter;
    static AdapterDishes promoAdapter;
    static AdapterDishes drinksAdapter;
    static AdapterDishes dessertsAdapter;

    // Данные о городах и кофейнях в них
    static ArrayList<CoffeeCity> cities;

    static ArrayList<Object> orders;
    static Client currentClient;

    static FirebaseFirestore fBase;
    static FirebaseAuth mAuth;

    public static void initialize(){
        fBase = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        allProducts = new ArrayList<>();
        promoProducts = new ArrayList<>();
        drinksProducts = new ArrayList<>();
        dessertsProducts = new ArrayList<>();

        cities = new ArrayList<>();
    }

}
