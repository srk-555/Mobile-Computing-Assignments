package com.example.alarmy;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
import android.os.Process;
import java.util.ArrayList;
import java.util.Date;

public class AlarmService extends Service {
    //    private static final int NOTIFICATION_ID = 123;
//    private static final String CHANNEL_ID = "MyForegroundServiceChannel";
    private Receiver myReceiver;
    //    private Map<Long, Runnable> mAlarmRunnables;
    private MediaPlayer mMediaPlayer;

    private HandlerThread handlerThread;
    private Handler handler;
    private static final int N_ID = 321;

    @Override
    public void onCreate() {
        super.onCreate();

        // Below 4 lines of code referred from MC Tutorial 2
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "Alarm Service", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        myReceiver = new Receiver();
        // Register battery low receiver
        IntentFilter batteryLowFilter = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        registerReceiver(myReceiver, batteryLowFilter);

        // Register power connected receiver
        IntentFilter powerConnectedFilter = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        registerReceiver(myReceiver, powerConnectedFilter);

        // Register battery okay receiver
        IntentFilter batteryOkayFilter = new IntentFilter(Intent.ACTION_BATTERY_OKAY);
        registerReceiver(myReceiver, batteryOkayFilter);

        // Register incoming call receiver
        IntentFilter incomingCallFilter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        registerReceiver(myReceiver, incomingCallFilter);

        handlerThread = new HandlerThread("TimeCheckHandlerThread", Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Service Started");
        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        final String CHANNEL_ID = "Foreground Service";

        // Below 18 lines of code referred from MC Tutorial 2
        NotificationChannel notificationChannel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Log.d("FORE.....", "Got inside 1st build-if ");
            notificationChannel = new NotificationChannel(CHANNEL_ID,CHANNEL_ID, NotificationManager.IMPORTANCE_LOW);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        }

        Notification.Builder builder = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            Log.d("FORE.....", "Got inside 3rd build-if ");
            builder = new Notification.Builder(this, "channel_id").
                    setContentText("Foreground service is running")
                    .setContentTitle("Foreground")
                    .setSmallIcon(R.drawable.ic_launcher_background);
        }
        startForeground(N_ID, builder.build());


//        ArrayList<Integer> hours = intent.getIntegerArrayListExtra("hours");
//        ArrayList<Integer> mins = intent.getIntegerArrayListExtra("mins");
        ArrayList<Integer> hours = (ArrayList<Integer>) MainFragment.hourArray.clone();
        ArrayList<Integer> mins = (ArrayList<Integer>) MainFragment.minArray.clone();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Date d=new Date();
                int currMinutes = d.getMinutes();
                currMinutes += d.getHours()*60;
//                Log.d("currentMinutes......", String.valueOf(currMinutes));
//                Log.d("myMinutes......", String.valueOf((hours.get(0)*60)+(mins.get(0))));
                // Logic here
                for(int i=0; i<hours.size();i++){
                    if((hours.get(i)*60)+(mins.get(i)) == currMinutes){
//                        Log.d("inside if.....", "Matched inside if ..........");
                        hours.set(i,-1);
                        mins.set(i,-1);
                        Toast.makeText(getApplicationContext(), "Alarm Ringing - Alarm time matched current time", Toast.LENGTH_SHORT).show();
                        Log.d("INFO", "Alarm Ringing (Alarm time matched with current time)");
                        playAlarm();
                    }
                }
                handler.postDelayed(this, 10000);
            }
        };
        handler.postDelayed(runnable, 10000);
        //stopForeground(true);
        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }
    @Override
    public void onDestroy() {
        MainFragment.started = false;
        Toast.makeText(getApplicationContext(), "Service Stopped", Toast.LENGTH_SHORT).show();
        Log.d("INFO", "Service Stopped");
        // Remove all alarm runnables from the handler
//        for (Runnable alarmRunnable : mAlarmRunnables.values()) {
//            mServiceHandler.removeCallbacks(alarmRunnable);
//        }

        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
        // Quit the handler thread
        stopForeground(true);
        handlerThread.quit();
        unregisterReceiver(myReceiver);
    }

    private void playAlarm() {
        // Code to play the alarm goes here
        // If the media player is already playing, stop it
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

        // Create a new media player to play the alarm sound
        mMediaPlayer = MediaPlayer.create(this, R.raw.alarm_sound);
        mMediaPlayer.setLooping(true);

        // Start playing the alarm sound
        mMediaPlayer.start();

        // Stop playing the alarm sound after 10 seconds
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    mMediaPlayer.release();
                    mMediaPlayer = null;
                }
            }
        }, 10000);
    }
}
