package com.shampaskitchen.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.youtube.player.YouTubePlayer;
import com.shampaskitchen.R;
import com.shampaskitchen.adapter.MenuListAdapter;
import com.shampaskitchen.async.MenuAsync;
import com.shampaskitchen.async.PostReviewAsync;
import com.shampaskitchen.custom.FixedHeightListView;
import com.shampaskitchen.custom.RoundedCornersTransformation;
import com.shampaskitchen.interfaces.MenuCallback;
import com.shampaskitchen.interfaces.PostReviewCallBack;
import com.shampaskitchen.model.MenuModel;
import com.shampaskitchen.utility.KitchenUtil;
import com.thefinestartist.ytpa.YouTubePlayerActivity;
import com.thefinestartist.ytpa.enums.Orientation;
import com.thefinestartist.ytpa.utils.YouTubeUrlParser;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.menu_title_tv)
    AppCompatTextView menu_title_tv;

    @BindView(R.id.menu_item_iv)
    AppCompatImageView menu_item_iv;

    @BindView(R.id.menu_banner)
    AppCompatImageView menu_banner;

    @BindView(R.id.prepare_tym_txtv)
    AppCompatTextView prepare_tym_txtv;

    @BindView(R.id.serve_to_txtv)
    AppCompatTextView serve_to_txtv;

    @BindView(R.id.ingredients_tv)
    JustifiedTextView ingredients_tv;

    @BindView(R.id.recipe_tv)
    JustifiedTextView recipe_tv;

    @BindView(R.id.hindi_tv)
    AppCompatTextView hindi_tv;

    @BindView(R.id.eng_tv)
    AppCompatTextView eng_tv;

    @BindView(R.id.see_vdo_tv)
    AppCompatTextView see_vdo_tv;

    @BindView(R.id.review_list)
    FixedHeightListView review_list;

    @BindView(R.id.submit_btn_tv)
    AppCompatTextView submit_review_tv;

    @BindView(R.id.ratingbar)
    AppCompatRatingBar ratingbar;

    @BindView(R.id.rater_name_edt)
    AppCompatEditText rater_name_edt;

    @BindView(R.id.rater_desc_edt)
    AppCompatEditText rater_desc_edt;

    @BindView(R.id.no_review_tv)
    TextView no_review_tv;

    @BindView(R.id.share_btn)
    AppCompatImageView share_btn;

    private String catID = "", sCatId = "";
    private int position = 0;
    private MenuListAdapter adapter;
    private String hindiTxt = "", engTxt = "";
    private String vdoUrl = "", menuId = "";
    private String pageLink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Recipe");

        if (getIntent().hasExtra("id") && getIntent().hasExtra("mid") && getIntent().hasExtra("pos")) {
            catID = getIntent().getStringExtra("id");
            sCatId = getIntent().getStringExtra("mid");
            position = getIntent().getIntExtra("pos", 0);
            position = 0;
        }

        callApi();

        //review_list.setAdapter(new MenuListAdapter(this, null));
    }

    private void callApi() {
        new MenuAsync(this, new MenuCallback() {
            @Override
            public void onSuccess(ArrayList<MenuModel> arrayList) {
                initView(arrayList);
            }

            @Override
            public void onFailure(String s) {
                Toast.makeText(MenuActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        }, sCatId, catID, false).execute();
    }

    private void initView(ArrayList<MenuModel> arrayList) {
        menu_title_tv.setText(arrayList.get(position).getMenu_name());
        pageLink = arrayList.get(position).getPage_link();
        vdoUrl = arrayList.get(position).getYoutube_video();
        Glide.with(this).load(arrayList.get(position).getPhoto()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(this, 15, 2))).into(menu_item_iv);
        Glide.with(this).load(arrayList.get(position).getAd_banner()).apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(this, 15, 2))).into(menu_banner);
        prepare_tym_txtv.setText("Preparation Time: " + arrayList.get(position).getPreparation_time() + " Mins");
        serve_to_txtv.setText("Serve to: " + arrayList.get(position).getNo_of_people() + " People");
        ingredients_tv.setText(arrayList.get(position).getIngredients());
        engTxt = arrayList.get(position).getMaking_process_english();
        recipe_tv.setText(engTxt);
        menuId = arrayList.get(position).getMenu_id();
        if (arrayList.get(position).getReviewModelArrayList() != null) {
            review_list.setAdapter(new MenuListAdapter(this, arrayList.get(position).getReviewModelArrayList()));
        } else no_review_tv.setVisibility(View.VISIBLE);

        eng_tv.setTypeface(null, Typeface.BOLD);
        byte[] data = Base64.decode(arrayList.get(position).getMaking_process_hindi(), Base64.DEFAULT);
        try {
            hindiTxt = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


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

    @OnClick({R.id.hindi_tv, R.id.eng_tv, R.id.see_vdo_tv, R.id.submit_btn_tv, R.id.share_btn})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hindi_tv:
                hindi_tv.setTypeface(null, Typeface.BOLD | Typeface.ITALIC);
                hindi_tv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                eng_tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                eng_tv.setTypeface(null, Typeface.NORMAL | Typeface.ITALIC);
                recipe_tv.setText(hindiTxt);
                break;
            case R.id.eng_tv:
                hindi_tv.setTypeface(null, Typeface.NORMAL | Typeface.ITALIC);
                hindi_tv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                eng_tv.setTypeface(null, Typeface.BOLD | Typeface.ITALIC);

                eng_tv.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                recipe_tv.setText(engTxt);
                break;
            case R.id.see_vdo_tv:
                Intent intent = new Intent(MenuActivity.this, YouTubePlayerActivity.class);

                intent.putExtra(YouTubePlayerActivity.EXTRA_VIDEO_ID, YouTubeUrlParser.getVideoId(vdoUrl));

                intent.putExtra(YouTubePlayerActivity.EXTRA_PLAYER_STYLE, YouTubePlayer.PlayerStyle.DEFAULT);

                intent.putExtra(YouTubePlayerActivity.EXTRA_ORIENTATION, Orientation.AUTO);

                intent.putExtra(YouTubePlayerActivity.EXTRA_SHOW_AUDIO_UI, true);

                intent.putExtra(YouTubePlayerActivity.EXTRA_HANDLE_ERROR, true);

                intent.putExtra(YouTubePlayerActivity.EXTRA_ANIM_ENTER, android.R.anim.fade_in);
                intent.putExtra(YouTubePlayerActivity.EXTRA_ANIM_EXIT, android.R.anim.fade_out);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.submit_btn_tv:
                if (ratingbar.getRating() < 1) {
                    Toast.makeText(this, "Rating star can't be blank!", Toast.LENGTH_SHORT).show();
                    return;

                } else if (rater_name_edt.getText().toString().isEmpty()) {
                    rater_name_edt.setError("Field can't be blank!");
                    return;
                } else if (rater_desc_edt.getText().toString().isEmpty()) {
                    rater_desc_edt.setError("Field can't be blank!");
                    return;
                }
                new PostReviewAsync(MenuActivity.this, rater_name_edt.getText().toString(), rater_desc_edt.getText().toString(),
                        ratingbar.getRating(), menuId, false, new PostReviewCallBack() {
                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(MenuActivity.this, s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(String s) {
                        Toast.makeText(MenuActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                }).execute();
                break;

            case R.id.share_btn:
                KitchenUtil.shareLink(this, menu_title_tv.getText().toString(),pageLink );
                break;
        }
    }
}
