package com.katfang.test;

import android.app.Application;
import com.firebase.client.Firebase;

public class Data extends Application {

    private String username = "";
    public static final String FIREBASE_URL = "https://fiery-fire-4044.firebaseio.com";
    private Firebase ref;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}