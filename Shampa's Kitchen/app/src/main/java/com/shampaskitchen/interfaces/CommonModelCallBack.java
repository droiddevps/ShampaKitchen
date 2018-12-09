package com.shampaskitchen.interfaces;

import com.shampaskitchen.model.CommonDataModel;

/**
 * Created by sayantan on 17/3/18.
 */

public interface CommonModelCallBack {

    void onSuccess(CommonDataModel dataModel);
    void onFailure(String s);
}
