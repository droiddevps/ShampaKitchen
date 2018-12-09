package com.shampaskitchen.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shampaskitchen.interfaces.GetVideosCallBack;
import com.shampaskitchen.model.VideosModel;
import com.shampaskitchen.networking.HTTPConnection;

import java.io.IOException;

import static com.shampaskitchen.networking.Urls.VIDEOS;

public class VideosAsync extends AsyncTask<Void, Void, String> {

    private ProgressDialog progressDialog;
    private Context context;
    private VideosModel videosModel;
    private GetVideosCallBack callBack;


    public VideosAsync(Context context, GetVideosCallBack callBack) {
        this.context = context;
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
        return HTTPConnection.getResponseCode(VIDEOS);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (progressDialog.isShowing())
            progressDialog.dismiss();
        VideosModel main = null;
        if (!s.isEmpty() && s.length() > 0) {
            main = parseVideoData(s);
            if (main != null && main.getVideoData().size() > 0)
                callBack.onSuccess(main.getVideoData());
            else {
                callBack.onFailure(main.getMsg());
            }
        }
    }

    private VideosModel parseVideoData(String s) {
        VideosModel data = new VideosModel();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false);
        try {
            data = mapper.readValue(s.trim(), VideosModel.class);
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
