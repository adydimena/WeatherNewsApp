package com.example.ady.weathernewsapp.Util.HelperClasses;

/**
 * Created by Ady on 1/17/2018.
 */

public class News {
    public String discri;
    public String author;
    public String title;
    public String imageUrl;

    public News(String discri, String author, String title, String imageUrl) {
        this.discri = discri;
        this.author = author;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getDiscri() {
        return discri;
    }

    public void setDiscri(String discri) {
        this.discri = discri;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
