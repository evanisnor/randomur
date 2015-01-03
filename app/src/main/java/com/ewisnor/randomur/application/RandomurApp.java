package com.ewisnor.randomur.application;

import android.app.Application;

import com.ewisnor.randomur.data.ImageCache;

/**
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
        this.imageCache.cleanUp();
        //TODO Show low memory dialog
    }

    public ImageCache getImageCache() {
        return imageCache;
    }
}
