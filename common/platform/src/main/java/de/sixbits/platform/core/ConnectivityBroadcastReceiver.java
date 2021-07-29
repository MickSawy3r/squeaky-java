package de.sixbits.platform.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ConnectivityBroadcastReceiver extends BroadcastReceiver {
    private final ConnectivityCallback mConnectivityCallback;

    ConnectivityBroadcastReceiver(ConnectivityCallback connectivityCallback) {
        mConnectivityCallback = connectivityCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (context == null) {
            return;
        }
        mConnectivityCallback.onConnectionChange(new NetworkHandler(context).isNetworkAvailable());
    }
}
