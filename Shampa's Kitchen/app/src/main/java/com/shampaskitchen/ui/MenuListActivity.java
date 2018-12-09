package com.shampaskitchen.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.shampaskitchen.R;
import com.shampaskitchen.ShampaKitchenApp;
import com.shampaskitchen.adapter.CategoryGridAdapter;
import com.shampaskitchen.adapter.MenuGridAdapter;
import com.shampaskitchen.async.MenuAsync;
import com.shampaskitchen.async.MenuSearchAsync;
import com.shampaskitchen.interfaces.ItemClickListener;
import com.shampaskitchen.interfaces.MenuCallback;
import com.shampaskitchen.model.MenuModel;
import com.shampaskitchen.utility.KitchenUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuListActivity extends AppCompatActivity {

    @BindView(R.id.category_grid)
    GridView gridView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String catID = "", sCatId = "";
    private MenuGridAdapter menuGridAdapter;
    private ArrayList<MenuModel> arrayListt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(ShampaKitchenApp.dishType);
        if (getIntent().hasExtra("id")) {
            catID = getIntent().getStringExtra("id");
            //sCatId = getIntent().getStringExtra("sid");
        }

        if (KitchenUtil.isOnline(this))
            callApi();
        else Toast.makeText(this, "No Network Available", Toast.LENGTH_SHORT).show();
    }

    private void callApi() {
        new MenuAsync(this, new MenuCallback() {
            @Override
            public void onSuccess(final ArrayList<MenuModel> arrayList) {
//                initView(arrayList);
                if (menuGridAdapter != null) {
                    menuGridAdapter.updateAdapter(arrayList);
                } else {
                    arrayListt = arrayList;
                    menuGridAdapter = new MenuGridAdapter(MenuListActivity.this, arrayList, new ItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(MenuListActivity.this, MenuActivity.class);
                            intent.putExtra("id", arrayListt.get(position).getCategory_id());
                            intent.putExtra("mid", arrayListt.get(position).getMenu_id());
                            intent.putExtra("pos", position);
                            startActivity(intent);
                        }
                    });
                    gridView.setAdapter(menuGridAdapter);
                }

            }

            @Override
            public void onFailure(String s) {
                Toast.makeText(MenuListActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }, "0", catID,true).execute();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem search_item = menu.findItem(R.id.mi_search);

        SearchView searchView = (SearchView) search_item.getActionView();
        searchView.setFocusable(false);
        searchView.setQueryHint("Search");
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                callApi();
                return false;
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String s) {
                searchCall(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        return true;
    }

    private void searchCall(String s) {
        new MenuSearchAsync(this, s, new MenuCallback() {
            @Override
            public void onSuccess(ArrayList<MenuModel> arrayList) {
                arrayListt = arrayList;
                menuGridAdapter.updateAdapter(arrayListt);
            }

            @Override
            public void onFailure(String s) {
                Toast.makeText(MenuListActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }
}
