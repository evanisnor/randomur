package com.ewisnor.randomur.data;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ewisnor.randomur.application.RandomurApp;

/**
 * Created by evan on 2015-01-03.
 */
public class ThumbnailAdapter extends BaseAdapter {
    private Context context;
    private RandomurApp appContext;

    public ThumbnailAdapter(Context context) {
        this.context = context;
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
        return null;
    }
}
