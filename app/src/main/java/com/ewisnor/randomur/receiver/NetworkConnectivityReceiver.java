package com.ewisnor.randomur.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.ewisnor.randomur.application.RandomurLogger;

/**
 * Broadcast receiver for listening to network change events.
 *
 * Created by evan on 2015-01-05.
 */
public class NetworkConnectivityReceiver extends BroadcastReceiver {
    public static final String CONNECTIVITY_INTENT_ACTION = "com.ewisnor.randomur.intent.connectivity";
    public static final String IS_CONNECTED_EXTRA = "isConnected";

    @Override
    public void onReceive(Context context, Intent intent) {
        Boolean isConnected = isConnected(context);

        RandomurLogger.info("Detected a change in network connectivity: " + ((isConnected) ? "Connected" : "Not Connected"));

        Intent connectionIntent = new Intent(CONNECTIVITY_INTENT_ACTION);
        connectionIntent.putExtra(IS_CONNECTED_EXTRA, isConnected);
        context.sendBroadcast(connectionIntent);
    }

    /**
     * Helper method for getting the current connectivity state.
     * @param context Context reference
     * @return True if connected
     */
    public static Boolean isConnected(Context context) {
        ConnectivityManager networkConnectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (networkConnectivity.getActiveNetworkInfo() == null) {
            return false;
        }
        return networkConnectivity.getActiveNetworkInfo().isConnected();
    }
}
