package com.yusufcakal.ecommerce.model;

public class Mail {

    private String to, title, content, link;

    public Mail(String to, String title, String content, String link) {
        this.to = to;
        this.title = title;
        this.content = content;
        this.link = link;
    }

    public Mail(String to, String title, String content) {
        this.to = to;
        this.title = title;
        this.content = content;
    }

    public Mail() {}

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
