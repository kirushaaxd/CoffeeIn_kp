package com.example.coffeein_kp;

public class OrderProduct {
    private int Count;
    private String Product;
    private int Price;

    public OrderProduct (int Count, String Product, int Price) {
        this.Count = Count;
        this.Product = Product;
        this.Price = Price;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
