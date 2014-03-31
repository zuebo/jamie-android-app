package com.example.jamiefirstapp;

import android.app.Activity;
import android.content.*;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref = getSharedPreferences("com.exmple.myfirstapp.service_status", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("is_service_running", false);
        editor.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }

    /**
     * Called when the user clicks the Start Service button
     */
    public void startMyService(View view) {
        startService(new Intent(this, MyService.class));
        SharedPreferences pref = getSharedPreferences("com.exmple.myfirstapp.service_status", MODE_PRIVATE);
        boolean is_running = pref.getBoolean("is_service_running", false);
        if (!is_running) {
            //register wifi receiver
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
            registerReceiver(mWifiBroadcastReceiver, intentFilter);
            //update preference
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("is_service_running", true);
            editor.commit();
        }


    }

    /**
     * Called when the user clicks the Stop Service button
     */
    public void stopMyService(View view) {
        SharedPreferences pref = getSharedPreferences("com.exmple.myfirstapp.service_status", MODE_PRIVATE);
        boolean is_running = pref.getBoolean("is_service_running", false);
        if (is_running) {
            unregisterReceiver(mWifiBroadcastReceiver);
            stopService(new Intent(this, MyService.class));
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("is_service_running", false);
            editor.commit();
        }
    }

    //handle wifi broadcast receiver
    private WifiBroadcastReceiver mWifiBroadcastReceiver = new WifiBroadcastReceiver();
}
