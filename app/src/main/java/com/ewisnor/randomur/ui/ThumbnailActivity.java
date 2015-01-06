package com.ewisnor.randomur.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.ewisnor.randomur.R;
import com.ewisnor.randomur.application.RandomurApp;
import com.ewisnor.randomur.application.RandomurLogger;
import com.ewisnor.randomur.iface.OnNetworkInterruptionListener;
import com.ewisnor.randomur.iface.OnThumbnailClickListener;
import com.ewisnor.randomur.receiver.NetworkConnectivityReceiver;
import com.ewisnor.randomur.ui.fragment.FullImageDialogFragment;
import com.ewisnor.randomur.ui.fragment.NetworkInterruptionFragment;
import com.ewisnor.randomur.ui.fragment.ThumbnailGridFragment;

/**
 * Main activity for Randomur. Loads the ThumbnailGridFragment, which fetches random thumbnails from Imgur.
 * This activity handles grid clicks so the FullImageDialogFragment can be shown to display full size
 * images in full screen.
 *
 * Also shows a fragment upon network connectivity loss.
 *
 * Created by evan on 2015-01-02.
 */
public class ThumbnailActivity extends ActionBarActivity implements OnThumbnailClickListener, OnNetworkInterruptionListener {
    private static final String STATE_IS_CONNECTED = "stateIsConnected";

    private static final String NETWORK_INTERRUPTION_FRAGMENT_TAG = "networkInterruptionFragment";
    private static final String THUMBNAIL_GRID_FRAGMENT_TAG = "thumbnailGridFragment";
    private static final String FULL_IMAGE_DIALOGFRAGMENT_TAG = "fullImageDialogFragment";

    private Boolean isConnected;

    public ThumbnailActivity() {
        this.isConnected = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnail);

        getFragmentManager().beginTransaction()
                .add(R.id.container, new NetworkInterruptionFragment(), NETWORK_INTERRUPTION_FRAGMENT_TAG)
                .add(R.id.container, new ThumbnailGridFragment(), THUMBNAIL_GRID_FRAGMENT_TAG)
                .commit();

        Boolean isConnected = NetworkConnectivityReceiver.isConnected(this);
        if (savedInstanceState != null) {
            isConnected = savedInstanceState.getBoolean(STATE_IS_CONNECTED);
        }
        if (!isConnected) {
            showNetworkInterruption();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_IS_CONNECTED, isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(connectivityReceiver, new IntentFilter(NetworkConnectivityReceiver.CONNECTIVITY_INTENT_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(connectivityReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_thumbnail, menu);

        MenuItem refresh = menu.findItem(R.id.action_refresh);
        refresh.setEnabled(isConnected);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            ThumbnailGridFragment f = (ThumbnailGridFragment) getFragmentManager().findFragmentByTag(THUMBNAIL_GRID_FRAGMENT_TAG);
            f.refresh();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onThumbnailClick(int id) {
        RandomurLogger.debug("Clicked on thumbnail " + id);
        FullImageDialogFragment fullImageDialog = new FullImageDialogFragment();
        Bundle b = new Bundle();
        b.putInt(FullImageDialogFragment.IMAGE_ID_ARGUMENT, id);
        fullImageDialog.setArguments(b);
        fullImageDialog.show(getFragmentManager(), FULL_IMAGE_DIALOGFRAGMENT_TAG);
    }

    /**
     * Set the connectivity status for the Activity. Will show the network interruption fragment
     * if the connection was lost, and will hide it when the device is connected.
     * @param isConnected True if connected to the internet
     */
    private void setConnectivityStatus(Boolean isConnected) {
        if (this.isConnected && !isConnected) {
            showNetworkInterruption();
        }
        else if (!this.isConnected && isConnected) {
            hideNetworkInterruption();
        }
        this.isConnected = isConnected;
    }

    /**
     * Show the Network Interruption fragment and hide the thumbnail grid.
     */
    private void showNetworkInterruption() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        Fragment thumbnailGridFragment = fm.findFragmentByTag(THUMBNAIL_GRID_FRAGMENT_TAG);
        Fragment networkInterruptionFragment = fm.findFragmentByTag(NETWORK_INTERRUPTION_FRAGMENT_TAG);
        if (thumbnailGridFragment != null && !thumbnailGridFragment.isHidden()) {
            transaction.hide(thumbnailGridFragment);
        }
        if (networkInterruptionFragment != null) {
            transaction.show(networkInterruptionFragment);
        }

        transaction.commit();
        invalidateOptionsMenu();
    }

    /**
     * Hide the Network Interruption fragment and show the thumbnail grid.
     */
    private void hideNetworkInterruption() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        Fragment thumbnailGridFragment = fm.findFragmentByTag(THUMBNAIL_GRID_FRAGMENT_TAG);
        Fragment networkInterruptionFragment = fm.findFragmentByTag(NETWORK_INTERRUPTION_FRAGMENT_TAG);
        if (networkInterruptionFragment != null && !networkInterruptionFragment.isHidden()) {
            transaction.hide(networkInterruptionFragment);
        }
        if (thumbnailGridFragment != null) {
            transaction.show(thumbnailGridFragment);
        }

        transaction.commit();

        // The app probably started without a network connection, so the thumbnail cache is empty.
        // Do a refresh to grab them automatically so the user isn't left with a blank grid.
        if (((RandomurApp) getApplication()).getImageCache().countThumbnails() == 0) {
            ((ThumbnailGridFragment)thumbnailGridFragment).refresh();
        }
        invalidateOptionsMenu();
    }

    @Override
    public void onNetworkInterruption() {
        Boolean isConnected = NetworkConnectivityReceiver.isConnected(this);
        FullImageDialogFragment fullImageDialog = (FullImageDialogFragment) getFragmentManager().findFragmentByTag(FULL_IMAGE_DIALOGFRAGMENT_TAG);
        if (fullImageDialog != null && !isConnected) {
            fullImageDialog.dismiss();
        }
        setConnectivityStatus(isConnected);
    }

    /**
     * Receiver for connectivity intents (broadcast by the NetworkConnectivityReceiver)
     */
    private BroadcastReceiver connectivityReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(NetworkConnectivityReceiver.CONNECTIVITY_INTENT_ACTION)) {
                Boolean isConnected = intent.getBooleanExtra(NetworkConnectivityReceiver.IS_CONNECTED_EXTRA, false);
                setConnectivityStatus(isConnected);
            }
        }

    };
}
