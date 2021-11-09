package com.urdost.Activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.urdost.Adapter.AadharcardAdapter;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.ChoosePhoto;
import com.urdost.common.FileUtils;
import com.urdost.common.LoggerUtil;
import com.urdost.common.Utils;
import com.urdost.constants.BaseActivity;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.response.ReponsePanCard;
import com.urdost.model.response.ResponseAadharData;
import com.urdost.model.response.ResponseGetHealthData;
import com.urdost.model.response.ResponsePanCard;
import com.urdost.model.response.ResponseSaveHealth;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;
import java.util.Calendar;

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

public class CardActivityAdd extends BaseActivity implements IPickCancel, IPickResult {

    private String pk_adarid;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    BottomSheetDialog dialogHealthCard, dialogUpdateHealthCard, dialogPanCard, dialogUpdatePanCard, dialogSaveAadharCard, dialogUpdateAdharCard;
    private String aname,afname,adob,aemail,amobile,aaddress,fname,hname,haddress;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.image_view)
    ImageView imageView;
    @BindView(R.id.health_card)
    ImageView healthCard;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.img_qr)
    ImageView imgQr;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_health_id)
    TextView tvHealthId;
    @BindView(R.id.tv_phr_address)
    TextView tvPhrAddress;
    @BindView(R.id.tv_barth)
    TextView tvBarth;
    @BindView(R.id.tv_gander)
    TextView tvGander;
    @BindView(R.id.tv_mobbile)
    TextView tvMobbile;
    @BindView(R.id.cv_health_card)
    CardView cvHealthCard;
    @BindView(R.id.btn_Edit)
    Button btnEdit;
    @BindView(R.id.img_qr_pan)
    ImageView imgQrPan;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.cv_pan_card)
    CardView cvPanCard;
    @BindView(R.id.btn_update_pan)
    Button btnUpdatePan;
    @BindView(R.id.btn_adhar)
    Button btnAdhar;
    @BindView(R.id.pan_number)
    TextView panNumber;
    @BindView(R.id.tv_pan_name)
    TextView tvPanName;
    @BindView(R.id.pan_date)
    TextView panDate;
    @BindView(R.id.border_layout)
    ConstraintLayout borderLayout;
    @BindView(R.id.aadharcard_viewpager)
    ViewPager aadharcardViewpager;
    private String gender = "Male";
    private String genderadahr = "Male";

    private String healthcardid;
    private String pancardid;
    private String genderupdatecard = "Male";
    private String cardname, carddob, cardmobile;
    private String panname, pandate;
    private String norecordfound;
    private ChoosePhoto choosePhoto=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_id);
        ButterKnife.bind(this);
        tvTitle.setText("ID Card");
        getHealthCard();
        getPanCard();
        setPagerAdapter();
        getData();
       // Toast.makeText(context, healthcardid+"", Toast.LENGTH_SHORT).show();

    }

    @OnClick({R.id.img_back, R.id.btn_Edit, R.id.btn_update_pan, R.id.img, R.id.btn_adhar})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_Edit:
                 if (healthcardid.equalsIgnoreCase("")){
                   openEditPanDilog();
               }else {
                   openDialogUpdateHealthCard();
               }
                break;
            case R.id.btn_update_pan:
                if (pancardid.equalsIgnoreCase(""))
                    OpenDialogPanCard();
                else openDialogUpdatePan();
                break;
            case R.id.img:
                showDialog();
                break;
            case R.id.btn_adhar:
               if (pk_adarid.equalsIgnoreCase(""))
                    openSaveAdharCard();
                else openUpdateAdharCard();

                break;
        }
    }

    private void openEditPanDilog() {
        dialogHealthCard = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_health_card, null);
        dialogHealthCard.setContentView(sheetView);

       /* dialogHealthCard = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_health_card, null);
        dialogHealthCard.setContentView(sheetView);*/
        EditText et_name_pan = sheetView.findViewById(R.id.et_name);
        TextView tv_date_pan = sheetView.findViewById(R.id.tv_date_pan);
        EditText et_mobile_pan = sheetView.findViewById(R.id.et_mobile_pan);
        RadioGroup rg12_gender = sheetView.findViewById(R.id.rg12_gender);
        EditText et_health_id=sheetView.findViewById(R.id.et_health_id);
        // RadioButton mail=sheetView.findViewById(R.id.male);
        //  RadioButton female=sheetView.findViewById(R.id.female);

        Button btn_update = sheetView.findViewById(R.id.btn_update);
        Button btn_cancel_pan = sheetView.findViewById(R.id.btn_cancel_pan);

        rg12_gender.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rg12_gender)
                gender = "Male";
            else gender = "Female";
        });

        tv_date_pan.setOnClickListener(v -> {
            datePicker(tv_date_pan);
            // tv_end_date.setText("");
        });


        btn_cancel_pan.setOnClickListener(v -> dialogHealthCard.dismiss());

        btn_update.setOnClickListener(v -> {
            dialogHealthCard.dismiss();
            if (et_health_id.getText().toString().length()==10) {
                SaveHealthData(et_name_pan.getText().toString().trim(),
                        tv_date_pan.getText().toString().trim(), et_mobile_pan.getText().toString().trim(), gender, et_health_id.getText().toString().trim());
            }else {
                showMessage("Enter Health ID Card");
            }
        });


        dialogHealthCard.setCancelable(false);
        dialogHealthCard.setCanceledOnTouchOutside(false);
        dialogHealthCard.show();
    }

    private void openDialogUpdateHealthCard() {
        dialogUpdateHealthCard = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_update_health_card, null);
        dialogUpdateHealthCard.setContentView(sheetView);
       /* dialogHealthCard = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_health_card, null);
        dialogHealthCard.setContentView(sheetView);*/
        EditText up_name_card = sheetView.findViewById(R.id.up_name);
        TextView up_date = sheetView.findViewById(R.id.up_date_card);
        EditText up_mobile = sheetView.findViewById(R.id.up_mobile_card);
        RadioGroup up_gender = sheetView.findViewById(R.id.up_gender);
        EditText up_health_id=sheetView.findViewById(R.id.up_health_id);
        // RadioButton mail=sheetView.findViewById(R.id.male);
        //  RadioButton female=sheetView.findViewById(R.id.female);

        Button btn_update = sheetView.findViewById(R.id.btn_update);
        Button btn_cancel = sheetView.findViewById(R.id.btn_cancel);
        up_name_card.setText(cardname);
        up_mobile.setText(cardmobile);
        up_date.setText(carddob);

        up_gender.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.up_gender)
                genderupdatecard = "Male";
            else genderupdatecard = "Female";
        });

        up_date.setOnClickListener(v -> {
            datePicker(up_date);
            // tv_end_date.setText("");

        });


        btn_cancel.setOnClickListener(v -> dialogUpdateHealthCard.dismiss());

        btn_update.setOnClickListener(v -> {
            dialogUpdateHealthCard.dismiss();
            UpdateHealthData(up_name_card.getText().toString().trim(),
                    up_date.getText().toString().trim(), up_mobile.getText().toString().trim(), genderupdatecard);
        });


        dialogUpdateHealthCard.setCancelable(false);
        dialogUpdateHealthCard.setCanceledOnTouchOutside(false);
        dialogUpdateHealthCard.show();
    }


    public void SaveHealthData(String name, String dob, String mobile, String gender1,String healthcardid) {
        showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Name", name);
        object.addProperty("DOB", dob);

        object.addProperty("Mobile", mobile);
        object.addProperty("Gender", gender1);
        object.addProperty("CardNo",healthcardid);
        Call<ResponseSaveHealth> call = apiServices.SaveHealthCardDetails(object);
        call.enqueue(new Callback<ResponseSaveHealth>() {
            @Override
            public void onResponse(Call<ResponseSaveHealth> call, Response<ResponseSaveHealth> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getHealthCard();


                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseSaveHealth> call, Throwable t) {
                hideLoading();
            }
        });

    }

    public void UpdateHealthData(String name, String dob, String mobile, String gender1) {
        showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_CardId", healthcardid);
        object.addProperty("Name", name);
        object.addProperty("DOB", dob);

        object.addProperty("Mobile", mobile);
        object.addProperty("Gender", gender1);
        Call<ResponseStatusMessage> call = apiServices.updateHealthCard(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getHealthCard();


                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });

    }

    private void datePicker(final TextView et) {
        Calendar cal = Calendar.getInstance();
        int mYear, mMonth, mDay;

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, monthOfYear, dayOfMonth) -> {
            et.setText(Utils.changeDateFormat(dayOfMonth, monthOfYear, year));
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }

    @Override
    public void onCancelClick() {

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

    File homeWorkFile, compressedImageFile, homeWorkFile1, compressedImageFile1;

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
                uploadHealthCardImg();
                //uploadProfilePic();
                //uploadeDocument();
                /// Toast                               .makeText(context, result.getUri()+"", Toast.LENGTH_SHORT).show();
                Log.e("ADDRESS File ", homeWorkFile.toString());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
            }
        }
           else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result1 = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {

                    homeWorkFile = FileUtils.getFile(context, result1.getUri());
                    compressedImageFile = new Compressor.Builder(this)
                            .setMaxWidth(800)
                            .setMaxHeight(640)
                            .setQuality(100)
                            .setCompressFormat(Bitmap.CompressFormat.WEBP)
                            .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_PICTURES).getAbsolutePath())
                            .build()
                            .compressToFile(homeWorkFile);
                    uplodeaadharimage();
                    Log.e("ADDRESS File ", homeWorkFile.toString());
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                        Exception error = result.getError();
                }
            }


        }


    //Get Health Card Data
    private void getHealthCard() {
        showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        Call<ResponseGetHealthData> call = apiServices.getHealthCard(object);
        call.enqueue(new Callback<ResponseGetHealthData>() {
            @Override
            public void onResponse(Call<ResponseGetHealthData> call, Response<ResponseGetHealthData> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    tvName.setText(response.body().getName());
                    tvHealthId.setText("Health ID Number :" + " " + response.body().getHealthCardNo());
                    tvGander.setText("Gender " + " " + response.body().getGender());
                    tvMobbile.setText("Mobile " + " " + response.body().getMobile());
                    tvBarth.setText("Year Of Birth:" + " " + response.body().getDob());
                    Glide.with(context).load(response.body().getPhoto()).into(img);
                    Glide.with(context).load(response.body().getQrImage()).into(imgQr);
                    healthcardid = response.body().getPKCardId();

                   // Toast.makeText(CardActivityAdd.this, healthcardid+"", Toast.LENGTH_SHORT).show();
                    cardname = response.body().getName();
                    cardmobile = response.body().getMobile();
                    carddob = response.body().getDob();

                    //  tvBarth.setText(response.body().getD);

                }
                else healthcardid=response.body().getPKCardId();
            }

            @Override
            public void onFailure(Call<ResponseGetHealthData> call, Throwable t) {
                hideLoading();
            }
        });

    }


    private void uploadHealthCardImg() {
        LoggerUtil.logItem(compressedImageFile.length());
        LoggerUtil.logItem(homeWorkFile.length());
        showLoading();
        RequestBody requestBody;
        MultipartBody.Part body = null;
        requestBody = RequestBody.create(MediaType.parse("image/*"), homeWorkFile);
        body = MultipartBody.Part.createFormData("Photo", homeWorkFile.getName(), requestBody);
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), PreferencesManager.getInstance(context).getPk_userId());
        RequestBody pk_cardId = RequestBody.create(MediaType.parse("text/plain"), healthcardid);

        //RequestBody adharnumber = RequestBody.create(MediaType.parse("text/plain"), tvAdharNumber.getText().toString().trim());
        Call<ResponseStatusMessage> call = apiServices.uploadHealthImage(userId, pk_cardId, body);
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
                        getHealthCard();

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
//Start Pan Card

    private void OpenDialogPanCard() {
        dialogPanCard = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_pan_card, null);
        dialogPanCard.setContentView(sheetView);
       /* dialogHealthCard = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_health_card, null);
        dialogHealthCard.setContentView(sheetView);*/
        EditText et_pan_name = sheetView.findViewById(R.id.et_name_pan);
        TextView tv_start_date = sheetView.findViewById(R.id.tv_start_date);
        // tv1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/mangal.ttf"));
        EditText et_pan_number=sheetView.findViewById(R.id.et_pan_number);

        Button btn_submit_pan = sheetView.findViewById(R.id.btn_submit_pan);
        Button btn_cancel_pan = sheetView.findViewById(R.id.btn_cancel_pan);

        tv_start_date.setOnClickListener(v -> {
            datePicker(tv_start_date);
            // tv_end_date.setText("");

        });


        btn_cancel_pan.setOnClickListener(v -> dialogPanCard.dismiss());

        btn_submit_pan.setOnClickListener(v -> {
            dialogPanCard.dismiss();
            if (et_pan_number.getText().toString().length() == 10)
            {
                SavePanCardData(et_pan_name.getText().toString().trim(),
                        tv_start_date.getText().toString().trim(), et_pan_number.getText().toString().trim());
        }else {
                showMessage("Enter Pan Number");
            }
        });


        dialogPanCard.setCancelable(false);
        dialogPanCard.setCanceledOnTouchOutside(false);
        dialogPanCard.show();
    }

    private void openDialogUpdatePan() {
        dialogUpdatePanCard = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_update_pan, null);
        dialogUpdatePanCard.setContentView(sheetView);
       /* dialogHealthCard = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_health_card, null);
        dialogHealthCard.setContentView(sheetView);*/
        EditText up_pan_name = sheetView.findViewById(R.id.up_pan_name);
        TextView up_start_date = sheetView.findViewById(R.id.up_start_date);

        Button btn_update_pan = sheetView.findViewById(R.id.btn_update_pan);
        Button up_cancel_pan = sheetView.findViewById(R.id.up_cancel_pan);
        up_pan_name.setText(panname);
        up_start_date.setText(pandate);

        up_start_date.setOnClickListener(v -> {
            datePicker(up_start_date);
            // tv_end_date.setText("");

        });


        up_cancel_pan.setOnClickListener(v -> dialogUpdatePanCard.dismiss());

        btn_update_pan.setOnClickListener(v -> {
            dialogUpdatePanCard.dismiss();
            UpdatePanCard(up_pan_name.getText().toString().trim(),
                    up_start_date.getText().toString().trim());
        });


        dialogUpdatePanCard.setCancelable(false);
        dialogUpdatePanCard.setCanceledOnTouchOutside(false);
        dialogUpdatePanCard.show();
    }

    private void SavePanCardData(String name, String date,String pan_number) {
        showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Name", name);
        object.addProperty("DOB", date);
        object.addProperty("PanNo",pan_number);
        object.addProperty("Mobile", "");
        object.addProperty("Gender", "");
        object.addProperty("NameH", "");
        object.addProperty("FatherName", "");
        object.addProperty("FatherNameH", "");
        object.addProperty("Email", "");
        object.addProperty("Address", "");
        object.addProperty("AddressH", "");
        Call<ResponseStatusMessage> call = apiServices.SavePanCard(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getPanCard();


                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });

    }

    private void UpdatePanCard(String name, String date) {
        showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_CardId", pancardid);

        object.addProperty("Name", name);
        object.addProperty("DOB", date);

        object.addProperty("Mobile", "");
        object.addProperty("Gender", "");
        object.addProperty("NameH", "");
        object.addProperty("FatherName", "");
        object.addProperty("FatherNameH", "");
        object.addProperty("Email", "");
        object.addProperty("Address", "");
        object.addProperty("AddressH", "");
        Call<ResponseStatusMessage> call = apiServices.updatePanCard(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getPanCard();

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });

    }

    private void getPanCard() {
        // showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        Call<ReponsePanCard> call = apiServices.getPancardData(object);
        call.enqueue(new Callback<ReponsePanCard>() {
            @Override
            public void onResponse(Call<ReponsePanCard> call, Response<ReponsePanCard> response) {
                //  hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    tvPanName.setText(response.body().getName());
                    panNumber.setText(response.body().getPanNo());
                    panDate.setText(response.body().getDob());
                    pancardid = response.body().getPKCardId();
                    panname = response.body().getName();
                    pandate = response.body().getDob();
                    showMessage(response.body().getMessage());
                    Toast.makeText(CardActivityAdd.this, pancardid+"", Toast.LENGTH_SHORT).show();

                    //  tvBarth.setText(response.body().getD);

                }else
                {
                    pancardid = response.body().getPKCardId();

                }
            }

            @Override
            public void onFailure(Call<ReponsePanCard> call, Throwable t) {
                // hideLoading();
            }
        });

    }

    private void setPagerAdapter() {
        AadharcardAdapter aadharcardAdapter = new AadharcardAdapter(getSupportFragmentManager());
        aadharcardViewpager.setAdapter(aadharcardAdapter);
    }

    private void openSaveAdharCard() {
        dialogSaveAadharCard = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_adddhar_save, null);
        dialogSaveAadharCard.setContentView(sheetView);

       /* dialogHealthCard = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_health_card, null);
        dialogHealthCard.setContentView(sheetView);*/
        EditText et_name_aadhar = sheetView.findViewById(R.id.et_name_aadhar);
        EditText et_father_name = sheetView.findViewById(R.id.etFathernameAdhar);
        TextView tv_adhar_dob = sheetView.findViewById(R.id.tv_adhar_dob);
        EditText et_adhar_mobile = sheetView.findViewById(R.id.et_adhar_mobile);
        EditText etAddress_Adhar = sheetView.findViewById(R.id.etAddress_Adhar);
        EditText etEmail_adhar = sheetView.findViewById(R.id.etEmail_adhar);
        RadioGroup rg_gender_adhar = sheetView.findViewById(R.id.rg_gender_adhar);
        EditText etDisticAdhar = sheetView.findViewById(R.id.etDisticAdhar);
        EditText etPinCode=sheetView.findViewById(R.id.etPinCode);
        EditText et_haddressname=sheetView.findViewById(R.id.et_haddressname);
        EditText et_hname=sheetView.findViewById(R.id.et_hname);
        EditText et_hFname=sheetView.findViewById(R.id.et_hFname);
        EditText et_adhar_number=sheetView.findViewById(R.id.et_adhar_number);
        // RadioButton mail=sheetView.findViewById(R.id.male);
        //  RadioButton female=sheetView.findViewById(R.id.female);


        Button btn_addar_cancle = sheetView.findViewById(R.id.btn_addar_cancle);
        Button btn_saveAddhar = sheetView.findViewById(R.id.btn_saveAddhar);

        rg_gender_adhar.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rg_gender_adhar)
                genderadahr = "Male";
            else genderadahr = "Female";
        });

        tv_adhar_dob.setOnClickListener(v -> {
            datePicker(tv_adhar_dob);
            // tv_end_date.setText("");
        });


        btn_addar_cancle.setOnClickListener(v -> dialogSaveAadharCard.dismiss());

        btn_saveAddhar.setOnClickListener(v -> {
            dialogSaveAadharCard.dismiss();
            if (et_adhar_number.getText().toString().length()==12) {
                SaveAadahrData(et_name_aadhar.getText().toString().trim(),
                        et_father_name.getText().toString().trim(), et_adhar_mobile.getText().toString().trim(), tv_adhar_dob.getText().toString().trim(),
                        etEmail_adhar.getText().toString().trim(), etAddress_Adhar.getText().toString().trim(), etDisticAdhar.getText().toString().trim(), gender, etPinCode.getText().toString().trim(), et_hname.getText().toString().trim(), et_hFname.getText().toString().trim(), et_haddressname.getText().toString().trim(),et_adhar_number.getText().toString().trim());
            }else {
                showMessage("Enter Aadhar Number");
            }
        });


        dialogSaveAadharCard.setCancelable(false);
        dialogSaveAadharCard.setCanceledOnTouchOutside(false);
        dialogSaveAadharCard.show();
    }

    private void SaveAadahrData(String Aname, String AFname, String Amobile, String Adob, String Aemail, String AAdress, String ADistic, String Agender,String pincode,String hname,String hFname,String haddress,String AdharNumber) {
        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("FatherName", AFname);
        object.addProperty("Mobile", Amobile);
        object.addProperty("DOB", Adob);
        object.addProperty("Gender", Agender);
        object.addProperty("Email", Aemail);
        object.addProperty("Address", AAdress);
        object.addProperty("Name", Aname);
        object.addProperty("PinCode",pincode);
        object.addProperty("NameH",hname);
        object.addProperty("FatherNameH",hFname);
        object.addProperty("AddressH",haddress);
         object.addProperty("AdharNo",AdharNumber);

        Call<ResponseStatusMessage> call = apiServices.SaveAdharCardDetails(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                //  hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.message());
                    getData();

                } else {
                    showMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                // hideLoading();
            }
        });

    }

    private void openUpdateAdharCard() {
        dialogUpdateAdharCard = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.adharcard_update_dialog, null);
        dialogUpdateAdharCard.setContentView(sheetView);

       /* dialogHealthCard = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_health_card, null);
        dialogHealthCard.setContentView(sheetView);*/
        EditText up_adhar_no=sheetView.findViewById(R.id.up_adhar_no);
        ImageView img_member=sheetView.findViewById(R.id.img_member);
        EditText up_name_aadhar = sheetView.findViewById(R.id.up_name_aadhar);
        EditText up_father_name = sheetView.findViewById(R.id.upFathernameAdhar);
        TextView up_adhar_dob = sheetView.findViewById(R.id.up_adhar_dob);
        EditText up_adhar_mobile = sheetView.findViewById(R.id.up_adhar_mobile);
        EditText upAddress_Adhar = sheetView.findViewById(R.id.upAddress_Adhar);
        EditText upEmail_adhar = sheetView.findViewById(R.id.upEmail_adhar);
        EditText upDisticAdhar = sheetView.findViewById(R.id.upDisticAdhar);
        EditText upPinCode=sheetView.findViewById(R.id.PinCode);
        EditText up_haddressname=sheetView.findViewById(R.id.up_haddressname);
        EditText up_hname=sheetView.findViewById(R.id.up_hname);
        EditText up_hFname=sheetView.findViewById(R.id.up_hFname);
        RadioGroup rg_gender_adhar_update=sheetView.findViewById(R.id.rg_gender_adhar_update);
        up_father_name.setText(afname);
        up_adhar_mobile.setText(amobile);
        up_name_aadhar.setText(aname);
        up_adhar_dob.setText(adob);
        upAddress_Adhar.setText(aaddress);
        upEmail_adhar.setText(aemail);
        up_hFname.setText(fname);
        up_hname.setText(hname);
        up_haddressname.setText(haddress);
       // up_adhar_no.setText();
        Button btn_addar_cancle_update = sheetView.findViewById(R.id.btn_addar_cancle_update);
        Button btn_updateAddhar = sheetView.findViewById(R.id.btn_updateAddhar);
        rg_gender_adhar_update.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rg_gender_adhar_update)
                genderadahr = "Male";
            else genderadahr = "Female";
        });


        img_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //choosePhoto = new ChoosePhoto(context);
               showDialog();
            }
        });
        up_adhar_dob.setOnClickListener(v -> {
            datePicker(up_adhar_dob);
            // tv_end_date.setText("");
        });


        btn_addar_cancle_update.setOnClickListener(v -> dialogUpdateAdharCard.dismiss());

        btn_updateAddhar.setOnClickListener(v -> {
            dialogUpdateAdharCard.dismiss();
            UpdateAadahrData(up_name_aadhar.getText().toString().trim(),
                    up_father_name.getText().toString().trim(), up_adhar_mobile.getText().toString().trim(), up_adhar_dob.getText().toString().trim(),
                    upEmail_adhar.getText().toString().trim(), upAddress_Adhar.getText().toString().trim(), upPinCode.getText().toString().trim(),up_adhar_no.getText().toString().trim(),up_hname.getText().toString().trim(),up_hFname.getText().toString().trim(),up_haddressname.getText().toString().trim(),genderadahr);
        });


        dialogUpdateAdharCard.setCancelable(false);
        dialogUpdateAdharCard.setCanceledOnTouchOutside(false);
        dialogUpdateAdharCard.show();
    }

    private void UpdateAadahrData(String Aname, String AFname, String Amobile, String Adob, String Aemail, String AAdress, String ADistic,String adharnumber,String hname,String Fname,String
                                  haddress,String genderadahr) {
        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("FatherName", AFname);
        object.addProperty("Mobile", Amobile);
        object.addProperty("DOB", Adob);
        object.addProperty("Email", Aemail);
        object.addProperty("Address", AAdress);
        object.addProperty("Name", Aname);
        object.addProperty("PK_CardId", pk_adarid);
        object.addProperty("AdharNo", adharnumber);
        object.addProperty("NameH",hname);
        object.addProperty("FatherNameH",Fname);
        object.addProperty("AddressH",haddress);
        object.addProperty("Gender",genderadahr);
        // object.addProperty("Name",Aname);

        Call<ResponseStatusMessage> call = apiServices.UpdateAdharCardDetails(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                //  hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.message());
                    getData();

                } else {
                    showMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                // hideLoading();
            }
        });
    }

    private void getData() {
        // showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        Call<ResponseAadharData> call = apiServices.getAadharData(object);
        call.enqueue(new Callback<ResponseAadharData>() {
            @Override
            public void onResponse(Call<ResponseAadharData> call, Response<ResponseAadharData> response) {
                //  hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {

                    pk_adarid = response.body().getPKCardId();
                    aname=response.body().getName();
                    afname=response.body().getFatherName();
                    aaddress=response.body().getAddress();
                    adob=response.body().getDob();
                    aemail=response.body().getEmail();
                    haddress=response.body().getAddressH();
                    hname=response.body().getNameH();
                    fname=response.body().getFatherNameH();

                }else
                    pk_adarid = response.body().getPKCardId();



            }

            @Override
            public void onFailure(Call<ResponseAadharData> call, Throwable t) {
                // hideLoading();
            }
        });

    }
    private void uplodeaadharimage() {
        LoggerUtil.logItem(compressedImageFile.length());
        LoggerUtil.logItem(homeWorkFile.length());
       // showLoading();
        RequestBody requestBody;
        MultipartBody.Part body = null;
        requestBody = RequestBody.create(MediaType.parse("image/*"), homeWorkFile);
        body = MultipartBody.Part.createFormData("Photo", homeWorkFile.getName(), requestBody);
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), PreferencesManager.getInstance(context).getPk_userId());
        RequestBody pk_cardId = RequestBody.create(MediaType.parse("text/plain"), pk_adarid);

        //RequestBody adharnumber = RequestBody.create(MediaType.parse("text/plain"), tvAdharNumber.getText().toString().trim());
        Call<ResponseStatusMessage> call = apiServices.uploadHealthImage(userId, pk_cardId, body);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
               // hideLoading();
                LoggerUtil.logItem(response.body());
                try {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        // hideLoading();
                        showMessage(response.body().getMessage());
                        LoggerUtil.logItem(response.body());
                       // getHealthCard();
                        getData();

                    } else showMessage(response.body().getMessage());


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                LoggerUtil.logItem(t.getMessage());
               // hideLoading();
            }
        });
    }
private void showcamera()
{
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

}
