package com.example.kozyhub.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductData implements Parcelable {
    private String name, shortDescription, longDescription;

    public ProductData(String name, String shortDescription, String longDescription) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    protected ProductData(Parcel in) {
        name = in.readString();
        shortDescription = in.readString();
        longDescription = in.readString();
    }

    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @Override
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(shortDescription);
        dest.writeString(longDescription);
    }
}
