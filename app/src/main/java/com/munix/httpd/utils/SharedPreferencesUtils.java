package com.munix.httpd.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by munix on 18/12/2015.
 */
public class SharedPreferencesUtils {
    public static SharedPreferences getSharedPreferenceManager(Context mContext) {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    public static void writeSharedPreference(Context mContext, String key, String value) {
        SharedPreferences settings = getSharedPreferenceManager(mContext);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void writeSharedPreference(Context mContext, String key, Boolean value) {
        SharedPreferences settings = getSharedPreferenceManager(mContext);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void writeSharedPreference(Context mContext, String key, long value) {
        SharedPreferences settings = getSharedPreferenceManager(mContext);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void writeSharedPreference(Context mContext, String key, int value) {
        SharedPreferences settings = getSharedPreferenceManager(mContext);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static Boolean readSharedPreference(Context mContext, String key, Boolean defaultValue) {
        SharedPreferences settings = getSharedPreferenceManager(mContext);
        return settings.getBoolean(key, defaultValue);
    }

    public static long readSharedPreference(Context mContext, String key, long defaultValue) {
        SharedPreferences settings = getSharedPreferenceManager(mContext);
        return settings.getLong(key, defaultValue);
    }

    public static int readSharedPreference(Context mContext, String key, int defaultValue) {
        SharedPreferences settings = getSharedPreferenceManager(mContext);
        return settings.getInt(key, defaultValue);
    }

    public static String readSharedPreference(Context mContext, String key, String defaultValue) {
        SharedPreferences settings = getSharedPreferenceManager(mContext);
        return settings.getString(key, defaultValue);
    }
}
