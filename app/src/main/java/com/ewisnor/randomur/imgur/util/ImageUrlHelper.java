package com.ewisnor.randomur.imgur.util;

/**
 * Created by evan on 2015-01-02.
 */
public class ImageUrlHelper {

    /**
     * Create an imgur thumbnail URL based on a full link URL provided by the imgur API
     * @param fullUrl Complete URL to an imgur-hosted image
     * @return Complete URL to a thumbnail of the imgur-hosted image
     */
    public static String CreateThumbnailUrl(String fullUrl) {
        String urlAndPath = fullUrl.substring(0, fullUrl.lastIndexOf('/') + 1);
        String fileName = fullUrl.substring(fullUrl.lastIndexOf('/') + 1, fullUrl.length());
        String[] splitFileName = fileName.split("\\.");
        String thumbnailFileName = splitFileName[0] + "s." + splitFileName[1];
        return urlAndPath + thumbnailFileName;
    }
}
