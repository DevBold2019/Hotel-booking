package com.example.hotel.Models;

public class dummyOrders  {

    String title,price,description;
    int quantity,image;

    public dummyOrders(String title, String price, String description, int quantity, int image) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
