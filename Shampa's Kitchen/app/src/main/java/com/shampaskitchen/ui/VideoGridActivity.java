package com.shampaskitchen.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.shampaskitchen.R;
import com.shampaskitchen.adapter.VideoRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoGridActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.video_recycler_list)
    RecyclerView video_recycler_list;

    VideoRecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_grid);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Video Collections");

        init();
    }

    private void init() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        video_recycler_list.setLayoutManager(mLayoutManager);
        video_recycler_list.setItemAnimator(new DefaultItemAnimator());



    }
}
