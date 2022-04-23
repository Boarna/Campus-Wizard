package com.example.the_campus_wizard.holderClasses;

import java.util.List;
import java.util.Map;

public class ForumDiscussion {

    private String author_name;
    private String discussion;
    private List<Map<Object, String>> comments;
    private String document_id;
    private String title;

    public ForumDiscussion(String author_name, String discussion, List<Map<Object, String>> comments, String document_id, String title) {
        this.author_name = author_name;
        this.discussion = discussion;
        this.comments = comments;
        this.document_id = document_id;
        this.title = title;
    }


    public String getAuthor_name() {
        return author_name;
    }

    public String getDiscussion() {
        return discussion;
    }

    public List<Map<Object, String>> getComments() {
        return comments;
    }

    public String getDocument_id() {
        return document_id;
    }

    public String getTitle() {
        return title;
    }
}
