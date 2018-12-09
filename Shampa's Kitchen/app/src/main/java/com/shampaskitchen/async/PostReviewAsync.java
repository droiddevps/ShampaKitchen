package com.shampaskitchen.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.shampaskitchen.interfaces.PostReviewCallBack;
import com.shampaskitchen.networking.HTTPConnection;

import org.json.JSONException;
import org.json.JSONObject;

import static com.shampaskitchen.networking.Urls.BASEURL;
import static com.shampaskitchen.networking.Urls.POSTREVIEW;
import static com.shampaskitchen.networking.Urls.POSTTIPSREVIEW;

/**
 * Created by sayantan on 15/3/18.
 */

public class PostReviewAsync extends AsyncTask<Void, Void, String> {
    private ProgressDialog progressDialog;
    private Context context;
    private String name;
    private String review;
    private float rate;
    private String menuId = "";
    private PostReviewCallBack callBack;
    String urlpart = "";
    private boolean isfromTips = false;

    public PostReviewAsync(Context context, String name, String review, float rate, String menuId, boolean isfromTips, PostReviewCallBack callBack) {
        this.context = context;
        this.name = name;
        this.review = review;
        this.rate = rate;
        this.isfromTips = isfromTips;
        this.menuId = menuId;
        this.callBack = callBack;
        if (isfromTips) {
            urlpart = "name=" + name + "&" + "&tips_id=" + menuId + "&rate=" + (int) rate + "&review=" + review;
        } else
            urlpart = "name=" + name + "&" + "&menu_id=" + menuId + "&rate=" + (int) rate + "&review=" + review;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        String s;
        if (isfromTips) {
            s = POSTTIPSREVIEW + urlpart;
        } else {
            s = POSTREVIEW + urlpart;
        }
        return HTTPConnection.getResponseCode(s);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);

            if (jsonObject.has("msg")) {
                if (jsonObject.optString("msg").equalsIgnoreCase("Success"))
                    callBack.onSuccess(jsonObject.optString("msg"));
                else callBack.onFailure(jsonObject.optString("msg"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
