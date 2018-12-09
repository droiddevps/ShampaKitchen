package com.shampaskitchen.interfaces;

import com.shampaskitchen.model.TipsAndTricksModel;

import java.util.ArrayList;

/**
 * Created by sayantan on 17/3/18.
 */

public interface TipsAndTrickCallback {
    void onSuccess(ArrayList<TipsAndTricksModel> arrayList);

    void onFailure(String s);
}
