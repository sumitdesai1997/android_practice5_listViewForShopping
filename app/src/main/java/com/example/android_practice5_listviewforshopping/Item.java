package com.example.android_practice5_listviewforshopping;

public class Item {
    private String name;
    private String img;
    private double price;

    public Item(String name, String img, double price) {
        this.name = name;
        this.img = img;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
