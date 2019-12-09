package com.example.kozyhub.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class CateringBooking implements Parcelable {
    public class Booking {
        public Katering katering;
        public int quantity;

        public Booking(Katering katering, int quantity) {
            this.katering = katering;
            this.quantity = quantity;
        }
    }

    public Map<Integer, Booking> bookings;

    public CateringBooking() {
        bookings = new HashMap<>();
    }

    protected CateringBooking(Parcel in) {
    }

    public void update(int pk, Katering katering, int qty) {
        bookings.put(pk, new Booking(katering, qty));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CateringBooking> CREATOR = new Creator<CateringBooking>() {
        @Override
        public CateringBooking createFromParcel(Parcel in) {
            return new CateringBooking(in);
        }

        @Override
        public CateringBooking[] newArray(int size) {
            return new CateringBooking[size];
        }
    };
}
