package de.sixbits.platform.helpers;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

import androidx.fragment.app.FragmentActivity;

public class FragmentHelper {
    void attachConnectivityBroadcastReceiver(
            FragmentActivity fragmentActivity,
            BroadcastReceiver connectivityBroadcastReceiver
    ) {
        fragmentActivity.registerReceiver(
                connectivityBroadcastReceiver,
                new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        );
        fragmentActivity.registerReceiver(
                connectivityBroadcastReceiver,
                new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED")
        );
    }
}
