package com.example.firstapplication;

public class Contact {
    public String name;
    public String content;
    public String date_time;
    public String no_likes;
    public String no_comments;

    public Contact(String name, String content, String date_time, String no_likes, String no_comments) {
        this.name = name;
        this.content = content;
        this.date_time = date_time;
        this.no_likes = no_likes;
        this.no_comments = no_comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getNo_likes() {
        return no_likes;
    }

    public void setNo_likes(String no_likes) {
        this.no_likes = no_likes;
    }

    public String getNo_comments() {
        return no_comments;
    }

    public void setNo_comments(String no_comments) {
        this.no_comments = no_comments;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", date_time='" + date_time + '\'' +
                ", no_likes='" + no_likes + '\'' +
                ", no_comments='" + no_comments + '\''
                ;
    }
}
