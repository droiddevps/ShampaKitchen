package com.shampaskitchen.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampaskitchen.interfaces.TipsAndTrickCallback;
import com.shampaskitchen.model.TipsAndTricksModelMain;
import com.shampaskitchen.networking.HTTPConnection;

import java.io.IOException;

import static com.shampaskitchen.networking.Urls.BASEURL;

/**
 * Created by sayantan on 17/3/18.
 */

public class TipsAndTricksAsync extends AsyncTask<Void, Void, String> {

    private Context context;
    private TipsAndTrickCallback callback;
    private ProgressDialog progressDialog;


    public TipsAndTricksAsync(Context context, TipsAndTrickCallback callback) {
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

        return HTTPConnection.getResponseCode(BASEURL + "tips");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog.isShowing())
            progressDialog.dismiss();
        TipsAndTricksModelMain main = null;
        if (!s.isEmpty() && s.length() > 0) {
            main = parseCommunityData(s);
            if (main != null && main.getArrayList().size() > 0)
                callback.onSuccess(main.getArrayList());
            else {
                Toast.makeText(context, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private TipsAndTricksModelMain parseCommunityData(String response) {

        TipsAndTricksModelMain data = new TipsAndTricksModelMain();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        try {
            data = mapper.readValue(response.trim(), TipsAndTricksModelMain.class);
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
