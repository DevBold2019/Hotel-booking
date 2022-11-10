package com.example.hotel.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class restaurantsModel implements Parcelable {

    String title,description;
    int image;

    public restaurantsModel(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    protected restaurantsModel(Parcel in) {
        title = in.readString();
        description = in.readString();
        image = in.readInt();
    }

    public static final Creator<restaurantsModel> CREATOR = new Creator<restaurantsModel>() {
        @Override
        public restaurantsModel createFromParcel(Parcel in) {
            return new restaurantsModel(in);
        }

        @Override
        public restaurantsModel[] newArray(int size) {
            return new restaurantsModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeInt(image);
    }
}
