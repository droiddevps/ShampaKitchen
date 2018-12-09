package com.shampaskitchen.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.shampaskitchen.R;
import com.shampaskitchen.adapter.TipsAndTrickAdapter;
import com.shampaskitchen.async.TipsAndTricksAsync;
import com.shampaskitchen.interfaces.ItemClickListener;
import com.shampaskitchen.interfaces.TipsAndTrickCallback;
import com.shampaskitchen.model.TipsAndTricksModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TipsAndTrickActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tips_and_tricks_list)
    ListView tips_and_tricks_list;

    TipsAndTrickAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_and_trick);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Tips & Tricks");
        apiCall();
    }

    private void apiCall() {
        new TipsAndTricksAsync(this, new TipsAndTrickCallback() {
            @Override
            public void onSuccess(ArrayList<TipsAndTricksModel> arrayList) {
                init(arrayList);
            }

            @Override
            public void onFailure(String s) {
                Toast.makeText(TipsAndTrickActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

    private void init(ArrayList<TipsAndTricksModel> arrayList) {
        adapter = new TipsAndTrickAdapter(this, arrayList, new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(TipsAndTrickActivity.this, TipsAndTricksDetailsActivity.class);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });
        tips_and_tricks_list.setAdapter(adapter);
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
