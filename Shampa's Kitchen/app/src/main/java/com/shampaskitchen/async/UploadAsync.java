package com.shampaskitchen.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.shampaskitchen.interfaces.UploadCallBack;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by sayantan on 7/2/18.
 */

public class UploadAsync extends AsyncTask<Void, Void, String> {

    private Context context;
    private MultipartEntity multipartEntity;
    private String url;
    private UploadCallBack callBack;
    private ProgressDialog progressDialog;

    public UploadAsync(Context context, MultipartEntity multipartEntity, String url, UploadCallBack callBack) {
        this.context = context;
        this.multipartEntity = multipartEntity;
        this.url = url;
        this.callBack = callBack;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Submitting...");
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
        String responseString = "";
        HttpParams params1 = new BasicHttpParams();
        params1.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpClient httpClient = new DefaultHttpClient(params1);
        HttpPost postRequest = new HttpPost(url);

        postRequest.setEntity(multipartEntity);
        HttpResponse response;

        try {
            response = httpClient.execute(postRequest);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String sResponse;
            StringBuilder s = new StringBuilder();
            while ((sResponse = reader.readLine()) != null) {
                s = s.append(sResponse);
            }
            responseString = s.toString();

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        Log.i("Response", s);
        String msg = "";
        try {
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.has("msg"))
                msg = jsonObject.getString("msg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
       callBack.onResponse(msg);
    }
}
