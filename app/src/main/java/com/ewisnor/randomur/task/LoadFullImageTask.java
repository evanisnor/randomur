package com.ewisnor.randomur.task;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.ewisnor.randomur.application.RandomurApp;
import com.ewisnor.randomur.application.RandomurLogger;
import com.ewisnor.randomur.imgur.model.GalleryImage;
import com.ewisnor.randomur.util.HttpHelper;
import com.ewisnor.randomur.util.Pair;

import org.apache.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by evan on 2015-01-03.
 */
public class LoadFullImageTask extends AsyncTask<Integer, Void, Bitmap> {

    private RandomurApp appContext;
    private ImageView view;

    public LoadFullImageTask(Context context, ImageView view) {
        this.appContext = (RandomurApp) context.getApplicationContext();
        this.view = view;
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

                appContext.getImageCache().saveFullImage(imageId, image);
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
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (view != null && bitmap != null) {
            view.setImageBitmap(bitmap);
        }
    }
}
