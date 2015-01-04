package com.ewisnor.randomur.imgur.model;

/**
 * Based on this: https://api.imgur.com/models/gallery_album
 *
 * Created by evan on 2015-01-02.
 */
public class GalleryAlbum {
    private String id;
    private String title;
    private String description;
    private Long dateTime;
    private String cover;
    private Integer coverWidth;
    private Integer coverHeight;
    private String accountUrl;
    private Integer accountId;
    private String privacy;
    private String layout;
    private Integer views;
    private String link;
    private Integer ups;
    private Integer downs;
    private Integer score;
    private Boolean isAlbum;
    private String vote;
    private Boolean favorite;
    private Boolean nsfw;
    private Integer commentCount;
    private Integer imagesCount;
    private GalleryImage[] images;

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

    public String getCover() {
        return cover;
    }

    public Integer getCoverWidth() {
        return coverWidth;
    }

    public Integer getCoverHeight() {
        return coverHeight;
    }

    public String getAccountUrl() {
        return accountUrl;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public String getPrivacy() {
        return privacy;
    }

    public String getLayout() {
        return layout;
    }

    public Integer getViews() {
        return views;
    }

    public String getLink() {
        return link;
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

    public String getVote() {
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

    public Integer getImagesCount() {
        return imagesCount;
    }

    public GalleryImage[] getImages() {
        return images;
    }
}
