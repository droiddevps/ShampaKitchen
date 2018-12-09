package com.shampaskitchen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sayantan on 3/3/18.
 */

public class CategoryModel {

    @JsonProperty("category_id")
    private String category_id="";

    @JsonProperty("category_name")
    private String category_name="";

    @JsonProperty("category_photo")
    private String category_photo="";

    @JsonProperty("subcategory_exists")
    private String subcategory_exists="";

    @JsonProperty("category_status")
    private String category_status="";

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_photo() {
        return category_photo;
    }

    public void setCategory_photo(String category_photo) {
        this.category_photo = category_photo;
    }

    public String getSubcategory_exists() {
        return subcategory_exists;
    }

    public void setSubcategory_exists(String subcategory_exists) {
        this.subcategory_exists = subcategory_exists;
    }

    public String getCategory_status() {
        return category_status;
    }

    public void setCategory_status(String category_status) {
        this.category_status = category_status;
    }
}
