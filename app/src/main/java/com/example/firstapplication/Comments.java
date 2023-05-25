package com.example.firstapplication;

import android.content.Intent;

public class Comments {
    private Integer no_comments;

    public Comments(Integer no_username) {
        this.no_comments = no_username;
    }

    public Integer getNo_comments() {
        return no_comments;
    }

    public void setNo_comments(Integer no_username) {
        this.no_comments = no_username;
    }
}
