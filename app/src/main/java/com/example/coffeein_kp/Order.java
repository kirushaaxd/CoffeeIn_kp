package com.example.coffeein_kp;

import java.util.ArrayList;

public class Order {
    private String Address;
    private String OrderTime;
    private String OrderDate;
    private int FinalQuantity;
    private ArrayList<OrderProduct> Products;

    public Order() {}

    public Order(String Address, String OrderTime, String OrderDate, int FinalQuantity, ArrayList<OrderProduct> Products) {
        this.Address = Address;
        this.OrderTime = OrderTime;
        this.OrderDate = OrderDate;
        this.FinalQuantity = FinalQuantity;
        this.Products = Products;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getOrderTime() {
        return OrderTime;
    }

    public void setOrderTime(String orderTime) {
        OrderTime = orderTime;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public int getFinalQuantity() {
        return FinalQuantity;
    }

    public void setFinalQuantity(int finalQuantity) {
        FinalQuantity = finalQuantity;
    }

    public ArrayList<OrderProduct> getProducts() {
        return Products;
    }

    public void setProducts(ArrayList<OrderProduct> products) {
        Products = products;
    }
}
