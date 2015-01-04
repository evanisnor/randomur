package com.ewisnor.randomur.data;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.ewisnor.randomur.application.RandomurLogger;

/**
 * In-memory caching for thumbnails and full-size images.
 * Advice taken from: https://developer.android.com/training/displaying-bitmaps/cache-bitmap.html
 *
 * Created by evan on 2015-01-03.
 */
public class ImageCache {
    private final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

    /* Thumbnail cache uses a size limit of 1/16th of available memory */
    private final int thumbnailCacheSize = maxMemory / 16;

    /* Full image cache uses a size limit of 1/8th of available memory */
    private final int fullImageCacheSize = maxMemory / 8;

    private LruCache<Integer, Bitmap> thumbnailCache;
    private LruCache<Integer, Bitmap> fullImageCache;

    public ImageCache() {
        this.thumbnailCache = new LruCache<>(thumbnailCacheSize);
        this.fullImageCache = new LruCache<>(fullImageCacheSize);
    }

    /**
     * Cache the provided thumbnail in memory
     * @param id
     * @param image
     */
    public void saveThumbnail(Integer id, Bitmap image) {
        if (this.thumbnailCache.get(id) == null) {
            this.thumbnailCache.put(id, image);
            RandomurLogger.debug("Cached thumbnail " + id);
        }
    }

    /**
     * Get an thumbnail by ID
     * @param id
     * @return
     */
    public Bitmap getThumbnail(Integer id) {
        return this.thumbnailCache.get(id);
    }

    /**
     * Returns the total number of thumbnails cached
     * @return
     */
    public Integer countThumbnails() {
        return this.thumbnailCache.size();
    }

    /**
     * Cache the provided full image in memory
     * @param id
     * @param image
     */
    public void saveFullImage(Integer id, Bitmap image) {
        if (this.fullImageCache.get(id) == null) {
            this.fullImageCache.put(id, image);
        }
    }

    /**
     * Get a full image by ID
     * @param id
     * @return
     */
    public Bitmap getFullImage(Integer id) {
        return this.fullImageCache.get(id);
    }

    /**
     * Clear all images from memory cache
     */
    public void cleanUp() {
        this.thumbnailCache.evictAll();
        this.fullImageCache.evictAll();
    }
}
