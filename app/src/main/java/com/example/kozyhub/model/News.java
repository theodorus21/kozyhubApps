package com.example.kozyhub.model;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {
    public int PkNews;
    public String NewsTitle;
    public String NewsDesc;
    public String NewsPict;
    public int NewsFlagType;

    protected News(Parcel in) {
        PkNews = in.readInt();
        NewsTitle = in.readString();
        NewsDesc = in.readString();
        NewsPict = in.readString();
        NewsFlagType = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(PkNews);
        dest.writeString(NewsTitle);
        dest.writeString(NewsDesc);
        dest.writeString(NewsPict);
        dest.writeInt(NewsFlagType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
