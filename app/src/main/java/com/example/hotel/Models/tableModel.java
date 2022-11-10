package com.example.hotel.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class tableModel implements Parcelable {

    Boolean occupied;
    String table_number,seats;

    public tableModel(Boolean occupied, String table_number, String seats) {

        this.occupied = occupied;
        this.table_number = table_number;
        this.seats = seats;
    }

    protected tableModel(Parcel in) {
        byte tmpOccupied = in.readByte();
        occupied = tmpOccupied == 0 ? null : tmpOccupied == 1;
        table_number = in.readString();
        seats = in.readString();
    }

    public static final Creator<tableModel> CREATOR = new Creator<tableModel>() {
        @Override
        public tableModel createFromParcel(Parcel in) {
            return new tableModel(in);
        }

        @Override
        public tableModel[] newArray(int size) {
            return new tableModel[size];
        }
    };

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (occupied == null ? 0 : occupied ? 1 : 2));
        parcel.writeString(table_number);
        parcel.writeString(seats);
    }
}
