package com.ewisnor.randomur.application;

import android.app.Application;

import com.ewisnor.randomur.data.ImageCache;

/**
 * Application context. Contains an instance of ImageCache that can be referenced
 * by other components.
 *
 * Created by evan on 2015-01-03.
 */
public class RandomurApp extends Application {

    private ImageCache imageCache;

    @Override
    public void onCreate() {
        super.onCreate();
        this.imageCache = new ImageCache();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        RandomurLogger.info("OS has reported low memory. Cleaning up image cache.");
        this.imageCache.cleanUp();
    }

    public ImageCache getImageCache() {
        return imageCache;
    }
}
