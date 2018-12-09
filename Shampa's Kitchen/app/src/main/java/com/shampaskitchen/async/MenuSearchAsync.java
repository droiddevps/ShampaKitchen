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
import com.shampaskitchen.networking.HTTPConnection;

import java.io.IOException;

import static com.shampaskitchen.networking.Urls.BASEURL;
import static com.shampaskitchen.networking.Urls.SEARCHSTRING;

/**
 * Created by sayantan on 26/3/18.
 */

public class MenuSearchAsync extends AsyncTask<Void,Void,String> {

    private Context context;
    private String searchString="";
    private MenuCallback callback;
    private ProgressDialog progressDialog;

    public MenuSearchAsync(Context context, String searchString, MenuCallback callback) {
        this.context = context;
        this.searchString = searchString;
        this.callback = callback;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Searching...");
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
        return HTTPConnection.getResponseCode(SEARCHSTRING + searchString);
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
