package com.shampaskitchen.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.shampaskitchen.R;
import com.shampaskitchen.ShampaKitchenApp;
import com.shampaskitchen.adapter.SubCategoryAdapter;
import com.shampaskitchen.async.SubCategoryAsync;
import com.shampaskitchen.interfaces.ItemClickListener;
import com.shampaskitchen.interfaces.SubCategoryCallback;
import com.shampaskitchen.model.SubCategoryModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubCategoryActivity extends Activity {

    @BindView(R.id.sub_category_grid)
    GridView sub_cat_grid;

//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    private String catID = "";
    private SubCategoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        ButterKnife.bind(this);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("id")) {
            catID = getIntent().getStringExtra("id");
        }

        callApi();
    }

    private void callApi() {
        new SubCategoryAsync(this, new SubCategoryCallback() {
            @Override
            public void onSuccess(final ArrayList<SubCategoryModel> arrayList) {
                adapter = new SubCategoryAdapter(SubCategoryActivity.this, arrayList, new ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(SubCategoryActivity.this, MenuListActivity.class);
                        intent.putExtra("id", arrayList.get(position).getCategory_id());
                        intent.putExtra("sid", arrayList.get(position).getSubcategory_id());
                        startActivity(intent);
                        ShampaKitchenApp.dishType=arrayList.get(position).getSubcategory_name();
                        finish();
                    }
                });
                sub_cat_grid.setAdapter(adapter);
            }

            @Override
            public void onFailure(String s) {
                Toast.makeText(SubCategoryActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }, catID).execute();
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
