package com.ewisnor.randomur.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.ewisnor.randomur.data.ThumbnailAdapter;
import com.ewisnor.randomur.iface.OnThumbnailClickListener;
import com.ewisnor.randomur.imgur.ImgurApi;
import com.ewisnor.randomur.task.CacheThumbnailsTask;

public class ThumbnailGridFragment extends Fragment implements GridView.OnScrollListener {
    /* Desired grid column width in DP */
    private static final Integer COLUMN_WIDTH_DP = 125;

    private ThumbnailAdapter adapter;
    private Integer page;
    private Boolean userScrolled;
    private OnThumbnailClickListener thumbnailClickListener;

    public static ThumbnailGridFragment newInstance() {
        ThumbnailGridFragment fragment = new ThumbnailGridFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ThumbnailGridFragment() {
        this.page = 0;
        this.userScrolled = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thumbnail_grid, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        GridView gridview = (GridView) view.findViewById(R.id.imageGrid);

        Integer columnWidth = getColumnWidth();
        gridview.setColumnWidth(columnWidth);
        adapter = new ThumbnailAdapter(getActivity().getApplicationContext(), thumbnailClickListener, columnWidth);
        gridview.setOnScrollListener(this);
        gridview.setAdapter(adapter);

        fetchThumbnails();
    }

    /**
     * Calculates the best column width for the grid view. Used to scale images to the desired size
     * across all device resolutions.
     * @return Calculated column width
     */
    public Integer getColumnWidth() {
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            thumbnailClickListener = (OnThumbnailClickListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnThumbnailClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
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

    /**
     * Launch an Asynctask to fetch more thumbnails. Task will notify the adapter when a new
     * thumbnail is available.
     */
    private void fetchThumbnails() {
        if (adapter != null && page <= ImgurApi.MAX_RANDOM_PAGES) {
            new CacheThumbnailsTask(getActivity().getApplicationContext(), adapter).execute(page++);
        }
    }
}
