package com.example.hotel.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class orderModel implements Parcelable {

    String quantity,name,price,description;
    int image;


    public orderModel(String quantity, String name, String price, String description, int image) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }


    protected orderModel(Parcel in) {
        quantity = in.readString();
        name = in.readString();
        price = in.readString();
        description = in.readString();
        image = in.readInt();
    }

    public static final Creator<orderModel> CREATOR = new Creator<orderModel>() {
        @Override
        public orderModel createFromParcel(Parcel in) {
            return new orderModel(in);
        }

        @Override
        public orderModel[] newArray(int size) {
            return new orderModel[size];
        }
    };

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(quantity);
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(description);
        parcel.writeInt(image);
    }
}
