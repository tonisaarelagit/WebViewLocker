package com.worldstar.webviewlocker.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LocalStorage {

    private final SharedPreferences mSharedPreferences;

    private static LocalStorage sInstance;

    private LocalStorage(final Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static LocalStorage init(final Context context) {
        if (sInstance == null) {
            sInstance = new LocalStorage(context);
        }
        return sInstance;
    }

    public static LocalStorage getInstance() {
        return sInstance;
    }

    /**
     * Get boolean preference with key
     *
     * @param key Key in string
     * @return boolean value of preference
     */
    public boolean getFlagValue(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    /**
     * Get saved string preference
     *
     * @param key Key in string type
     * @return String preference
     */
    public String getStringPreference(String key) {
        return mSharedPreferences.getString(key, null);
    }

    /**
     * Convenience method to get integer preference value.
     *
     * @param key Key in string.,
     * @return Integer value for key.
     */
    public int getIntPreference(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    /**
     * Convenience method to get long preference value.
     *
     * @param key Key in string.,
     * @return Long value for key.
     */
    public long getLongPreference(String key) {
        return mSharedPreferences.getLong(key, 0);
    }

    /**
     * Set string to Preferences
     *
     * @param key   Key in string type
     * @param value String preference value to be saved
     */
    public void put(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * Set long to Preferences
     *
     * @param key   Key in string type
     * @param value Long preference value to be saved
     */
    public void put(String key, long value) {
        mSharedPreferences.edit().putLong(key, value).apply();
    }

    /**
     * Set boolean preference
     *
     * @param key   Key in string type
     * @param value Flag value in boolean type
     */
    public void put(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * Set long to Preferences
     *
     * @param key   Key in string type
     * @param value Long preference value to be saved
     */
    public void put(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    /**
     * Remove defaults for key.
     *
     * @param key Key in string type.
     */
    public void remove(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    public boolean isExist(String key) {
        return mSharedPreferences.contains(key);
    }
}
