package com.shampaskitchen.networking;

/**
 * Created by sayantan on 3/2/18.
 */

public interface Urls {
    //    String BASEURL = "http://www.dreamworksbiz.com/projects/kitchen/webservice/service.php?action=";
//    String BASEURL = "http://199.79.62.15/~dreamofx/projects/kitchen/webservice/service.php?action=";
    String BASEURL = "http://www.shampaskitchen.com/webservice/service.php?action=";
    String SUB_CATEGORY = "subcategory&category_id=";
    String POSTREVIEW = BASEURL + "post_review&";
    String POSTTIPSREVIEW = BASEURL + "post_tips_review&";
    String SEARCHSTRING = BASEURL + "search&search_string=";
    String VIDEOS = BASEURL + "video";

}
