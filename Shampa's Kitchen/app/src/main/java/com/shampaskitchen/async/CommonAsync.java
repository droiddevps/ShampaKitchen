package com.shampaskitchen.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampaskitchen.interfaces.CommonModelCallBack;
import com.shampaskitchen.model.CommonDataModelMain;
import com.shampaskitchen.networking.HTTPConnection;
import com.shampaskitchen.utility.KitchenUtil;

import java.io.IOException;

/**
 * Created by sayantan on 17/3/18.
 */

public class CommonAsync extends AsyncTask<Void, Void, String> {

    private Context context;
    private String url = "";
    private String type = "";
    private CommonModelCallBack callBack;
    private ProgressDialog progressDialog;

    public CommonAsync(Context context, String url, String type, CommonModelCallBack callBack) {
        this.context = context;
        this.url = url;
        this.type = type;
        this.callBack = callBack;
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

        return HTTPConnection.getResponseCode(url);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog.isShowing())
            progressDialog.dismiss();
        CommonDataModelMain main = null;
        if (!s.isEmpty() && s.length() > 0) {
            main = parseData(s);
            if (main.getCommonAboutDataModel() != null || main.getCommonPrivacyDataModel() != null || main.getCommonTermDataModel() != null) {
                if (type.equalsIgnoreCase(KitchenUtil.ABOUTUS))
                    callBack.onSuccess(main.getCommonAboutDataModel());
                else if (type.equalsIgnoreCase(KitchenUtil.TERMS)) {
                    callBack.onSuccess(main.getCommonTermDataModel());
                } else if (type.equalsIgnoreCase(KitchenUtil.PRIVACY)) {
                    callBack.onSuccess(main.getCommonPrivacyDataModel());
                }
            } else {
                Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private CommonDataModelMain parseData(String response) {

        CommonDataModelMain data = new CommonDataModelMain();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        try {
            data = mapper.readValue(response.trim(), CommonDataModelMain.class);
        } catch (JsonParseException e1) {
            e1.printStackTrace();
        } catch (JsonMappingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return data;
    }
}
