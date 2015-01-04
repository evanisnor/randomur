package com.ewisnor.randomur.imgur.model;

/**
 * Based on this: https://api.imgur.com/models/basic
 *
 * Created by evan on 2015-01-03.
 */
public class BasicImages {
    private GalleryImage[] data;
    private Boolean success;
    private Integer status;

    public GalleryImage[] getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getStatus() {
        return status;
    }
}
