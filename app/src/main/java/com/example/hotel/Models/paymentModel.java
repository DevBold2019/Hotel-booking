package com.example.hotel.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class paymentModel implements Parcelable {

    int image;
    String title;

    public paymentModel(int image, String title) {
        this.image = image;
        this.title = title;
    }

    protected paymentModel(Parcel in) {
        image = in.readInt();
        title = in.readString();
    }

    public static final Creator<paymentModel> CREATOR = new Creator<paymentModel>() {
        @Override
        public paymentModel createFromParcel(Parcel in) {
            return new paymentModel(in);
        }

        @Override
        public paymentModel[] newArray(int size) {
            return new paymentModel[size];
        }
    };

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(image);
        parcel.writeString(title);
    }
}
