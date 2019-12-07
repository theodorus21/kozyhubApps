package com.example.kozyhub.util.session;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.kozyhub.R;
import com.example.kozyhub.model.User;
import com.google.gson.Gson;

public class SessionManager {
    private static User user;
    private static boolean isLoggedIn;

    private static final SessionManager ourInstance = new SessionManager();

    public static SessionManager getInstance() {
        return ourInstance;
    }

    private SessionManager() {
        this.user = null;
    }

    public User getUser(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        String sf = sharedPref.getString(activity.getString(R.string.app_name) + "user", "");

        if ("".equals(sf)) {
            user = null;
        } else {
            user = new Gson().fromJson(sf, User.class);
        }

        return user;
    }

    public void setUser(Activity activity, User u) {
        user = u;
        isLoggedIn = true;

        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(activity.getString(R.string.app_name) + "user", new Gson().toJson(user));
        editor.apply();
    }


    public boolean isIsLoggedIn(Activity activity) {
        if (!isLoggedIn) {
            user = getUser(activity);
            if (user != null) {
                isLoggedIn = !"".equals(user.PersonEmail);
            }
        }

        return isLoggedIn;
    }

    public void invalidateUser(Activity activity) {
        user = null;
        isLoggedIn = false;

        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(activity.getString(R.string.app_name) + "user");
        editor.clear();
        editor.apply();
    }
}
