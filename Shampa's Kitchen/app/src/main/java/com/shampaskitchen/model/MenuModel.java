package com.shampaskitchen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by sayantan on 4/3/18.
 */

public class MenuModel {
    @JsonProperty("menu_id")
    private String menu_id = "";

    @JsonProperty("category_id")
    private String category_id = "";

    @JsonProperty("subcategory_id")
    private String subcategory_id = "";

    @JsonProperty("menu_name")
    private String menu_name = "";

    @JsonProperty("preparation_time")
    private String preparation_time = "";

    @JsonProperty("no_of_people")
    private String no_of_people = "";

    @JsonProperty("ingredients")
    private String ingredients = "";

    @JsonProperty("making_process_english")
    private String making_process_english = "";

    @JsonProperty("making_process_hindi")
    private String making_process_hindi = "";

    @JsonProperty("youtube_video")
    private String youtube_video = "";

    @JsonProperty("photo")
    private String photo = "";

    @JsonProperty("ad_banner")
    private String ad_banner = "";

    @JsonProperty("menu_status")
    private String menu_status = "";

    @JsonProperty("page_link")
    private String page_link = "";

    @JsonProperty("Reviews")
    private ArrayList<ReviewModel> reviewModelArrayList = new ArrayList<>();

    private boolean lanEngSelected = false;

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(String preparation_time) {
        this.preparation_time = preparation_time;
    }

    public String getNo_of_people() {
        return no_of_people;
    }

    public void setNo_of_people(String no_of_people) {
        this.no_of_people = no_of_people;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getMaking_process_english() {
        return making_process_english;
    }

    public void setMaking_process_english(String making_process_english) {
        this.making_process_english = making_process_english;
    }

    public String getMaking_process_hindi() {
        return making_process_hindi;
    }

    public void setMaking_process_hindi(String making_process_hindi) {
        this.making_process_hindi = making_process_hindi;
    }

    public String getYoutube_video() {
        return youtube_video;
    }

    public void setYoutube_video(String youtube_video) {
        this.youtube_video = youtube_video;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAd_banner() {
        return ad_banner;
    }

    public void setAd_banner(String ad_banner) {
        this.ad_banner = ad_banner;
    }

    public String getMenu_status() {
        return menu_status;
    }

    public void setMenu_status(String menu_status) {
        this.menu_status = menu_status;
    }

    public ArrayList<ReviewModel> getReviewModelArrayList() {
        return reviewModelArrayList;
    }

    public void setReviewModelArrayList(ArrayList<ReviewModel> reviewModelArrayList) {
        this.reviewModelArrayList = reviewModelArrayList;
    }

    public boolean isLanEngSelected() {
        return lanEngSelected;
    }

    public void setLanEngSelected(boolean lanEngSelected) {
        this.lanEngSelected = lanEngSelected;
    }

    public String getPage_link() {
        return page_link;
    }

    public void setPage_link(String page_link) {
        this.page_link = page_link;
    }
}
