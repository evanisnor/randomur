package com.ewisnor.randomur.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;

import com.ewisnor.randomur.application.RandomurApp;
import com.ewisnor.randomur.application.RandomurLogger;
import com.ewisnor.randomur.iface.OnImageDownloadedListener;
import com.ewisnor.randomur.iface.OnNetworkInterruptionListener;
import com.ewisnor.randomur.imgur.model.GalleryImage;
import com.ewisnor.randomur.util.HttpHelper;
import com.ewisnor.randomur.util.ImageHelper;
import com.ewisnor.randomur.util.Pair;

import org.apache.http.HttpStatus;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Background task for downloading a full size image, as selected by the user.
 * Calls the ImageHelper class for scaling if the image is too large.
 *
 * Created by evan on 2015-01-03.
 */
public class FetchFullImageTask extends AsyncTask<Integer, Void, Bitmap> {

    private RandomurApp appContext;
    private OnImageDownloadedListener imageDownloadedListener;
    private OnNetworkInterruptionListener networkInterruptionListener;

    /**
     * Create a new Fetch task.
     * @param context Context reference
     * @param imageDownloadedListener A listener to be called when download is complete
     * @param networkInterruptionListener A listener for network interruptions
     */
    public FetchFullImageTask(Context context, OnImageDownloadedListener imageDownloadedListener, OnNetworkInterruptionListener networkInterruptionListener) {
        this.appContext = (RandomurApp) context.getApplicationContext();
        this.imageDownloadedListener = imageDownloadedListener;
        this.networkInterruptionListener = networkInterruptionListener;
    }

    @Override
    protected Bitmap doInBackground(Integer... params) {
        Integer imageId = params[0];
        GalleryImage meta = appContext.getImageCache().getImageMeta(imageId);

        if (meta == null) {
            RandomurLogger.error("Image meta does not exist for ID " + imageId);
            return null;
        }

        Pair<InputStream, Integer> result;
        try {
            result = HttpHelper.GetByteStream(meta.getFullSizeDownloadUrl());
        }
        catch (IOException ioe) {
            RandomurLogger.error("Failed to fetch full size image " + meta.getId());
            return null;
        }

        InputStream imageStream = result.getFirst();
        Integer status = result.getSecond();

        if (status == HttpStatus.SC_OK || status == HttpStatus.SC_ACCEPTED) {
            return decodeImage(imageStream);
        }
        else if (status == HttpStatus.SC_NOT_FOUND) {
            RandomurLogger.info("Image " + meta.getId() + " has been deleted from Imgur.");
        }
        else {
            RandomurLogger.error("Imgur did not respond favorably to the request. (Status: " + status + ")");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageDownloadedListener.OnImageDownloaded(bitmap);
    }

    /**
     * Decode a bitmap from an InputStream. The image bounds will be parsed to determine
     * if it needs to be scaled down.
     *
     * @param is InputStream holding the bitmap
     * @return A bitmap image, possibly scaled down
     */
    private Bitmap decodeImage(InputStream is) {
        InputStream stream = new BufferedInputStream(is);
        Pair<Integer, Integer> bounds = ImageHelper.getImageBounds(stream);

        try {
            stream.reset();
        }
        catch (IOException ioe) {
            RandomurLogger.error("Failed to reset image stream position. " + ioe.getMessage());
        }

        Bitmap image;
        if (bounds.getFirst() > ImageHelper.MAX_IMAGE_BOUNDS || bounds.getSecond() > ImageHelper.MAX_IMAGE_BOUNDS) {
            RandomurLogger.debug("Image is too large. Scaling it down.");
            image = ImageHelper.getScaledDownImage(stream);
        }
        else {
            image = BitmapFactory.decodeStream(stream);
        }

        try {
            stream.close();
        }
        catch (IOException ioe) {
            RandomurLogger.error("Failed to clean up image stream. " + ioe.getMessage());
        }

        return image;
    }
}
