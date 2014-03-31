package com.example.jamiefirstapp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "The new Service was Created", Toast.LENGTH_LONG).show();

    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w("myApp", "Starting Service...");
        Toast.makeText(this, " Service Started", Toast.LENGTH_LONG).show();
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.w("myApp", "Destroying Service...");
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();


    }


}
