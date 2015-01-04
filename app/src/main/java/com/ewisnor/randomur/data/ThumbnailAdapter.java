package com.ewisnor.randomur.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.ewisnor.randomur.application.RandomurApp;

/**
 * Created by evan on 2015-01-03.
 */
public class ThumbnailAdapter extends BaseAdapter {
    private RandomurApp appContext;

    public ThumbnailAdapter(Context context) {
        this.appContext = (RandomurApp) context.getApplicationContext();
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(appContext);
            imageView.setLayoutParams(new GridView.LayoutParams(250,250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap((Bitmap)getItem(position));
        return imageView;
    }
}
