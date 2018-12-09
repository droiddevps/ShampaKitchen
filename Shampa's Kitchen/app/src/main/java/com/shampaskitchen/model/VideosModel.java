package com.shampaskitchen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class VideosModel {

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("VideoData")
    private ArrayList<VideoDataItem> videoData;

    @JsonProperty("Ack")
    private int ack;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setVideoData(ArrayList<VideoDataItem> videoData) {
        this.videoData = videoData;
    }

    public ArrayList<VideoDataItem> getVideoData() {
        return videoData;
    }

    public void setAck(int ack) {
        this.ack = ack;
    }

    public int getAck() {
        return ack;
    }

    @Override
    public String toString() {
        return
                "VideosModel{" +
                        "msg = '" + msg + '\'' +
                        ",videoData = '" + videoData + '\'' +
                        ",ack = '" + ack + '\'' +
                        "}";
    }
}