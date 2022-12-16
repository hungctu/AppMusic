package com.example.springmusicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MusicList implements Serializable {

    @SerializedName("musicListId")
    @Expose
    private Integer musicListId;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("musicListName")
    @Expose
    private String musicListName;
    @SerializedName("musicListDateCreate")
    @Expose
    private String musicListDateCreate;

    public Integer getMusicListId() {
        return musicListId;
    }

    public void setMusicListId(Integer musicListId) {
        this.musicListId = musicListId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMusicListName() {
        return musicListName;
    }

    public void setMusicListName(String musicListName) {
        this.musicListName = musicListName;
    }

    public String getMusicListDateCreate() {
        return musicListDateCreate;
    }

    public void setMusicListDateCreate(String musicListDateCreate) {
        this.musicListDateCreate = musicListDateCreate;
    }
}
