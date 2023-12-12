package com.example.coffeein_kp;

public class OrderProduct {
    private int Count;
    private Product Product;
    private int Price;

    public OrderProduct (int Count, Product Product, int Price) {
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

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product product) {
        Product = product;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
