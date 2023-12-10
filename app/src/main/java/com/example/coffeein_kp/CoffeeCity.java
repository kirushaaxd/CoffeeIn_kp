package com.example.coffeein_kp;

import java.util.ArrayList;

public class CoffeeCity {
    private String City;
    private String Region;
    private String DocId;
    private ArrayList<CoffeeHouse> coffeeShops;
    private AdapterCoffeeHouse adapter;

    public CoffeeCity(String City, String Region, String DocId) {
        this.City = City;
        this.Region = Region;
        this.DocId = DocId;
        coffeeShops = new ArrayList<>();
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public String getDocId() {
        return DocId;
    }

    public void setDocId(String docId) {
        DocId = docId;
    }

    public ArrayList<CoffeeHouse> getCoffeeShops() {
        return coffeeShops;
    }
    public void AddCoffeeHouse(CoffeeHouse shop){
        coffeeShops.add(shop);
    }

    public void setCoffeeShops(ArrayList<CoffeeHouse> coffeeShops) {
        this.coffeeShops = coffeeShops;
    }

    public AdapterCoffeeHouse getAdapter() {
        return adapter;
    }

    public void setAdapter(AdapterCoffeeHouse adapter) {
        this.adapter = adapter;
    }
}
