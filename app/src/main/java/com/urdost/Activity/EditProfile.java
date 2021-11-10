package com.urdost.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.FileUtils;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseActivity;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.response.ResponseUpdateProfile;
import com.urdost.model.response.responseViewProfile.ResponseViewProfile;
import com.google.gson.JsonObject;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfile extends BaseActivity  implements IPickCancel, IPickResult {


    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.img_member)
    ImageView imgMember;
    @BindView(R.id.tv_spill_id)
    TextView tvSpillId;
    @BindView(R.id.tv_spill_name)
    TextView tvSpillName;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.tv_Contact)
    TextView tvContact;
    @BindView(R.id.et_Email)
    EditText etEmail;
    @BindView(R.id.male)
    RadioButton male;
    @BindView(R.id.female)
    RadioButton female;
    @BindView(R.id.rg_sex)
    RadioGroup rgSex;
    @BindView(R.id.et_pincode)
    EditText etPincode;
    @BindView(R.id.cardview1)
    RelativeLayout cardview1;
    @BindView(R.id.tv_joining_date)
    TextView tvJoiningDate;
    @BindView(R.id.et_account_number)
    EditText etAccountNumber;
    @BindView(R.id.et_bank_name)
    EditText etBankName;
    @BindView(R.id.et_branch_name)
    EditText etBranchName;
    @BindView(R.id.et_ifsc)
    EditText etIfsc;
    @BindView(R.id.et_Adhar_no)
    EditText etAdharNo;
    @BindView(R.id.et_pan_no)
    EditText etPanNo;
    @BindView(R.id.tv_summary)
    EditText tvSummary;
    @BindView(R.id.btn_update)
    Button btnUpdate;

    String leg = "L", gender = "Male";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cardview2)
    CardView cardview2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        ButterKnife.bind(this);
        tvTitle.setText("MY PROFILE");

        rgSex.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.female)
                gender = "Female";
            else gender = "Male";
        });


        getData();
    }

    @OnClick({R.id.img_back, R.id.img, R.id.img_member, R.id.btn_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.img:
                showDialog();
                break;
           /* case R.id.img_member:
                showDialog();
                break;*/
            case R.id.btn_update:
                getUpdateProfile();

                break;
        }
    }

    private void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("UserId", PreferencesManager.getInstance(context).getPk_userId());
        LoggerUtil.logItem(object);

        Call<ResponseViewProfile> call = apiServices.getViewProfile(object);
        call.enqueue(new Callback<ResponseViewProfile>() {
            @Override
            public void onResponse(Call<ResponseViewProfile> call, Response<ResponseViewProfile> response) {
                hideLoading();
                try {
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        Glide.with(context).load(response.body().getLstViewProfile().getProfilePicture()).placeholder(
                                R.drawable.digi_logo
                        ).error(R.drawable.ic_icon__2_07).into(img);
                        tvSpillId.setText(response.body().getLstViewProfile().getSponsorId());
                        tvSpillName.setText(response.body().getLstViewProfile().getSponsorName());
                        tvName.setText(response.body().getLstViewProfile().getFirstName());
                        etLastName.setText(response.body().getLstViewProfile().getLastName());
                        tvContact.setText(response.body().getLstViewProfile().getMobile());
                        etEmail.setText(response.body().getLstViewProfile().getEmailId());
                        tvJoiningDate.setText(response.body().getLstViewProfile().getJoiningDate());
                        etAccountNumber.setText(response.body().getLstViewProfile().getAccountNumber());
                        etBankName.setText(response.body().getLstViewProfile().getBankName());
                        etBranchName.setText(response.body().getLstViewProfile().getBankBranch());
                        etAdharNo.setText(response.body().getLstViewProfile().getAdharNo());
                        etIfsc.setText(response.body().getLstViewProfile().getIfsc());
                        tvSummary.setText(response.body().getLstViewProfile().getSummary());
                       // etPincode.setText(response.body().getLstViewProfile().getPin);

                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseViewProfile> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void getUpdateProfile() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("PK_UserID", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("FirstName", etLastName.getText().toString().trim());
        object.addProperty("EmailId", etEmail.getText().toString().trim());
        object.addProperty("PinCode", etPincode.getText().toString().trim());
        object.addProperty("Gender", gender);
        object.addProperty("AccountNumber", etAccountNumber.getText().toString().trim());
        object.addProperty("AdharNo", etAdharNo.getText().toString().trim());
        object.addProperty("BankBranch", etBranchName.getText().toString().trim());
        object.addProperty("BankName", etBankName.getText().toString().trim());
        object.addProperty("PanNumber", etPanNo.getText().toString().trim());
        object.addProperty("IFSC", etIfsc.getText().toString().trim());
        object.addProperty("Summary", tvSummary.getText().toString().trim());

        LoggerUtil.logItem(object);

        Call<ResponseUpdateProfile> call = apiServices.getUpdateProfile(object);
        call.enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
                hideLoading();
                try {
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        showMessage(response.body().getMessage());
                        onBackPressed();
                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {
                hideLoading();
            }
        });
    }

    PickImageDialog dialog;

    void showDialog() {
        PickSetup pickSetup = new PickSetup();
        pickSetup.setTitle("Select Image");
        pickSetup.setPickTypes(EPickType.GALLERY, EPickType.CAMERA);
        pickSetup.setGalleryIcon(com.vansuita.pickimage.R.mipmap.gallery_colored);
        pickSetup.setCameraIcon(com.vansuita.pickimage.R.mipmap.camera_colored);
        pickSetup.setCancelTextColor(R.color.colorAccent);
        // If show system dialog..
//        pickSetup.setSystemDialog(true);

        dialog = PickImageDialog.build(pickSetup);
        dialog.setOnPickCancel(this);
        dialog.show(getSupportFragmentManager());
    }

    @Override
    public void onPickResult(PickResult pickResult) {
        Log.e("RESULT", "= " + pickResult.getPath());
        if (pickResult.getError() == null) {
            CropImage.activity(pickResult.getUri()).setCropShape(CropImageView.CropShape.RECTANGLE)
                    .start(context);
        } else {
            Log.e("RESULT", "ERROR = " + pickResult.getError());
        }
    }

    File homeWorkFile, compressedImageFile;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                homeWorkFile = FileUtils.getFile(context, result.getUri());
                compressedImageFile = new Compressor.Builder(this)
                        .setMaxWidth(800)
                        .setMaxHeight(640)
                        .setQuality(100)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .build()
                        .compressToFile(homeWorkFile);
                uploadProfilePic();
                /// Toast                               .makeText(context, result.getUri()+"", Toast.LENGTH_SHORT).show();
                Log.e("ADDRESS File ", homeWorkFile.toString());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                // Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
//                        Exception error = result.getError();
            }
        }
    }

    private void uploadProfilePic() {
        LoggerUtil.logItem(compressedImageFile.length());
        LoggerUtil.logItem(homeWorkFile.length());
        showLoading();
        RequestBody requestBody;
        MultipartBody.Part body = null;
        requestBody = RequestBody.create(MediaType.parse("image/*"), homeWorkFile);
        body = MultipartBody.Part.createFormData("ProfilePic", homeWorkFile.getName(), requestBody);
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), PreferencesManager.getInstance(context).getPk_userId());
        //RequestBody adharnumber = RequestBody.create(MediaType.parse("text/plain"), tvAdharNumber.getText().toString().trim());
        Call<ResponseStatusMessage> call = apiServices.uploadProfilePic(userId, body);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                try {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        // hideLoading();
                        showMessage(response.body().getMessage());

                        LoggerUtil.logItem(response.body());

                    } else showMessage(response.body().getMessage());


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                LoggerUtil.logItem(t.getMessage());
                hideLoading();
            }
        });
    }

    @Override
    public void onCancelClick() {

    }
}

