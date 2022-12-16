package com.example.springmusicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Singer implements Serializable {

    @SerializedName("singerId")
    @Expose
    private Integer singerId;
    @SerializedName("singerName")
    @Expose
    private String singerName;
    @SerializedName("singerImages")
    @Expose
    private String singerImages;

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSingerImages() {
        return singerImages;
    }

    public void setSingerImages(String singerImages) {
        this.singerImages = singerImages;
    }

}