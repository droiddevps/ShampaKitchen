package com.shampaskitchen.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.shampaskitchen.R;
import com.shampaskitchen.custom.RoundedCornersTransformation;
import com.shampaskitchen.interfaces.ItemClickListener;
import com.shampaskitchen.model.CategoryModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sayantan on 3/3/18.
 */

public class CategoryGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CategoryModel> arrayList;
    private ItemClickListener clickListener;
    private LayoutInflater layoutInflater;
    private Holder holder;

    public CategoryGridAdapter(Context context, ArrayList<CategoryModel> arrayList, ItemClickListener clickListener) {
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
        return Long.parseLong(arrayList.get(i).getCategory_id());
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.category_grid_item, null);
            holder = new Holder(view);
            view.setTag(holder);
        } else holder = (Holder) view.getTag();

        Glide.with(context).load(arrayList.get(i).getCategory_photo()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(context, 3, 2))).into(holder.imageView);
        holder.textView.setText(arrayList.get(i).getCategory_name());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(i);
            }
        });
        return view;
    }

    class Holder {
        @BindView(R.id.grid_food_cat_imgv)
        AppCompatImageView imageView;

        @BindView(R.id.category_name_txtV)
        TextView textView;

        @BindView(R.id.category_rl)
        RelativeLayout layout;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
