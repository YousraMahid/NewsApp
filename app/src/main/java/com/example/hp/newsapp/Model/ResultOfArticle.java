package com.example.hp.newsapp.Model;

/**
 * Created by hp on 10/29/2017.
 */

public class ResultOfArticle {
    private String webTitle;
    private String sectionName;
    private String authors;
    private String webPublicationDate;
    private String webUrl;

    public ResultOfArticle(String webTitle, String sectionName, String authors, String webPublicationDate, String webUrl) {
        this.webTitle = webTitle;
        this.sectionName = sectionName;
        this.authors = authors;
        this.webPublicationDate = webPublicationDate;
        this.webUrl = webUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public ResultOfArticle(String webTitle, String sectionName, String authors, String webPublicationDate) {
        this.webTitle = webTitle;
        this.sectionName = sectionName;
        this.authors = authors;
        this.webPublicationDate = webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getAuthors() {
        return authors;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }
}
