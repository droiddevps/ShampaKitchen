package com.shampaskitchen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.shampaskitchen.R;
import com.shampaskitchen.model.VideoDataItem;
import com.shampaskitchen.utility.KitchenUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoRecyclerAdapter extends RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder> {
    private Context context;
    private ArrayList<VideoDataItem> arrayList;

    public VideoRecyclerAdapter(Context context, ArrayList<VideoDataItem> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String videoImmageUrl = KitchenUtil.getYoutubeThumbnailUrlFromVideoUrl(arrayList.get(position).getVideoLink());
        Glide.with(context).load(videoImmageUrl).into(holder.video_thumb);
        holder.video_title_text.setText(arrayList.get(position).getVideoTitle());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null) ? arrayList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_thumb)
        AppCompatImageView video_thumb;
        @BindView(R.id.rl_video_main)
        RelativeLayout rl_video_main;
        @BindView(R.id.video_title_text)
        AppCompatTextView video_title_text;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
