package com.shampaskitchen.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shampaskitchen.R;
import com.shampaskitchen.adapter.MenuListAdapter;
import com.shampaskitchen.async.PostReviewAsync;
import com.shampaskitchen.async.TipsAndTricksAsync;
import com.shampaskitchen.custom.FixedHeightListView;
import com.shampaskitchen.interfaces.PostReviewCallBack;
import com.shampaskitchen.interfaces.TipsAndTrickCallback;
import com.shampaskitchen.model.TipsAndTricksModel;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TipsAndTricksDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.title_tv)
    TextView title_tv;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.title_details)
    JustifiedTextView title_details;

    @BindView(R.id.no_review_tv)
    TextView no_review_tv;

    @BindView(R.id.review_list)
    FixedHeightListView review_list;

    @BindView(R.id.ratingbar)
    AppCompatRatingBar ratingbar;

    @BindView(R.id.rater_name_edt)
    AppCompatEditText rater_name_edt;

    @BindView(R.id.rater_desc_edt)
    AppCompatEditText rater_desc_edt;

    @BindView(R.id.submit_btn_tv)
    TextView submit_btn_tv;

    private int position = 0;
    private MenuListAdapter adapter;
    private String tipsId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_and_tricks_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Tips");
        if (getIntent().hasExtra("pos")) {
            position = getIntent().getIntExtra("pos", 0);
        }

        callApi();
    }

    private void callApi() {
        new TipsAndTricksAsync(this, new TipsAndTrickCallback() {
            @Override
            public void onSuccess(ArrayList<TipsAndTricksModel> arrayList) {
                init(arrayList);
            }

            @Override
            public void onFailure(String s) {
                Toast.makeText(TipsAndTricksDetailsActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

    private void init(ArrayList<TipsAndTricksModel> arrayList) {
        tipsId = arrayList.get(position).getTips_id();
        title_tv.setText(arrayList.get(position).getTitle());
        title_details.setText(arrayList.get(position).getDetails());
        if (arrayList.get(position).getReviewModelArrayList() != null) {
            adapter = new MenuListAdapter(this, arrayList.get(position).getReviewModelArrayList());
            review_list.setAdapter(adapter);
        } else {
            no_review_tv.setVisibility(View.VISIBLE);
        }


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

    @OnClick(R.id.submit_btn_tv)
    @Override
    public void onClick(View view) {
        if (ratingbar.getRating() < 1) {
            Toast.makeText(this, "Rating star can't be blank!", Toast.LENGTH_SHORT).show();
            return;

        } else if (rater_name_edt.getText().toString().isEmpty()) {
            rater_name_edt.setError("Field can't be blank!");
            return;
        } else if (rater_desc_edt.getText().toString().isEmpty()) {
            rater_desc_edt.setError("Field can't be blank!");
            return;
        }
        new PostReviewAsync(TipsAndTricksDetailsActivity.this, rater_name_edt.getText().toString(), rater_desc_edt.getText().toString(),
                ratingbar.getRating(), tipsId, true, new PostReviewCallBack() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(TipsAndTricksDetailsActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String s) {
                Toast.makeText(TipsAndTricksDetailsActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }
}
