package com.ewisnor.randomur.iface;

import android.graphics.Bitmap;

/**
 * Implementers may receive a Bitmap of an image once it has been downloaded.
 *
 * Created by evan on 2015-01-04.
 */
public interface OnImageDownloadedListener {

    /**
     * Handle a downloaded image in Bitmap form.
     * @param image image bitmap
     */
    void OnImageDownloaded(Bitmap image);
}
