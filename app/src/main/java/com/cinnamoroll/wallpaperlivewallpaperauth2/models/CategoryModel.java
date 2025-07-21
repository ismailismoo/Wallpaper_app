package com.cinnamoroll.wallpaperlivewallpaperauth2.models;

public class CategoryModel {
    String name,url;

    public CategoryModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
