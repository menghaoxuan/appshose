package com.example.myapplication.bean;

import android.graphics.Bitmap;

import java.io.File;



public class EventBus_Tag {
    private int tag;
    private String content;




    public EventBus_Tag(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
