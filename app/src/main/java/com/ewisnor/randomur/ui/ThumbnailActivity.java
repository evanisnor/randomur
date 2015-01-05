package com.ewisnor.randomur.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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

    private FullImageDialogFragment fullImageDialog;
    private ThumbnailGridFragment thumbnailGridFragment;
    private NetworkInterruptionFragment networkInterruptionFragment;
    private Boolean isConnected;

    public ThumbnailActivity() {
        this.thumbnailGridFragment = new ThumbnailGridFragment();
        this.networkInterruptionFragment = new NetworkInterruptionFragment();
        this.isConnected = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnail);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, networkInterruptionFragment, "networkInterruptionFragment")
                    .add(R.id.container, thumbnailGridFragment, "thumbnailGridFragment")
                    .commit();
            setConnectivityStatus(NetworkConnectivityReceiver.isConnected(this));
        }
        else {
            Boolean isConnected = savedInstanceState.getBoolean(STATE_IS_CONNECTED);
            setConnectivityStatus((isConnected == null) ? this.isConnected : isConnected);
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

    @Override
    public void onThumbnailClick(int id) {
        RandomurLogger.debug("Clicked on thumbnail " + id);
        fullImageDialog = new FullImageDialogFragment();
        Bundle b = new Bundle();
        b.putInt(FullImageDialogFragment.IMAGE_ID_ARGUMENT, id);
        fullImageDialog.setArguments(b);
        fullImageDialog.show(getFragmentManager(), "");
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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(thumbnailGridFragment);
        transaction.show(networkInterruptionFragment);
        transaction.commit();
    }

    /**
     * Hide the Network Interruption fragment and show the thumbnail grid.
     */
    private void hideNetworkInterruption() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(networkInterruptionFragment);
        transaction.show(thumbnailGridFragment);
        transaction.commit();

        // The app probably started without a network connection, so the thumbnail cache is empty.
        // Do a refresh to grab them automatically so the user isn't left with a blank grid.
        if (((RandomurApp) getApplication()).getImageCache().countThumbnails() == 0) {
            thumbnailGridFragment.refresh();
        }
    }

    @Override
    public void onNetworkInterruption() {
        Boolean isConnected = NetworkConnectivityReceiver.isConnected(this);
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
