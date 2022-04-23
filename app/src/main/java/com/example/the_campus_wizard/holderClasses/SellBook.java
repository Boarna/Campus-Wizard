package com.example.the_campus_wizard.holderClasses;

public class SellBook {

    String title;
    String author;
    String description;
    String document_id;
    String sender;

    public SellBook(String title, String author, String description, String document_id, String sender) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.document_id = document_id;
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getDocument_id() {
        return document_id;
    }

    public String getSender() {
        return sender;
    }
}
