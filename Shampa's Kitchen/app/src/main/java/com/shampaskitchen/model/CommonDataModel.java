package com.shampaskitchen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sayantan on 17/3/18.
 */

public class CommonDataModel {
    @JsonProperty("content_id")
    private String content_id = "";

    @JsonProperty("content_title")
    private String content_title = "";

    @JsonProperty("content")
    private String content = "";

    @JsonProperty("msg")
    private String msg = "";

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
