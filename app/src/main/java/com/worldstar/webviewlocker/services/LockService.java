package com.worldstar.webviewlocker.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.worldstar.webviewlocker.activities.MainActivity;
import com.worldstar.webviewlocker.utils.Constants;
import com.worldstar.webviewlocker.utils.LocalStorage;

import java.util.Calendar;

public class LockService extends Service {

    AlarmManager alarmManager;
    PendingIntent alarmIntent;

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("LockService", "New Intent");

            if (shouldShowWebView()) {
                Intent mIntent = new Intent(LockService.this, MainActivity.class);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mIntent);

                LocalStorage.getInstance().put(Constants.ALREADY_SHOWN, true);
            }
        }

        private boolean shouldShowWebView() {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            int seconds = c.get(Calendar.SECOND);

            boolean alreadyShown = LocalStorage.getInstance().getFlagValue(Constants.ALREADY_SHOWN);
            return hour >= 9 && minute >= 0 && seconds >= 0 && !alreadyShown;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mReceiver, filter);

        // Setup alarm at 1:00 am with daily interval.
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 1);

        alarmManager.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
