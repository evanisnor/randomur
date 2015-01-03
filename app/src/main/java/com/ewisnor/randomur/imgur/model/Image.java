package com.ewisnor.randomur.imgur.model;

import com.ewisnor.randomur.iface.IDownloadableImage;
import com.ewisnor.randomur.imgur.util.ImageUrlHelper;

/**
 * Created by evan on 2015-01-02.
 */
public class Image implements IDownloadableImage {
    private String id;
    private String title;
    private String description;
    private Integer dateTime;
    private String type;
    private Boolean animated;
    private Integer width;
    private Integer height;
    private Integer size;
    private Integer views;
    private Integer bandwidth;
    private String section;
    private String link;
    private Boolean favorite;
    private Boolean nsfw;
    private Integer vote;
    private String accountUrl;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDateTime() {
        return dateTime;
    }

    public String getType() {
        return type;
    }

    public Boolean getAnimated() {
        return animated;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getViews() {
        return views;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public String getSection() {
        return section;
    }

    public String getLink() {
        return link;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public Integer getVote() {
        return vote;
    }

    public String getAccountUrl() {
        return accountUrl;
    }

    @Override
    public Boolean IsThumbnailAvailable() {
        return true;
    }

    @Override
    public String GetThumbnailDownloadUrl() {
        return ImageUrlHelper.CreateThumbnailUrl(getLink());
    }

    @Override
    public String GetFullSizeDownloadUrl() {
        return getLink();
    }
}