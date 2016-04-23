package com.example.eric.crowdio;

import com.firebase.client.Firebase;

/**
 * Created by eric on 18/04/16.
 */
public class Crowdio extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
