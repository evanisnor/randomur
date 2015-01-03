package com.ewisnor.randomur.application;

import android.util.Log;

/**
 * Created by evan on 2015-01-02.
 */
public class RandomurLogger {
    private static String LOGGER_TAG = "randomur";

    public static void info(String text) {
        if (Log.isLoggable(LOGGER_TAG, Log.INFO)) {
            Log.i(LOGGER_TAG, text);
        }
    }

    public static void debug(String text) {
        if (Log.isLoggable(LOGGER_TAG, Log.DEBUG)) {
            Log.d(LOGGER_TAG, text);
        }
    }

    public static void error(String text) {
        if (Log.isLoggable(LOGGER_TAG, Log.ERROR)) {
            Log.e(LOGGER_TAG, text);
        }
    }
}
