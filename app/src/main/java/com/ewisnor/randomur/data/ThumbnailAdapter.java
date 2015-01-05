package com.ewisnor.randomur.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.ewisnor.randomur.application.RandomurApp;
import com.ewisnor.randomur.iface.OnThumbnailClickListener;

/**
 * Adapter to feed the GridView in the ThumbnailGridFragment. Reads thumbnails from
 * the ImageCache instance stored in the Application context.
 *
 * Each thumbnail is rendered as an ImageView and their clicks are forwarded to the
 * OnThumbnailClickListener instance provided in the constructor. (ThumbnailActivity)
 *
 * Created by evan on 2015-01-03.
 */
public class ThumbnailAdapter extends BaseAdapter {
    private RandomurApp appContext;
    private OnThumbnailClickListener thumbnailClickListener;
    private Integer imageWidth;

    /**
     * Create a new ThumbnailAdapter
     * @param context Context reference -- need this to get the ImageCache instance
     * @param thumbnailClickListener Click listener for thumbnails
     * @param imageWidth Width, in pixels, of each image when rendered
     */
    public ThumbnailAdapter(Context context, OnThumbnailClickListener thumbnailClickListener, Integer imageWidth) {
        this.appContext = (RandomurApp) context.getApplicationContext();
        this.thumbnailClickListener = thumbnailClickListener;
        this.imageWidth = imageWidth;
    }

    /**
     * Set the width of images.
     * @param imageWidth Width, in pixels, of each image when rendered
     */
    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    @Override
    public int getCount() {
        return this.appContext.getImageCache().countThumbnails();
    }

    @Override
    public Object getItem(int position) {
        return this.appContext.getImageCache().getThumbnail(position);
    }

    @Override
    public boolean isEnabled(int position) {
        return this.appContext.getImageCache().getThumbnail(position) != null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(appContext);
            imageView.setLayoutParams(new GridView.LayoutParams(imageWidth, imageWidth));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap((Bitmap) getItem(position));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Send the click position back to the ThumbnailActivity so it can
                //launch the FullImageDialogFragment
                thumbnailClickListener.onThumbnailClick(position);
            }
        });
        return imageView;
    }
}
