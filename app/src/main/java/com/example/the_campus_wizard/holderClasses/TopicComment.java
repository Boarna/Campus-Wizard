package com.example.the_campus_wizard.holderClasses;

public class TopicComment {

    String text;
    String user_name;
    String timestamp;

    public TopicComment(String user_name, String text, String timestamp) {
        this.user_name = user_name;
        this.text = text;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
