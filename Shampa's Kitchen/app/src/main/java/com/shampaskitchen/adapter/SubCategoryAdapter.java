package com.shampaskitchen.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shampaskitchen.R;
import com.shampaskitchen.interfaces.ItemClickListener;
import com.shampaskitchen.model.SubCategoryModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sayantan on 4/3/18.
 */

public class SubCategoryAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<SubCategoryModel> arrayList;
    private ItemClickListener clickListener;
    private LayoutInflater layoutInflater;
    private Holder holder;
    private boolean b = false;

    public SubCategoryAdapter(Context context, ArrayList<SubCategoryModel> arrayList, ItemClickListener clickListener) {
        this.context = context;
        this.arrayList = arrayList;
        this.clickListener = clickListener;
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
        return Long.parseLong(arrayList.get(i).getSubcategory_id());
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.inflate_sub_category_grid_item, null);
            holder = new Holder(view);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.sub_cat_txtv.setText(arrayList.get(i).getSubcategory_name());
        holder.rl_item_main.setBackgroundDrawable(context.getDrawable(R.drawable.buttonbg2));
        holder.rl_item_main.setPadding((int) context.getResources().getDimension(R.dimen._20sdp), (int) context.getResources().getDimension(R.dimen._20sdp), (int) context.getResources().getDimension(R.dimen._20sdp), (int) context.getResources().getDimension(R.dimen._20sdp));
        holder.sub_cat_txtv.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        if (i % 2 != 0) {
            if (b) {
                holder.rl_item_main.setBackgroundDrawable(context.getDrawable(R.drawable.buttonbg));
                holder.rl_item_main.setPadding((int) context.getResources().getDimension(R.dimen._20sdp), (int) context.getResources().getDimension(R.dimen._20sdp), (int) context.getResources().getDimension(R.dimen._20sdp), (int) context.getResources().getDimension(R.dimen._20sdp));
                holder.sub_cat_txtv.setTextColor(context.getResources().getColor(R.color.colorAccent));
            }
        } else {
            if (b) {
                holder.rl_item_main.setBackgroundDrawable(context.getDrawable(R.drawable.buttonbg));
                holder.rl_item_main.setPadding((int) context.getResources().getDimension(R.dimen._20sdp), (int) context.getResources().getDimension(R.dimen._20sdp), (int) context.getResources().getDimension(R.dimen._20sdp), (int) context.getResources().getDimension(R.dimen._20sdp));
                holder.sub_cat_txtv.setTextColor(context.getResources().getColor(R.color.colorAccent));
                b = false;
            } else b = true;
        }
        holder.rl_item_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(i);
            }
        });
        return view;
    }

    class Holder {
        @BindView(R.id.sub_cat_txtv)
        AppCompatTextView sub_cat_txtv;

        @BindView(R.id.rl_item_main)
        RelativeLayout rl_item_main;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
