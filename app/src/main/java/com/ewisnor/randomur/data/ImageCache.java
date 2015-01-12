package com.ewisnor.randomur.data;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.ewisnor.randomur.application.RandomurLogger;
import com.ewisnor.randomur.imgur.model.GalleryImage;

/**
 * In-memory caching for thumbnails and image metadata.
 * Stores thumbnails and metadata in separate LruCache instances.
 * ID values are universal, but not enforced.
 *
 * Reference: https://developer.android.com/training/displaying-bitmaps/cache-bitmap.html
 *
 * Created by evan on 2015-01-03.
 */
public class ImageCache {
    private final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    private final int MAX_WRITE_ATTEMPTS = 3;

    /* Thumbnail cache uses a size limit of 1/4 of available memory */
    private final int thumbnailCacheSize = maxMemory / 4;

    /* Image meta cache uses a size limit of 1/4 of available memory */
    private final int imageMetaCacheSize = maxMemory / 4;

    private LruCache<Integer, Bitmap> thumbnailCache;
    private LruCache<Integer, GalleryImage> imageMetaCache;
    private Integer currentPage;
    private Integer firstVisiblePosition;

    public ImageCache() {
        this.thumbnailCache = new LruCache<>(thumbnailCacheSize);
        this.imageMetaCache = new LruCache<>(imageMetaCacheSize);
        this.currentPage = 0;
        this.firstVisiblePosition = 0;
    }

    /**
     * Cache the provided thumbnail in memory
     * @param id The universal ID of the image
     * @param image Bitmap image thumbnail to save
     */
    public void saveThumbnail(Integer id, Bitmap image) {
        saveThumbnail(id, image, 1);
    }

    /**
     * Private overload for handling thumbnail saves when the LruCache memory space has been exceeded.
     * Attempts to clear and re-save up to MAX_WRITE_ATTEMPTS.
     * @param id The universal ID of the image
     * @param image Bitmap image thumbnail to save
     * @param attempt Attempt number, starting at 1.
     */
    private void saveThumbnail(Integer id, Bitmap image, Integer attempt) {
        if (this.thumbnailCache.get(id) == null && image != null && attempt <= MAX_WRITE_ATTEMPTS) {
            try {
                this.thumbnailCache.put(id, image);
            }
            catch (OutOfMemoryError oome) {
                RandomurLogger.debug("Insufficient free memory for saving a thumbnail. Cleaning up resources and trying again. Attempt #" + attempt);
                this.cleanUp();
                this.saveThumbnail(id, image, attempt++);
            }
        }
        else if (attempt > MAX_WRITE_ATTEMPTS) {
            RandomurLogger.error("Failed to free enough memory to save thumbnail " + id);
        }
    }

    /**
     * Get an thumbnail by ID
     * @param id The universal ID of the image
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
     * Cache the metadata about an image separately from thumbnails and full images
     * @param id The universal ID of the image
     * @param meta Metadata to store
     */
    public void saveImageMeta(Integer id, GalleryImage meta) {
        this.saveImageMeta(id, meta, 1);
    }

    /**
     * Private overload for handling image meta saves when the LruCache memory space has been exceeded.
     * Attempts to clear and re-save up to MAX_WRITE_ATTEMPTS.
     * @param id The universal ID of the image
     * @param meta Metadata to store
     * @param attempt Attempt number, starting at 1.
     */
    private void saveImageMeta(Integer id, GalleryImage meta, Integer attempt) {
        if (this.imageMetaCache.get(id) == null && meta != null && attempt <= MAX_WRITE_ATTEMPTS) {
            try {
                this.imageMetaCache.put(id, meta);
            }
            catch (OutOfMemoryError oome) {
                RandomurLogger.debug("Insufficient free memory for saving image meta. Cleaning up resources and trying again.");
                this.cleanUp();
                this.saveImageMeta(id, meta, attempt++);
            }
        }
        else if (attempt > MAX_WRITE_ATTEMPTS) {
            RandomurLogger.error("Failed to free enough memory to save image meta for ID " + id);
        }
    }

    /**
     * Get the metadata about an image
     * @param id The universal ID of the image
     * @return
     */
    public GalleryImage getImageMeta(Integer id) {
        return this.imageMetaCache.get(id);
    }

    /**
     * Get the current page of images
     * @return
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * Set the current page of images
     * @param currentPage
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Set the first visible image position
     * @param position First visible position
     */
    public void setFirstVisiblePosition(Integer position) {
        this.firstVisiblePosition = position;
    }

    /**
     * Get the previous first visible image position
     * @return First visible position
     */
    public int getFirstVisiblePosition() {
        return this.firstVisiblePosition;
    }

    /**
     * Clear all images from memory cache
     */
    public void cleanUp() {
        this.thumbnailCache.evictAll();
        this.imageMetaCache.evictAll();
        this.currentPage = 0;
        this.firstVisiblePosition = 0;
    }
}
