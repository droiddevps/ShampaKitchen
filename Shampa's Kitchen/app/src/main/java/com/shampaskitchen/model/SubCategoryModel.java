package com.shampaskitchen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sayantan on 4/3/18.
 */

public class SubCategoryModel {
    @JsonProperty("subcategory_id")
    private String subcategory_id="";

    @JsonProperty("category_id")
    private String category_id="";

    @JsonProperty("subcategory_name")
    private String subcategory_name="";

    @JsonProperty("subcategory_status")
    private String subcategory_status="";

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSubcategory_name() {
        return subcategory_name;
    }

    public void setSubcategory_name(String subcategory_name) {
        this.subcategory_name = subcategory_name;
    }

    public String getSubcategory_status() {
        return subcategory_status;
    }

    public void setSubcategory_status(String subcategory_status) {
        this.subcategory_status = subcategory_status;
    }
}
