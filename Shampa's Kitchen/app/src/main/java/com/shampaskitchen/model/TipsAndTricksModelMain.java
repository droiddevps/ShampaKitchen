package com.shampaskitchen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by sayantan on 17/3/18.
 */

public class TipsAndTricksModelMain {
    @JsonProperty("TipsData")
    private ArrayList<TipsAndTricksModel> arrayList = new ArrayList<>();

    @JsonProperty("msg")
    private String msg = "";

    public ArrayList<TipsAndTricksModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<TipsAndTricksModel> arrayList) {
        this.arrayList = arrayList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
