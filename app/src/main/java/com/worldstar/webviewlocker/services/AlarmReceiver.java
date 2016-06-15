package com.worldstar.webviewlocker.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.worldstar.webviewlocker.utils.Constants;
import com.worldstar.webviewlocker.utils.LocalStorage;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("AlarmReceiver", "onReceive Event Fired");

        LocalStorage.getInstance().put(Constants.ALREADY_SHOWN, false);
    }
}
