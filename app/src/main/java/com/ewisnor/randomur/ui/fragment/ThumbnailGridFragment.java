package com.ewisnor.randomur.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.GridView;

import com.ewisnor.randomur.R;
import com.ewisnor.randomur.application.RandomurApp;
import com.ewisnor.randomur.application.RandomurLogger;
import com.ewisnor.randomur.data.ThumbnailAdapter;
import com.ewisnor.randomur.iface.OnCacheThumbnailsFinishedListener;
import com.ewisnor.randomur.iface.OnNetworkInterruptionListener;
import com.ewisnor.randomur.iface.OnThumbnailClickListener;
import com.ewisnor.randomur.imgur.ImgurApi;
import com.ewisnor.randomur.task.CacheThumbnailsTask;

/**
 * View for displaying a grid of random thumbnails from Imgur. Binds the ThumbnailAdapter to the gridview.
 * Listens for the user to scroll to the bottom and then grabs another page of thumbnails using the
 * CacheThumbnailsTask.
 *
 * Created by evan on 2015-01-03.
 */
public class ThumbnailGridFragment extends Fragment implements GridView.OnScrollListener, OnCacheThumbnailsFinishedListener {
    /* Desired grid column width in DP */
    private static final Integer COLUMN_WIDTH_DP = 125;

    private static final String STATE_PAGE = "statePage";

    private View view;
    private RandomurApp appContext;
    private ThumbnailAdapter adapter;
    private Integer page;
    private Boolean userScrolled;
    private OnThumbnailClickListener thumbnailClickListener;
    private OnNetworkInterruptionListener networkInterruptionListener;
    private AsyncTask runningTask;
    private Boolean isSetToRefresh;

    public ThumbnailGridFragment() {
        this.page = 0;
        this.userScrolled = false;
        this.isSetToRefresh = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_thumbnail_grid, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.imageGrid);
        Integer columnWidth = getColumnWidth();
        adapter = new ThumbnailAdapter(getActivity().getApplicationContext(), thumbnailClickListener, columnWidth);
        gridview.setColumnWidth(columnWidth);
        gridview.setOnScrollListener(this);
        gridview.setAdapter(adapter);

        if (savedInstanceState == null) {
            fetchThumbnails();
        }
        else {
            onRestoreInstanceState(savedInstanceState);
        }
        return view;
    }

    /**
     * Re-fetch the thumbnails. Cancel a running task if needed.
     */
    public void refresh() {
        if (runningTask != null) {
            RandomurLogger.info("Cancelling a running thumbnail fetch so the grid may be refreshed");
            ((CacheThumbnailsTask) runningTask).cancel();
            isSetToRefresh = true;
        }
        else {
            RandomurLogger.info("Refreshing the thumbnail grid");
            appContext.getImageCache().cleanUp();
            page = 0;
            isSetToRefresh = false;
            fetchThumbnails();
        }
    }

    /**
     * Calculates the best column width for the grid view. Used to scale images to the desired size
     * across all device resolutions.
     * @return Calculated column width
     */
    private Integer getColumnWidth() {
        Resources r = getResources();
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Integer width = size.x;

        float desiredImageWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, COLUMN_WIDTH_DP, r.getDisplayMetrics());
        Integer numColumns = (int)Math.ceil(width / desiredImageWidth);

        return width / numColumns;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_PAGE, page);
    }

    /**
     * To be called when savedInstanceState is not null -- Probably because the device screen orientation
     * changed.
     * @param savedInstanceState State bundle
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        page = savedInstanceState.getInt(STATE_PAGE);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        appContext = (RandomurApp) activity.getApplicationContext();
        try {
            thumbnailClickListener = (OnThumbnailClickListener) activity;
            networkInterruptionListener = (OnNetworkInterruptionListener) activity;
        }
        catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement all interfaces. " + e.getMessage());
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem + visibleItemCount >= totalItemCount - visibleItemCount && userScrolled) {
            fetchThumbnails();
            userScrolled = false;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == GridView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            userScrolled = true;
        }
    }

    @Override
    public void onCacheThumbnailsFinished() {
        runningTask = null;
        if (isSetToRefresh) {
            refresh();
        }
    }

    /**
     * Launch an Asynctask to fetch more thumbnails. Task will notify the adapter when a new
     * thumbnail is available. The ThumbnailActivity is set up to handle network problems.
     */
    private void fetchThumbnails() {
        if (adapter != null && page <= ImgurApi.MAX_RANDOM_PAGES && runningTask == null) {
            runningTask = new CacheThumbnailsTask(getActivity().getApplicationContext(), adapter, networkInterruptionListener, this).execute(page++);
        }
    }
}
