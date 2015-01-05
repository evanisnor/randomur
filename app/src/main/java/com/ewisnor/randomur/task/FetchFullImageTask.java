package com.ewisnor.randomur.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.ewisnor.randomur.application.RandomurApp;
import com.ewisnor.randomur.application.RandomurLogger;
import com.ewisnor.randomur.iface.OnImageDownloadedListener;
import com.ewisnor.randomur.iface.OnNetworkInterruptionListener;
import com.ewisnor.randomur.imgur.model.GalleryImage;
import com.ewisnor.randomur.util.HttpHelper;
import com.ewisnor.randomur.util.Pair;

import org.apache.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by evan on 2015-01-03.
 */
public class FetchFullImageTask extends AsyncTask<Integer, Void, Bitmap> {

    private RandomurApp appContext;
    private OnImageDownloadedListener imageDownloadedListener;
    private OnNetworkInterruptionListener networkInterruptionListener;

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

        try {
            Pair<InputStream, Integer> result = HttpHelper.GetByteStream(meta.getFullSizeDownloadUrl());
            InputStream imageStream = result.getFirst();
            Integer status = result.getSecond();

            if (status == HttpStatus.SC_OK || status == HttpStatus.SC_ACCEPTED) {
                Bitmap image = BitmapFactory.decodeStream(imageStream);
                if (image == null) {
                    RandomurLogger.error("Failed to fetch full size image. (Status: " + status + ")");
                    return null;
                }
                return image;
            }
            else if (status == HttpStatus.SC_NOT_FOUND) {
                RandomurLogger.info("Image " + meta.getId() + " has been deleted from Imgur.");
            }
            else {
                RandomurLogger.error("Imgur did not respond favorably to the request. (Status: " + status + ")");
            }
        }
        catch (IOException ioe) {
            RandomurLogger.error("Failed to fetch full size image " + meta.getId());
            if (networkInterruptionListener != null) {
                networkInterruptionListener.onNetworkInterruption();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (imageDownloadedListener != null) {
            imageDownloadedListener.OnImageDownloaded(bitmap);
        }
    }
}
