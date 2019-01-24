package com.example.joshuamugisha.github.Application;

import android.app.Application;

import com.example.joshuamugisha.github.util.NetworkReceiver;

public class MyApplication extends Application {

    private static MyApplication mInstance;

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public void setConnectivityListener(NetworkReceiver.NetworkReceiverListener listener) {
        NetworkReceiver.networkReceiverListener = listener;
    }
}
