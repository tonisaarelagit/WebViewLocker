package com.worldstar.webviewlocker;

import android.app.Application;

import com.worldstar.webviewlocker.utils.LocalStorage;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initSingletons();
    }

    private void initSingletons() {
        LocalStorage.init(this);
    }
}
