package com.ewisnor.randomur.application;

import android.util.Log;

/**
 * Basic logger class.
 *
 * Created by evan on 2015-01-02.
 */
public class RandomurLogger {
    /**
     * Tag to be used when logging - "randomur"
     */
    private static final String LOGGER_TAG = "randomur";

    /**
     * Print INFO-level logging statement
     * @param text Text to log
     */
    public static void info(String text) {
        if (Log.isLoggable(LOGGER_TAG, Log.INFO)) {
            Log.i(LOGGER_TAG, text);
        }
    }

    /**
     * Print DEBUG-level logging statement
     * @param text Text to log
     */
    public static void debug(String text) {
        if (Log.isLoggable(LOGGER_TAG, Log.DEBUG)) {
            Log.d(LOGGER_TAG, text);
        }
    }

    /**
     * Print ERROR-level logging statement
     * @param text Text to log
     */
    public static void error(String text) {
        if (Log.isLoggable(LOGGER_TAG, Log.ERROR)) {
            Log.e(LOGGER_TAG, text);
        }
    }
}
