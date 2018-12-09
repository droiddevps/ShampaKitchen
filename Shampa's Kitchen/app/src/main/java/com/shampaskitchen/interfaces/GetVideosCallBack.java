package com.shampaskitchen.interfaces;

import com.shampaskitchen.model.VideoDataItem;
import com.shampaskitchen.model.VideosModel;

import java.util.ArrayList;

public interface GetVideosCallBack {

    void onSuccess(ArrayList<VideoDataItem> model);

    void onFailure(String msg);
}
