package com.ritikaneema.newsapp;

public class News {
    private String title;
    private String webUrl;

    public News(String title, String webUrl) {
        this.title = title;
        this.webUrl = webUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }
}
