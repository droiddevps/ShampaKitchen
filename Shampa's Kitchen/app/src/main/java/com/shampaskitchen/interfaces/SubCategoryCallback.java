package com.shampaskitchen.interfaces;

import com.shampaskitchen.model.SubCategoryModel;

import java.util.ArrayList;

/**
 * Created by sayantan on 4/3/18.
 */

public interface SubCategoryCallback {
    void onSuccess(ArrayList<SubCategoryModel> arrayList);

    void onFailure(String s);
}
