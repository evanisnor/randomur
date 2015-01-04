package com.ewisnor.randomur.task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.ewisnor.randomur.application.RandomurApp;
import com.ewisnor.randomur.application.RandomurLogger;
import com.ewisnor.randomur.data.ThumbnailAdapter;
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
public class CacheThumbnailsTask extends AsyncTask<Integer, Integer, Boolean> {

    private RandomurApp appContext;
    private ThumbnailAdapter adapter;

    public CacheThumbnailsTask(Context context, ThumbnailAdapter adapter) {
        this.appContext = (RandomurApp) context.getApplicationContext();
        this.adapter = adapter;
    }

    @Override
    protected Boolean doInBackground(Integer... params) {
        Integer pageNumber = 0;
        Integer thumbId = appContext.getImageCache().countThumbnails();

        if (params.length > 0 && params[0] != null) {
            pageNumber = params[0];
        }

        RandomurLogger.info("Fetching random thumbnail page " + pageNumber);

        try {
            BasicImages randomImageMeta = ImgurApi.getRandomImageMeta(pageNumber);
            Integer count = randomImageMeta.getData().length;
            Integer futureTotal = appContext.getImageCache().countThumbnails() + count;

            for (GalleryImage imageMeta : randomImageMeta.getData()) {
                if (imageMeta.isAlbum() || imageMeta.isNsfw()) {
                    continue;
                }

                String url = imageMeta.getThumbnailDownloadUrl();
                Pair<InputStream, Integer> result = HttpHelper.GetByteStream(url);
                InputStream thumbnailStream = result.getFirst();
                Integer status = result.getSecond();

                if (status == HttpStatus.SC_OK || status == HttpStatus.SC_ACCEPTED) {
                    Bitmap thumbnail = BitmapFactory.decodeStream(thumbnailStream);
                    if (thumbnail == null) {
                        RandomurLogger.error("Failed to fetch image. (Status: " + status + ")");
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
            return true;
        }
        catch (IOException ioe) {
            RandomurLogger.error("Failed to cache thumbnails" + ioe.getMessage());
        }

        return false;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        adapter.notifyDataSetChanged();
    }
}
