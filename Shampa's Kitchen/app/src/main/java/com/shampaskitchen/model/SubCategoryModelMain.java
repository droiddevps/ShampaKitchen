package com.shampaskitchen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by sayantan on 4/3/18.
 */

public class SubCategoryModelMain {
    @JsonProperty("SubcategoryData")
    private ArrayList<SubCategoryModel> arrayList=new ArrayList<>();

    @JsonProperty("msg")
    private String msg="";

    public ArrayList<SubCategoryModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<SubCategoryModel> arrayList) {
        this.arrayList = arrayList;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
