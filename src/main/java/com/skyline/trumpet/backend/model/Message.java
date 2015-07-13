package com.skyline.trumpet.backend.model;

/**
 * Created by Administrator on 2015/7/4.
 */
public class Message {
    private String name;
    private String text;

    public Message(String name, String text){
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }




}
