package com.example.myapplication.entity;

/**
 */

public class DiscuzComment {
    public String id;
    public String discuzId;
    public String user_name;
    public String content;
    public String date;

    public DiscuzComment(String id, String discuzId,  String master_name, String content, String date) {
        this.id = id;
        this.discuzId = discuzId;
        this.user_name = master_name;
        this.content = content;
        this.date = date;
    }

    public DiscuzComment() {
    }
}
