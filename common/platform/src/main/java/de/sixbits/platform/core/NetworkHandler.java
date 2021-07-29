package de.sixbits.platform.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import de.sixbits.platform.helpers.ContextHelper;

public class NetworkHandler {
    Context mContext;

    NetworkHandler(Context context) {
        mContext = context;
    }

    Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = ContextHelper.getConnectivityManager(mContext);

        Network network = connectivityManager.getActiveNetwork();
        if (network == null) {
            return false;
        }

        NetworkCapabilities activeNetwork = connectivityManager.getNetworkCapabilities(network);
        if (activeNetwork == null) {
            return false;
        }

        if (activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            return true;
        } else if (activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            return true;
        } else if (activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
            return true;
        } else if (activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
            return true;
        } else return activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH);
    }
}
