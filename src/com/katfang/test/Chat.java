package com.katfang.test;

/**
 * User: greg
 * Date: 6/21/13
 * Time: 2:38 PM
 */
public class Chat {

    private String content;
    private String type = "phrase";
    private String author;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Chat() { }

    Chat(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }
}