package com.example.the_campus_wizard.holderClasses;

public class News {

    String title;
    String topic;

    public News(String title, String topic) {
        this.title = title;
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
