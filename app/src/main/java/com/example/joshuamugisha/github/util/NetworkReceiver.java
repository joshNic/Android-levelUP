package com.example.joshuamugisha.github.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.joshuamugisha.github.Application.MyApplication;

public class NetworkReceiver extends BroadcastReceiver {

    public static NetworkReceiverListener networkReceiverListener;

    public NetworkReceiver() {
        super();
    }

    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    public void onReceive(Context context, Intent arg1) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (networkReceiverListener != null) {
            networkReceiverListener.onNetworkConnectionChanged(isConnected);
        }
    }

    public interface NetworkReceiverListener {
        void onNetworkConnectionChanged(boolean isConnected);
    }

}


