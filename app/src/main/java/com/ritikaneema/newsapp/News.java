package com.ritikaneema.newsapp;

public class News {
    private String title;
    private String date;
    private String author;
    private String webUrl;

    public News(String title, String date, String author, String webUrl) {
        this.title = title;
        this.date = date;
        this.author = author;
        this.webUrl = webUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                "date='" + date + '\'' +
                ", author='" + author + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }
}
