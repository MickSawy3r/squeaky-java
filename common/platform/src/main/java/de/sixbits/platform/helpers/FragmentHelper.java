package de.sixbits.platform.helpers;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.fragment.app.FragmentActivity;

import de.sixbits.platform.core.BaseFragment;
import de.sixbits.platform.core.ConnectivityBroadcastReceiver;
import de.sixbits.platform.core.ContainerActivity;

public class FragmentHelper {

    static public <T extends BaseFragment> T getLoadedFragment(
            T fragment,
            String dataKey,
            Parcelable payload
    ) {
        // Create the data payload
        Bundle bundle = new Bundle();
        bundle.putParcelable(dataKey, payload);

        // Add the payload to the fragment
        fragment.setArguments(bundle);

        // And return the fragment with the payload
        return fragment;
    }

    static public void attachConnectivityBroadcastReceiver(
            FragmentActivity activity, ConnectivityBroadcastReceiver receiver
    ) {
        activity.registerReceiver(
                receiver,
                new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        );
        activity.registerReceiver(
                receiver,
                new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED")
        );
    }

    static public void deAttachConnectivityBroadcastReceiver(
            FragmentActivity activity, ConnectivityBroadcastReceiver receiver
    ) {
        activity.unregisterReceiver(receiver);
    }
}
