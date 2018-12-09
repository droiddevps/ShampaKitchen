package com.shampaskitchen.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.shampaskitchen.R;
import com.shampaskitchen.custom.FixedHeightListView;
import com.shampaskitchen.model.MenuModel;
import com.shampaskitchen.model.ReviewModel;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sayantan on 4/3/18.
 */

public class MenuListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ReviewModel> arrayList;
    private LayoutInflater layoutInflater;
    private Holder holder;

    public MenuListAdapter(Context context, ArrayList<ReviewModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return (arrayList != null) ? arrayList.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(arrayList.get(i).getReview_id());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.menu_screen_rate_list_item, null);
            holder = new Holder(view);
            view.setTag(holder);
        } else holder = (Holder) view.getTag();
        holder.ratingBar.setRating(Float.parseFloat(arrayList.get(i).getRate()));
        holder.rater_name_tv.setText(arrayList.get(i).getPosted_by());
        holder.rating_date.setText(arrayList.get(i).getReview_date());
        holder.review_tv.setText(arrayList.get(i).getReview());
        return view;
    }

    class Holder {


        @BindView(R.id.rate_list_ratingbar)
        AppCompatRatingBar ratingBar;

        @BindView(R.id.rater_name_tv)
        AppCompatTextView rater_name_tv;

        @BindView(R.id.rating_date)
        AppCompatTextView rating_date;

        @BindView(R.id.review_tv)
        JustifiedTextView review_tv;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
