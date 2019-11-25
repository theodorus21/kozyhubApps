package com.example.kozyhub.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.kozyhub.util.string.StringHelper;

public class Menu implements Parcelable {
    public int PkMenuCafe;
    public String MenuName, MenuPict, MenuDesc;
    public float MenuPrice;

    protected Menu(Parcel in) {
        PkMenuCafe = in.readInt();
        MenuName = in.readString();
        MenuPict = in.readString();
        MenuDesc = in.readString();
        MenuPrice = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(PkMenuCafe);
        dest.writeString(MenuName);
        dest.writeString(MenuPict);
        dest.writeString(MenuDesc);
        dest.writeFloat(MenuPrice);
    }

    public String formatMenuPrice() {
        return StringHelper.FormatRupiah(this.MenuPrice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };
}
