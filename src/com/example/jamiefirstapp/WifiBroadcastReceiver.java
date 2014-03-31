package com.example.jamiefirstapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Created by zuebo on 3/19/14.
 */
public class WifiBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w("myApp", "Receiving Broadcast...");
        final String action = intent.getAction();
        if (action.equals(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION)) {
            if (intent.getBooleanExtra(WifiManager.EXTRA_SUPPLICANT_CONNECTED, true)) {
                //do stuff
                Log.w("myApp", "Jamie's wifi network is connected");
            } else {
                // wifi connection was lost
                Log.w("myApp", "Jamie's wifi network is lost now");
            }
        }
    }
}
