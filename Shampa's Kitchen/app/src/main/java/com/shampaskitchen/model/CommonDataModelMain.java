package com.shampaskitchen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sayantan on 17/3/18.
 */

public class CommonDataModelMain {

    @JsonProperty("AboutData")
    private CommonDataModel commonAboutDataModel;

    @JsonProperty("TermData")
    private CommonDataModel commonTermDataModel;

    @JsonProperty("PrivacyData")
    private CommonDataModel commonPrivacyDataModel;

    public CommonDataModel getCommonAboutDataModel() {
        return commonAboutDataModel;
    }

    public void setCommonAboutDataModel(CommonDataModel commonAboutDataModel) {
        this.commonAboutDataModel = commonAboutDataModel;
    }

    public CommonDataModel getCommonTermDataModel() {
        return commonTermDataModel;
    }

    public void setCommonTermDataModel(CommonDataModel commonTermDataModel) {
        this.commonTermDataModel = commonTermDataModel;
    }

    public CommonDataModel getCommonPrivacyDataModel() {
        return commonPrivacyDataModel;
    }

    public void setCommonPrivacyDataModel(CommonDataModel commonPrivacyDataModel) {
        this.commonPrivacyDataModel = commonPrivacyDataModel;
    }
}
