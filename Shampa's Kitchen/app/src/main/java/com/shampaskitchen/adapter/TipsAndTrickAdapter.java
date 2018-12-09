package com.shampaskitchen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shampaskitchen.R;
import com.shampaskitchen.interfaces.ItemClickListener;
import com.shampaskitchen.model.TipsAndTricksModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sayantan on 17/3/18.
 */

public class TipsAndTrickAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TipsAndTricksModel> arrayList;
    private LayoutInflater layoutInflater;
    private Holder holder;
    private ItemClickListener listener;

    public TipsAndTrickAdapter(Context context, ArrayList<TipsAndTricksModel> arrayList, ItemClickListener listener) {
        this.context = context;
        this.arrayList = arrayList;
        this.listener = listener;
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.tips_tricks_list_item, null);
            holder = new Holder(view);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.tips_trick_details_tv.setText(arrayList.get(i).getDetails());
        holder.tips_trick_tv.setText(arrayList.get(i).getTitle());
        holder.rl_title_head.setTag(i);
        holder.rl_title_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick((Integer) view.getTag());
            }
        });
        return view;
    }

    class Holder {
        @BindView(R.id.tips_trick_tv)
        TextView tips_trick_tv;

        @BindView(R.id.tips_trick_details_tv)
        TextView tips_trick_details_tv;

        @BindView(R.id.rl_title_head)
        RelativeLayout rl_title_head;

        private Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
