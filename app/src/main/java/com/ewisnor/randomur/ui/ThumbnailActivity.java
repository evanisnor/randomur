package com.ewisnor.randomur.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ewisnor.randomur.R;
import com.ewisnor.randomur.application.RandomurLogger;
import com.ewisnor.randomur.iface.OnNetworkInterruptionListener;
import com.ewisnor.randomur.iface.OnThumbnailClickListener;
import com.ewisnor.randomur.ui.fragment.FullImageDialogFragment;
import com.ewisnor.randomur.ui.fragment.NetworkInterruptionFragment;
import com.ewisnor.randomur.ui.fragment.ThumbnailGridFragment;

/**
 * Main activity for Randomur. Loads the ThumbnailGridFragment, which fetches random thumbnails from Imgur.
 * This activity handles grid clicks so the FullImageDialogFragment can be shown to display full size
 * images in full screen.
 *
 * Created by evan on 2015-01-02.
 */
public class ThumbnailActivity extends ActionBarActivity implements OnThumbnailClickListener, OnNetworkInterruptionListener {

    private FullImageDialogFragment fullImageDialog;
    private ThumbnailGridFragment thumbnailGridFragment;
    private NetworkInterruptionFragment networkInterruptionFragment;

    public ThumbnailActivity() {
        this.thumbnailGridFragment = new ThumbnailGridFragment();
        this.networkInterruptionFragment = new NetworkInterruptionFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnail);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, thumbnailGridFragment, "thumbnailGridFragment")
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_thumbnail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            ThumbnailGridFragment f = (ThumbnailGridFragment) getSupportFragmentManager().findFragmentByTag("thumbnailGridFragment");
            f.refresh();
        }

        return super.onOptionsItemSelected(item);
    }

    public void showNetworkInterruption() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(thumbnailGridFragment);
        transaction.add(R.id.container, networkInterruptionFragment, "networkInterruptionFragment");
        transaction.commit();
    }

    public void hideNetworkInterruption() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(networkInterruptionFragment);
        transaction.add(R.id.container, thumbnailGridFragment, "thumbnailGridFragment");
        transaction.commit();
    }

    @Override
    public void onThumbnailClick(int id) {
        RandomurLogger.debug("Clicked on thumbnail " + id);
        fullImageDialog = new FullImageDialogFragment();
        Bundle b = new Bundle();
        b.putInt(FullImageDialogFragment.IMAGE_ID_ARGUMENT, id);
        fullImageDialog.setArguments(b);
        fullImageDialog.show(getFragmentManager(), "");
    }

    @Override
    public void onNetworkInterruption() {
        if (fullImageDialog != null) {
            fullImageDialog.dismiss();
        }

        showNetworkInterruption();
    }
}
