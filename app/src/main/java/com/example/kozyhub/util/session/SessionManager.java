package com.example.kozyhub.util.session;

import com.example.kozyhub.model.User;

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

    public static User getUser() {
        return user;
    }

    public static void setUser(User u) {
        user = u;
        isLoggedIn = true;
    }

    public static boolean isIsLoggedIn() {
        return isLoggedIn;
    }

    public static void invalidateUser() {
        user = null;
        isLoggedIn = false;
    }
}
