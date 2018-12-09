package com.shampaskitchen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by sayantan on 3/3/18.
 */

public class CategoryModelMain {

    @JsonProperty("CategoryData")
    private ArrayList<CategoryModel> arrayList = new ArrayList<>();

    @JsonProperty("msg")
    private String msg = "";

    public ArrayList<CategoryModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<CategoryModel> arrayList) {
        this.arrayList = arrayList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
