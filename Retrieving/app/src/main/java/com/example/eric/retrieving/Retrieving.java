package com.example.eric.retrieving;

import com.firebase.client.Firebase;

/**
 * Created by eric on 19/04/16.
 */
public class Retrieving extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
