package com.shampaskitchen.interfaces;

import com.shampaskitchen.model.CategoryModel;

import java.util.ArrayList;

/**
 * Created by sayantan on 3/3/18.
 */

public interface CategoryCallback {

    void onSuccess(ArrayList<CategoryModel> categoryModelList);

    void onFailure(String string);
}
