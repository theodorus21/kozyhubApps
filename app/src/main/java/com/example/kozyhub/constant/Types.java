package com.example.kozyhub.constant;

import com.example.kozyhub.model.Cafe;
import com.example.kozyhub.model.Menu;
import com.example.kozyhub.model.Property;
import com.example.kozyhub.model.Response;

import java.lang.reflect.Type;

public class Types {
    public static final Type ResponseCafe = com.squareup.moshi.Types.newParameterizedType(Response.class, Cafe.class);
    public static final Type ResponseMenu = com.squareup.moshi.Types.newParameterizedType(Response.class, Menu.class);
    public static final Type ResponseProperty = com.squareup.moshi.Types.newParameterizedType(Response.class, Property.class);
}
