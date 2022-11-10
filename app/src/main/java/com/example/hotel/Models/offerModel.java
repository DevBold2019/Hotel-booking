package com.example.hotel.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class offerModel implements Parcelable {

    String title,description;
    int image;

    public offerModel(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;
    }

    protected offerModel(Parcel in) {
        title = in.readString();
        description = in.readString();
        image = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<offerModel> CREATOR = new Creator<offerModel>() {
        @Override
        public offerModel createFromParcel(Parcel in) {
            return new offerModel(in);
        }

        @Override
        public offerModel[] newArray(int size) {
            return new offerModel[size];
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
}
