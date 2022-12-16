package com.example.springmusicapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Category implements Serializable {

    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("categoryImages")
    @Expose
    private String categoryImages;
    @SerializedName("categoryId")
    @Expose
    private Integer categoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImages() {
        return categoryImages;
    }

    public void setCategoryImages(String categoryImages) {
        this.categoryImages = categoryImages;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}