package com.example.kozyhub.model;

import android.os.Parcel;

import java.util.List;

public class SuccessLogin extends Success{
    public List<User> session_info;

    public SuccessLogin(String info, String result) {
        super(info, result);
    }

    protected SuccessLogin(Parcel in) {
        super(in);
    }
}
