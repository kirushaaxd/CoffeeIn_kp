package com.example.coffeein_kp;

import java.util.ArrayList;

public class ClientOrders {
    private String ClientId;
    private ArrayList<String> OrdersId;

    public ClientOrders (String ClientId){
        this.ClientId = ClientId;
        this.OrdersId = new ArrayList<>();
    }

    public ClientOrders (String ClientId, ArrayList<String> OrdersId) {
        this.ClientId = ClientId;
        this.OrdersId = OrdersId;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public ArrayList<String> getOrdersId() {
        return OrdersId;
    }

    public void setOrdersId(ArrayList<String> ordersId) {
        OrdersId = ordersId;
    }

    public void addOrder(String OrderId){
        this.OrdersId.add(OrderId);
    }
}
