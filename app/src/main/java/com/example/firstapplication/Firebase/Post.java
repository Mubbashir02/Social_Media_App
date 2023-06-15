package com.example.firstapplication.Firebase;

public class Post {


    String post_id;
    String post_content;
    String current_timestamp;

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getCurrent_timestamp() {
        return current_timestamp;
    }

    public void setCurrent_timestamp(String current_timestamp) {
        this.current_timestamp = current_timestamp;
    }

    public Post(String post_id, String post_content, String current_timestamp) {
        this.post_id = post_id;
        this.post_content = post_content;
        this.current_timestamp = current_timestamp;
    }
}
