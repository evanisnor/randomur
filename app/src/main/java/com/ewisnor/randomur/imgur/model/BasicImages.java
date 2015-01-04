package com.ewisnor.randomur.imgur.model;

/**
 * Imgur API model for the JSON that is returned by the Random endpoint. Deserialized with GSON.
 *
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
