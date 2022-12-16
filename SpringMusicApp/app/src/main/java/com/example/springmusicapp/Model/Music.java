package com.example.springmusicapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Music implements Parcelable {

    @SerializedName("musicId")
    @Expose
    private Integer musicId;
    @SerializedName("singerId")
    @Expose
    private Integer singerId;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;
    @SerializedName("singerName")
    @Expose
    private String singerName;
    @SerializedName("musicName")
    @Expose
    private String musicName;
    @SerializedName("musicImages")
    @Expose
    private String musicImages;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("lyrics")
    @Expose
    private String lyrics;

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicImages() {
        return musicImages;
    }

    public void setMusicImages(String musicImages) {
        this.musicImages = musicImages;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    protected Music(Parcel in) {
        musicId = Integer.valueOf(in.readString());
        singerId = Integer.valueOf(in.readString());
        categoryId = Integer.valueOf(in.readString());
        singerName = in.readString();
        musicName = in.readString();
        musicImages = in.readString();
        lyrics = in.readString();
        url = in.readString();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(String.valueOf(musicId));
        dest.writeString(String.valueOf(singerId));
        dest.writeString(String.valueOf(categoryId));
        dest.writeString(singerName);
        dest.writeString(musicName);
        dest.writeString(musicImages);
        dest.writeString(lyrics);
        dest.writeString(url);
    }
}