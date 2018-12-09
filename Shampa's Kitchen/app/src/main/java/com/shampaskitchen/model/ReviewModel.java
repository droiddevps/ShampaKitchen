package com.shampaskitchen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sayantan on 4/3/18.
 */

public class ReviewModel {

    @JsonProperty("review_id")
    private String review_id = "";
    @JsonProperty("review")
    private String review = "";
    @JsonProperty("rate")
    private String rate = "";
    @JsonProperty("posted_by")
    private String posted_by = "";
    @JsonProperty("review_date")
    private String review_date = "";
    @JsonProperty("review_status")
    private String review_status = "";

    public String getReview_id() {
        return review_id;
    }

    public void setReview_id(String review_id) {
        this.review_id = review_id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(String posted_by) {
        this.posted_by = posted_by;
    }

    public String getReview_date() {
        return review_date;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }

    public String getReview_status() {
        return review_status;
    }

    public void setReview_status(String review_status) {
        this.review_status = review_status;
    }
}
