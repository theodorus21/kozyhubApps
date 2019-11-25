package com.example.kozyhub.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductData extends Category implements Parcelable {
    private String shortDescription, longDescription;

    public ProductData(String name, String image, String shortDescription, String longDescription) {
        super(name, image);
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    protected ProductData(Parcel in) {
        super(in);
        shortDescription = in.readString();
        longDescription = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(shortDescription);
        dest.writeString(longDescription);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }
}
