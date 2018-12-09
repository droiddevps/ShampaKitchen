package com.shampaskitchen.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shampaskitchen.R;
import com.shampaskitchen.async.UploadAsync;
import com.shampaskitchen.interfaces.UploadCallBack;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.utils.Orientation;
import pub.devrel.easypermissions.EasyPermissions;

import static com.shampaskitchen.networking.Urls.BASEURL;

public class SubmitRecipeActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int RC_FILE_PICKER_PERM = 321;
    private int MAX_ATTACHMENT_COUNT = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.name_edt)
    AppCompatEditText name_edt;

    @BindView(R.id.email_edt)
    AppCompatEditText email_edt;

    @BindView(R.id.mobile_edt)
    AppCompatEditText mobile_edt;

    @BindView(R.id.rcp_name_edt)
    AppCompatEditText rcp_name_edt;

    @BindView(R.id.prepare_time_edt)
    AppCompatEditText prepare_time_edt;

    @BindView(R.id.serveto_edt)
    AppCompatEditText serveto_edt;

    @BindView(R.id.ingredients_edt)
    AppCompatEditText ingredients_edt;

    @BindView(R.id.making_procedure_edt)
    AppCompatEditText making_procedure_edt;

    @BindView(R.id.choose_file_btn_tv)
    TextView choose_file_btn_tv;

    @BindView(R.id.file_name_tv)
    TextView file_name_tv;

    @BindView(R.id.form_submit_tv)
    TextView form_submit_tv;

    @BindView(R.id.success_tv)
    TextView success_tv;

    private ArrayList<String> photoPaths;
    private String file_name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_recipe);
        ButterKnife.bind(this);
        setTitle("Submit Recipe");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick({R.id.form_submit_tv, R.id.choose_file_btn_tv})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.form_submit_tv:
                if (success_tv.isShown())
                    success_tv.setVisibility(View.GONE);
                validateData();
                break;
            case R.id.choose_file_btn_tv:
                if (EasyPermissions.hasPermissions(this, FilePickerConst.PERMISSIONS_FILE_PICKER)) {
                    onPickPic();
                } else {
                    // Ask for one permission
                    EasyPermissions.requestPermissions(this, getString(R.string.rationale_doc_picker),
                            RC_FILE_PICKER_PERM, FilePickerConst.PERMISSIONS_FILE_PICKER);
                }
                break;
        }
    }

    private void onPickPic() {
        FilePickerBuilder.getInstance()
                .setMaxCount(1)
                .setSelectedFiles(photoPaths)
                .setActivityTheme(R.style.FilePickerTheme)
                .setActivityTitle("Please select media")
                .enableVideoPicker(false)
                .enableCameraSupport(true)
                .showGifs(false)
                .showFolderView(false)
                .enableSelectAll(true)
                .enableImagePicker(true)
                .setCameraPlaceholder(android.R.drawable.ic_menu_camera)
                .withOrientation(Orientation.UNSPECIFIED)
                .pickPhoto(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE_PHOTO:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    photoPaths = new ArrayList<>();
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));
                    file_name = photoPaths.get(0);
                    file_name_tv.setText(file_name);
                }
                break;
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

    private void validateData() {
        if (name_edt.getText().toString().isEmpty()) {
            name_edt.setError("Field can't be blank!");
        } else if (email_edt.getText().toString().isEmpty()) {
            email_edt.setError("Field can't be blank!");
        } else if (mobile_edt.getText().toString().isEmpty()) {
            mobile_edt.setError("Field can't be blank!");
        } else if (rcp_name_edt.getText().toString().isEmpty()) {
            rcp_name_edt.setError("Field can't be blank!");
        } else if (ingredients_edt.getText().toString().isEmpty()) {
            ingredients_edt.setError("Field can't be blank!");
        } else if (making_procedure_edt.getText().toString().isEmpty()) {
            making_procedure_edt.setError("Field can't be blank!");
        } else {
            callApiSubmit();
        }
    }

    //    post_recipe&name=Kunal Sarkar&email=kunal.sarkar2810@gmail.com&phone
//    =9007921046&recipe_name=Mutton Biriyani&preparation_time=15 minutes&serve_for=7&ingredients=testing&making_procedure=demo
    private void callApiSubmit() {

        String url = BASEURL + "post_recipe&name=" + name_edt.getText().toString().trim().replace(" ", "_") + "&email=" + email_edt.getText().toString().trim().replace(" ", "_") + "&phone=" + mobile_edt.getText().toString() +
                "&recipe_name=" + rcp_name_edt.getText().toString().trim().replace(" ", "_") + "&preparation_time=" + prepare_time_edt.getText().toString().trim().replace(" ", "_") +
                "Mins" + "&serve_for=" + serveto_edt.getText().toString().trim().replace(" ", "_") + "&ingredients=" + ingredients_edt.getText().toString().trim().replace(" ", "_") +
                "&making_procedure=" + making_procedure_edt.getText().toString().trim().replace(" ", "_");

        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

        if (!TextUtils.isEmpty(file_name)) {
            File file = new File(file_name);
            if (file.exists()) {

                ContentBody cbFile = new FileBody(file, "image/*");
                cbFile.getMediaType();
                // Add the data to the multipart entity
                entity.addPart("recipe_photo", cbFile);

            }
        }

        new UploadAsync(this, entity, url, new UploadCallBack() {
            @Override
            public void onResponse(String s) {
                Toast.makeText(SubmitRecipeActivity.this, s, Toast.LENGTH_LONG).show();
                success_tv.setVisibility(View.VISIBLE);
            }
        }).execute();

    }
}
