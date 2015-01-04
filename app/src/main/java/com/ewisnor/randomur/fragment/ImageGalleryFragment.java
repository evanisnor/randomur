package com.ewisnor.randomur.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;

import com.ewisnor.randomur.R;
import com.ewisnor.randomur.data.ThumbnailAdapter;
import com.ewisnor.randomur.imgur.ImgurApi;
import com.ewisnor.randomur.task.CacheThumbnailsTask;

public class ImageGalleryFragment extends Fragment implements GridView.OnScrollListener, AdapterViewCompat.OnItemClickListener {

    private ThumbnailAdapter adapter;
    private Integer page;
    private Boolean userScrolled;

    public static ImageGalleryFragment newInstance() {
        ImageGalleryFragment fragment = new ImageGalleryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ImageGalleryFragment() {
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_gallery, container, false);
        adapter = new ThumbnailAdapter(getActivity().getApplicationContext());
        GridView gridview = (GridView) view.findViewById(R.id.imageGrid);
        gridview.setOnScrollListener(this);
        gridview.setAdapter(adapter);

        fetchThumbnails();

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
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

    @Override
    public void onItemClick(AdapterViewCompat<?> adapterViewCompat, View view, int i, long l) {

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
