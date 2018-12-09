package com.shampaskitchen.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.shampaskitchen.R;
import com.shampaskitchen.async.CommonAsync;
import com.shampaskitchen.interfaces.CommonModelCallBack;
import com.shampaskitchen.model.CommonDataModel;
import com.shampaskitchen.utility.KitchenUtil;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.shampaskitchen.networking.Urls.BASEURL;

public class CommonActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title_tv)
    TextView title_tv;

    @BindView(R.id.title_details)
    JustifiedTextView title_details;

    private String getStringFromIntent = "";
    private String end_url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getStringFromIntent = getIntent().getStringExtra("type");
        setTitle(getResources().getString(R.string.app_name));
        initUrl();
    }

    private void initUrl() {
        if (getStringFromIntent.equalsIgnoreCase(KitchenUtil.ABOUTUS)) {
            end_url = "about_us";
        } else if (getStringFromIntent.equalsIgnoreCase(KitchenUtil.PRIVACY)) {
            end_url = "privacy";
        } else if (getStringFromIntent.equalsIgnoreCase(KitchenUtil.TERMS)) {
            end_url = "terms";
        }

        callApi();
    }

    private void callApi() {
        new CommonAsync(this, BASEURL + end_url, getStringFromIntent, new CommonModelCallBack() {
            @Override
            public void onSuccess(CommonDataModel dataModel) {
                title_tv.setText(dataModel.getContent_title());
                title_details.setText(dataModel.getContent());
            }

            @Override
            public void onFailure(String s) {

            }
        }).execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //handle the home button onClick event here.
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
