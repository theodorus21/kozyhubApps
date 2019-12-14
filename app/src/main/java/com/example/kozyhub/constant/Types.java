package com.example.kozyhub.constant;

import com.example.kozyhub.model.Cafe;
import com.example.kozyhub.model.Catering;
import com.example.kozyhub.model.Menu;
import com.example.kozyhub.model.News;
import com.example.kozyhub.model.Notification;
import com.example.kozyhub.model.Property;
import com.example.kozyhub.model.Response;
import com.example.kozyhub.model.Success;

import java.lang.reflect.Type;

public class Types {
    public static final Type ResponseCafe = com.squareup.moshi.Types.newParameterizedType(Response.class, Cafe.class);
    public static final Type ResponseCatering = com.squareup.moshi.Types.newParameterizedType(Response.class, Catering.class);
    public static final Type ResponseMenu = com.squareup.moshi.Types.newParameterizedType(Response.class, Menu.class);
    public static final Type ResponseProperty = com.squareup.moshi.Types.newParameterizedType(Response.class, Property.class);
    public static final Type ResponseNews = com.squareup.moshi.Types.newParameterizedType(Response.class, News.class);
    public static final Type ResponseSuccess = com.squareup.moshi.Types.newParameterizedType(Success.class, Property.class);
    public static final Type ResponseNotification = com.squareup.moshi.Types.newParameterizedType(Response.class, Notification.class);
}
