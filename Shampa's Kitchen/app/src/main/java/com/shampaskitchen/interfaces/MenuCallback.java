package com.shampaskitchen.interfaces;

import com.shampaskitchen.model.MenuModel;

import java.util.ArrayList;

/**
 * Created by sayantan on 4/3/18.
 */

public interface MenuCallback {

    void onSuccess(ArrayList<MenuModel> arrayList);

    void onFailure(String s);
}
