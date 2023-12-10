package com.example.coffeein_kp;

import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
    static ArrayList<String> citiesNames;

    // Данные об оформляемом заказе (корзина)
    static ArrayList<Product> orders;
    static LinkedHashMap<Product, Integer> ordersList;
    static AdapterOrders ordersAdapter;

    // Универсальный FragmentManager
    static FragmentManager fragmentManager;

    // Информация о авторизованном пользователе
    static Client currentClient;
    static CoffeeHouse selectedCoffeeHouse;


    // Работа с Firebase
    static FirebaseFirestore fBase;
    static FirebaseAuth mAuth;

    public static void initialize(){
        fBase = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        allProducts = new ArrayList<>();
        promoProducts = new ArrayList<>();
        drinksProducts = new ArrayList<>();
        dessertsProducts = new ArrayList<>();

        orders = new ArrayList<>();
        ordersList = new LinkedHashMap<>();

        cities = new ArrayList<>();
        citiesNames = new ArrayList<>();

    }

}
