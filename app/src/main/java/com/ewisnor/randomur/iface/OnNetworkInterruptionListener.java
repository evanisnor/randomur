package com.ewisnor.randomur.iface;

/**
 * Interface for handling network interruptions (Airplane mode, signal loss, the apocalypse, etc)
 *
 * Created by evan on 2015-01-04.
 */
public interface OnNetworkInterruptionListener {
    void onNetworkInterruption();
}
