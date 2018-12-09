package com.shampaskitchen.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampaskitchen.interfaces.MenuCallback;
import com.shampaskitchen.model.MenuModelMain;
import com.shampaskitchen.model.SubCategoryModelMain;
import com.shampaskitchen.networking.HTTPConnection;

import java.io.IOException;

import static com.shampaskitchen.networking.Urls.BASEURL;
import static com.shampaskitchen.networking.Urls.SUB_CATEGORY;

/**
 * Created by sayantan on 4/3/18.
 */

public class MenuAsync extends AsyncTask<Void, Void, String> {

    private Context context;
    private MenuCallback callback;
    private ProgressDialog progressDialog;
    private String urlPart = "";
    private boolean isList = false;

    public MenuAsync(Context context, MenuCallback callback, String sub_category_id, String category_id, boolean isList) {
        this.context = context;
        this.callback = callback;
        this.isList = isList;
        if (isList)
            urlPart = "menu&category_id=" + category_id + "&subcategory_id=0";
        else urlPart = "search_category&category_id=" + category_id + "&menu_id=" + sub_category_id;
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

        return HTTPConnection.getResponseCode(BASEURL + urlPart);
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog.isShowing())
            progressDialog.dismiss();
        MenuModelMain main = null;
        if (!s.isEmpty() && s.length() > 0) {
            main = parseCommunityData(s);
            if (main != null && main.getArrayList().size() > 0)
                callback.onSuccess(main.getArrayList());
            else {
                Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private MenuModelMain parseCommunityData(String response) {

        MenuModelMain data = new MenuModelMain();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        try {
            data = mapper.readValue(response.trim(), MenuModelMain.class);
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
