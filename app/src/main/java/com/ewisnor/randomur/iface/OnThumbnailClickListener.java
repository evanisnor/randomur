package com.ewisnor.randomur.iface;

/**
 * Listener interface for handling thumbnail clicks
 *
 * Created by evan on 2015-01-04.
 */
public interface OnThumbnailClickListener {

    /**
     * Handle the click of a thumbnail
     * @param id The ImageCache ID of the thumbnail
     */
    void onThumbnailClick(int id);
}
