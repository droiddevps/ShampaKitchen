package com.shampaskitchen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.shampaskitchen.R;
import com.shampaskitchen.ShampaKitchenApp;
import com.shampaskitchen.adapter.CategoryGridAdapter;
import com.shampaskitchen.async.CategoryAsync;
import com.shampaskitchen.interfaces.CategoryCallback;
import com.shampaskitchen.interfaces.ItemClickListener;
import com.shampaskitchen.model.CategoryModel;
import com.shampaskitchen.utility.KitchenUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.category_grid)
    GridView gridView;

    private CategoryGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getResources().getString(R.string.app_name));


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (KitchenUtil.isOnline(this))
            callApi();
        else Toast.makeText(this, "No Network Available", Toast.LENGTH_SHORT).show();
    }

    private void callApi() {
        new CategoryAsync(this, new CategoryCallback() {
            @Override
            public void onSuccess(final ArrayList<CategoryModel> categoryModelList) {
                adapter = new CategoryGridAdapter(DashboardActivity.this, categoryModelList, new ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (categoryModelList.get(position).getSubcategory_exists().equalsIgnoreCase(getString(R.string.sub_category_no))) {
                            Toast.makeText(DashboardActivity.this, "Details Un-available", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent = new Intent(DashboardActivity.this, MenuListActivity.class);
                        intent.putExtra("id", categoryModelList.get(position).getCategory_id());
                        startActivity(intent);
                        ShampaKitchenApp.dishType=categoryModelList.get(position).getCategory_name();
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
//                        Toast.makeText(DashboardActivity.this, position, Toast.LENGTH_SHORT).show();
                    }
                });
                gridView.setAdapter(adapter);
            }

            @Override
            public void onFailure(String string) {
                Toast.makeText(DashboardActivity.this, string, Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about_us) {
            // Handle the camera action
            Intent intent = new Intent(DashboardActivity.this, CommonActivity.class);
            intent.putExtra("type", KitchenUtil.ABOUTUS);
            startActivity(intent);
        } else if (id == R.id.nav_terms) {
            Intent intent = new Intent(DashboardActivity.this, CommonActivity.class);
            intent.putExtra("type", KitchenUtil.TERMS);
            startActivity(intent);

        } else if (id == R.id.nav_privacy) {
            Intent intent = new Intent(DashboardActivity.this, CommonActivity.class);
            intent.putExtra("type", KitchenUtil.PRIVACY);
            startActivity(intent);

        } else if (id == R.id.nav_submit_recipe) {
            Intent intent = new Intent(DashboardActivity.this, SubmitRecipeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_tips_trick) {
            startActivity(new Intent(DashboardActivity.this, TipsAndTrickActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
