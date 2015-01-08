package com.ewisnor.randomur.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.ewisnor.randomur.application.RandomurApp;
import com.ewisnor.randomur.application.RandomurLogger;
import com.ewisnor.randomur.data.ThumbnailAdapter;
import com.ewisnor.randomur.iface.OnCacheThumbnailsFinishedListener;
import com.ewisnor.randomur.iface.OnNetworkInterruptionListener;
import com.ewisnor.randomur.imgur.ImgurApi;
import com.ewisnor.randomur.imgur.model.BasicImages;
import com.ewisnor.randomur.imgur.model.GalleryImage;
import com.ewisnor.randomur.util.HttpHelper;
import com.ewisnor.randomur.util.Pair;

import org.apache.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;

/**
 * AsyncTask for caching random thumbnails from Imgur
 *
 * Created by evan on 2015-01-03.
 */
public class CacheThumbnailsTask extends AsyncTask<Integer, Integer, Void> {

    private RandomurApp appContext;
    private ThumbnailAdapter adapter;
    private OnNetworkInterruptionListener networkInterruptionListener;
    private OnCacheThumbnailsFinishedListener cacheThumbnailsFinishedListener;
    private Boolean isRunning;

    /**
     * Create a new CacheThumbnailsTask
     * @param context Context reference
     * @param adapter Reference to the active ThumbnailAdapter instance
     * @param networkInterruptionListener A listener for network interruptions
     * @param cacheThumbnailsFinishedListener A listener to be called on completion
     */
    public CacheThumbnailsTask(Context context, ThumbnailAdapter adapter,
                               OnNetworkInterruptionListener networkInterruptionListener,
                               OnCacheThumbnailsFinishedListener cacheThumbnailsFinishedListener) {
        this.appContext = (RandomurApp) context.getApplicationContext();
        this.adapter = adapter;
        this.networkInterruptionListener = networkInterruptionListener;
        this.cacheThumbnailsFinishedListener = cacheThumbnailsFinishedListener;
    }

    /**
     * Cancel this task.
     */
    public void cancel() {
        isRunning = false;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        isRunning = true;
        Integer pageNumber = 0;
        Integer thumbId = appContext.getImageCache().countThumbnails();

        if (params.length > 0 && params[0] != null) {
            pageNumber = params[0];
        }

        RandomurLogger.info("Fetching random thumbnail page " + pageNumber);

        BasicImages randomImageMeta = getRandomImageMeta(pageNumber);
        if (randomImageMeta == null) {
            return null;
        }

        Integer count = randomImageMeta.getData().length;
        Integer futureTotal = appContext.getImageCache().countThumbnails() + count;

        for (GalleryImage imageMeta : randomImageMeta.getData()) {
            if (!isRunning) {
                break;
            }
            else if (imageMeta.isAlbum() || imageMeta.isNsfw()) {
                continue;
            }

            String url = imageMeta.getThumbnailDownloadUrl();
            Pair<InputStream, Integer> result;
            try {
                result = HttpHelper.GetByteStream(url);
            }
            catch (IOException ioe) {
                RandomurLogger.error("Failed to cache thumbnails: " + ioe.getMessage());
                if (networkInterruptionListener != null) {
                    networkInterruptionListener.onNetworkInterruption();
                }
                break;
            }

            InputStream thumbnailStream = result.getFirst();
            Integer status = result.getSecond();

            if (status == HttpStatus.SC_OK || status == HttpStatus.SC_ACCEPTED) {
                Bitmap thumbnail = BitmapFactory.decodeStream(thumbnailStream);
                if (thumbnail == null) {
                    RandomurLogger.error("Failed to decode thumbnail");
                    continue;
                }

                appContext.getImageCache().saveThumbnail(thumbId, thumbnail);
                appContext.getImageCache().saveImageMeta(thumbId, imageMeta);
                thumbId++;
                publishProgress(thumbId - futureTotal / count);
            }
            else if (status == HttpStatus.SC_NOT_FOUND) {
                RandomurLogger.info("Image " + imageMeta.getId() + " has been deleted from Imgur. Skipping.");
            }
            else {
                RandomurLogger.error("Imgur did not respond favorably to the request. (Status: " + status + ")");
            }
        }

        appContext.getImageCache().setCurrentPage(pageNumber);

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (cacheThumbnailsFinishedListener != null) {
            cacheThumbnailsFinishedListener.onCacheThumbnailsFinished();
        }
    }

    /**
     * Get the random image meta for the specified page number.
     * @param pageNumber Imgur random endpoint page
     * @return Random image meta
     */
    private BasicImages getRandomImageMeta(Integer pageNumber) {
        try {
            return ImgurApi.getRandomImageMeta(pageNumber);
        }
        catch (IOException ioe) {
            RandomurLogger.error("Failed to cache thumbnails: " + ioe.getMessage());
            if (networkInterruptionListener != null) {
                networkInterruptionListener.onNetworkInterruption();
            }
            return null;
        }
    }
}
