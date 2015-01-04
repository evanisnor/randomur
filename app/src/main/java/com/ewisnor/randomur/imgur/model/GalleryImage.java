package com.ewisnor.randomur.imgur.model;

import com.ewisnor.randomur.iface.IDownloadableImage;
import com.ewisnor.randomur.imgur.util.ImageUrlHelper;

/**
 * Based on this: https://api.imgur.com/models/gallery_image
 *
 * Created by evan on 2015-01-02.
 */
public class GalleryImage implements IDownloadableImage {
    private String id;
    private String title;
    private String description;
    private Long dateTime;
    private String type;
    private Boolean animated;
    private Integer width;
    private Integer height;
    private Integer size;
    private Integer views;
    private Long bandwidth;
    private String deleteHash;
    private String link;
    private String gifv;
    private String mp4;
    private String webm;
    private Boolean looping;
    private Integer vote;
    private Boolean favorite;
    private Boolean nsfw;
    private Integer commentCount;
    private String section;
    private String accountUrl;
    private String accountId;
    private Integer ups;
    private Integer downs;
    private Integer score;
    private Boolean isAlbum;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getDateTime() {
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

    public Long getBandwidth() {
        return bandwidth;
    }

    public String getDeleteHash() {
        return deleteHash;
    }

    public String getLink() {
        return link;
    }

    public String getGifv() {
        return gifv;
    }

    public String getMp4() {
        return mp4;
    }

    public String getWebm() {
        return webm;
    }

    public Boolean getLooping() {
        return looping;
    }

    public Integer getVote() {
        return vote;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public String getSection() {
        return section;
    }

    public String getAccountUrl() {
        return accountUrl;
    }

    public String getAccountId() {
        return accountId;
    }

    public Integer getUps() {
        return ups;
    }

    public Integer getDowns() {
        return downs;
    }

    public Integer getScore() {
        return score;
    }

    public Boolean getIsAlbum() {
        return isAlbum;
    }

    @Override
    public Boolean IsThumbnailAvailable() {
        return true;
    }

    @Override
    public String getThumbnailDownloadUrl() {
        return ImageUrlHelper.CreateThumbnailUrl(getLink());
    }

    @Override
    public String getFullSizeDownloadUrl() {
        return getLink();
    }
}