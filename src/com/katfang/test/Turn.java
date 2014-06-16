package com.katfang.test;

/**
 * User: greg
 * Date: 6/21/13
 * Time: 2:38 PM
 */
public class Turn {

    public final static String PICTURE = "picture";
    public final static String PHRASE = "phrase";

    private String creator;
    private String content;
    private String type;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Turn() { }

    Turn(String creator, String content, String type) {
        this.creator = creator;
        this.content = content;
        this.type = type;
    }

    public String getCreator() {
        return creator;
    }

    public String getContent() { return content; }

    public String getType() { return type; }
}