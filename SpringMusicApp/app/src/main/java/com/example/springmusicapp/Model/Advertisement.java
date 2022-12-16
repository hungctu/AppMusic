package com.example.springmusicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Advertisement implements Serializable {
    @SerializedName("ad_ID")
    @Expose
    private Integer adID;
    @SerializedName("music_id")
    @Expose
    private Integer musicId;
    @SerializedName("ad_images")
    @Expose
    private String adImages;
    @SerializedName("ad_content")
    @Expose
    private String adContent;
    @SerializedName("ad_date_create")
    @Expose
    private String adDateCreate;

    public Integer getAdID() {
        return adID;
    }

    public void setAdID(Integer adID) {
        this.adID = adID;
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public String getAdImages() {
        return adImages;
    }

    public void setAdImages(String adImages) {
        this.adImages = adImages;
    }

    public String getAdContent() {
        return adContent;
    }

    public void setAdContent(String adContent) {
        this.adContent = adContent;
    }

    public String getAdDateCreate() {
        return adDateCreate;
    }

    public void setAdDateCreate(String adDateCreate) {
        this.adDateCreate = adDateCreate;
    }

}