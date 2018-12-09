package com.shampaskitchen.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampaskitchen.interfaces.CategoryCallback;
import com.shampaskitchen.model.CategoryModelMain;
import com.shampaskitchen.networking.HTTPConnection;

import java.io.IOException;

import static com.shampaskitchen.networking.Urls.BASEURL;

/**
 * Created by sayantan on 3/3/18.
 */

public class CategoryAsync extends AsyncTask<Void, Void, String> {

    private ProgressDialog progressDialog;
    private Context context;
    private CategoryModelMain modelMain;
    private CategoryCallback callback;


    public CategoryAsync(Context context, CategoryCallback callback) {
        this.context = context;
        this.callback = callback;
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

        return HTTPConnection.getResponseCode(BASEURL + "category");
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog.isShowing())
            progressDialog.dismiss();
        CategoryModelMain main = null;
        if (!s.isEmpty() && s.length() > 0) {
            main = parseCommunityData(s);
            if (main != null && main.getArrayList().size() > 0)
                callback.onSuccess(main.getArrayList());
            else {
                Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private CategoryModelMain parseCommunityData(String response) {

        CategoryModelMain data = new CategoryModelMain();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        try {
            data = mapper.readValue(response.trim(), CategoryModelMain.class);
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
