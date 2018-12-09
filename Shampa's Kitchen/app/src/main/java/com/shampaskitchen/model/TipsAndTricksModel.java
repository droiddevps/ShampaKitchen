package com.shampaskitchen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by sayantan on 17/3/18.
 */

public class TipsAndTricksModel {
    @JsonProperty("tips_id")
    private String tips_id="";

    @JsonProperty("title")
    private String title="";

    @JsonProperty("details")
    private String details="";

    @JsonProperty("tips_status")
    private String tips_status="";

    @JsonProperty("Reviews")
    private ArrayList<ReviewModel> reviewModelArrayList = new ArrayList<>();

    public String getTips_id() {
        return tips_id;
    }

    public void setTips_id(String tips_id) {
        this.tips_id = tips_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTips_status() {
        return tips_status;
    }

    public void setTips_status(String tips_status) {
        this.tips_status = tips_status;
    }

    public ArrayList<ReviewModel> getReviewModelArrayList() {
        return reviewModelArrayList;
    }

    public void setReviewModelArrayList(ArrayList<ReviewModel> reviewModelArrayList) {
        this.reviewModelArrayList = reviewModelArrayList;
    }
}
