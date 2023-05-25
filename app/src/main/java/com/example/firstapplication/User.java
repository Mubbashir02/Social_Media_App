package com.example.firstapplication;

public class User {

    private String username;
    private String content;
    private String date_time;
    private String no_likes;


    public User(String username, String content, String date_time, String no_likes) {
        this.username = username;
        this.content = content;
        this.date_time = date_time;
        this.no_likes = no_likes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

}
