package com.example.coffeein_kp;

public class CoffeeHouse {
    private String Name;
    private String Address;
    private String CityId;
    private String TimeOpen;
    private String TimeClose;
    private String ShopId;

    public CoffeeHouse(String Name, String Address, String CityId, String TimeOpen, String TimeClose, String ShopId) {
        this.Name = Name;
        this.Address = Address;
        this.CityId = CityId;
        this.TimeOpen = TimeOpen;
        this.TimeClose = TimeClose;
        this.ShopId = ShopId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
        CityId = cityId;
    }

    public String getTimeOpen() {
        return TimeOpen;
    }

    public void setTimeOpen(String timeOpen) {
        TimeOpen = timeOpen;
    }

    public String getTimeClose() {
        return TimeClose;
    }

    public void setTimeClose(String timeClose) {
        TimeClose = timeClose;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }
}
