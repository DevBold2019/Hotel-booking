package com.example.hotel.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class itemsModel implements Parcelable {

    int id,image;
    String name,price,description;

    public itemsModel(int id, int image, String name, String price, String description) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    protected itemsModel(Parcel in) {
        id = in.readInt();
        image = in.readInt();
        name = in.readString();
        price = in.readString();
        description = in.readString();
    }

    public static final Creator<itemsModel> CREATOR = new Creator<itemsModel>() {
        @Override
        public itemsModel createFromParcel(Parcel in) {
            return new itemsModel(in);
        }

        @Override
        public itemsModel[] newArray(int size) {
            return new itemsModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(image);
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(description);
    }
}
