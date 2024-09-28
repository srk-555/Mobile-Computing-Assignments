package com.example.alarmy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {
    int receiveCount = 0;
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Intent.ACTION_BATTERY_LOW:
                Log.d("INFO", "Battary Low Broadcast Received");
                Toast.makeText(context, "Battary Low Broadcast Received", Toast.LENGTH_SHORT).show();
                receiveCount++;
            case Intent.ACTION_POWER_CONNECTED:
                Log.d("INFO", "Power Connected Broadcast Received");
                Toast.makeText(context, "Power Connected Broadcast Received", Toast.LENGTH_SHORT).show();
                receiveCount++;
            case Intent.ACTION_BATTERY_OKAY:
                Log.d("INFO", "Battery Okay Broadcast Received");
                Toast.makeText(context, "Battery Okay Broadcast Received", Toast.LENGTH_SHORT).show();
                receiveCount++;
            case TelephonyManager.ACTION_PHONE_STATE_CHANGED:
                String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
                if(stateStr!=null && stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                    Log.d("INFO", "Incoming Call Broadcast Received");
                    Toast.makeText(context, "Incoming Call Broadcast Received", Toast.LENGTH_SHORT).show();
                    receiveCount++;
                }
            default:
                // Ignore unrecognized intent
                break;
        }
        if (receiveCount >= 1) {
//            Intent serviceIntent = new Intent(context, AlarmService.class);
            context.stopService(new Intent(context, AlarmService.class));
        }
    }
}
