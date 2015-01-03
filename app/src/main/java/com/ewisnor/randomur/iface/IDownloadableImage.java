package com.ewisnor.randomur.iface;

/**
 * Interface to be applied to an Image model in order to de-couple the third-party API
 * from the randomur download and storage logic. This is applied to the imgur API, but
 * could be used for other APIs as well.
 *
 * Created by evan on 2015-01-02.
 */
public interface IDownloadableImage {

    /**
     * True if the target API supports downloading thumbnails
     * @return
     */
    Boolean IsThumbnailAvailable();

    /**
     * Obtain the full download link for the thumbnail image
     * @return
     */
    String GetThumbnailDownloadUrl();

    /**
     * Obtain the full download link for the full size image
     * @return
     */
    String GetFullSizeDownloadUrl();
}
