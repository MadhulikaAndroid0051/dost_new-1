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
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.FileUtils;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseActivity;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.response.ResponseGetKycData;
import com.urdost.model.response.kycStatus.ResponseKycStatus;
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

public class UploadeKyc extends BaseActivity {//implements IPickCancel, IPickResult {
   /* @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.tv_adhar_number)
    EditText tvAdharNumber;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.btn_adhar)
    Button btnAdhar;
    @BindView(R.id.img_adhar)
    ImageView imgAdhar;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.et_pan_number)
    EditText etPanNumber;
    @BindView(R.id.tv_status_pan)
    TextView tvStatusPan;
    @BindView(R.id.btn_pan)
    Button btnPan;
    @BindView(R.id.img_pan)
    ImageView imgPan;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.et_document)
    EditText etDocument;
    @BindView(R.id.tv_document_status)
    TextView tvDocumentStatus;
    @BindView(R.id.btn_document)
    Button btnDocument;
    @BindView(R.id.img_document)
    ImageView imgDocument;
    @BindView(R.id.container)
    ConstraintLayout container;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_nfc_profile);
        ButterKnife.bind(this);
       // tvTitle.setText("KYC");
      //  getKycData();
    }


  /*  PickImageDialog dialog;

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
               // Toast.makeText(context, result.getUri()+"", Toast.LENGTH_SHORT).show();
                Log.e("ADDRESS File ", homeWorkFile.toString());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
               // Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
//                        Exception error = result.getError();
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
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
                uploadPanCard();
                Log.e("ADDRESS File ", homeWorkFile.toString());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                        Exception error = result.getError();
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
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
                uploadeDocument();
                Log.e("ADDRESS File ", homeWorkFile.toString());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//                        Exception error = result.getError();
            }
        }
    }
    private void uploadProfilePic () {
        LoggerUtil.logItem(homeWorkFile.length());
        showLoading();
        RequestBody requestBody;
        MultipartBody.Part body = null;
        requestBody = RequestBody.create(MediaType.parse("image/*"), homeWorkFile);
        body = MultipartBody.Part.createFormData("AadharImage", homeWorkFile.getName(), requestBody);
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), PreferencesManager.getInstance(context).getPk_userId());
        RequestBody adharnumber = RequestBody.create(MediaType.parse("text/plain"), tvAdharNumber.getText().toString().trim());
        Call<ResponseStatusMessage> call = apiServices.uploadAdhar(userId, adharnumber, body);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                try {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                       // hideLoading();
                        showMessage(response.body().getMessage());
                        goToActivityWithFinish(context, UploadeKyc.class, null);
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
    private void uploadPanCard () {
        LoggerUtil.logItem(homeWorkFile.length());
        showLoading();
        RequestBody requestBody;
        MultipartBody.Part body = null;
        requestBody = RequestBody.create(MediaType.parse("image/*"), homeWorkFile);
        body = MultipartBody.Part.createFormData("PanImage", homeWorkFile.getName(), requestBody);
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), PreferencesManager.getInstance(context).getPk_userId());
        RequestBody panNumber = RequestBody.create(MediaType.parse("text/plain"), etPanNumber.getText().toString().trim());
        Call<ResponseStatusMessage> call = apiServices.uploadPan(userId, panNumber, body);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                try {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        hideLoading();
                        showMessage(response.body().getMessage());
                        //goToActivityWithFinish(context, UploadeKyc.class, null);
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
    private void uploadeDocument () {
        LoggerUtil.logItem(homeWorkFile.length());
        showLoading();
        RequestBody requestBody;
        MultipartBody.Part body = null;
        requestBody = RequestBody.create(MediaType.parse("image/*"), homeWorkFile);
        body = MultipartBody.Part.createFormData("DocumentImage", homeWorkFile.getName(), requestBody);
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), PreferencesManager.getInstance(context).getPk_userId());
        RequestBody panNumber = RequestBody.create(MediaType.parse("text/plain"), etDocument.getText().toString().trim());
        Call<ResponseStatusMessage> call = apiServices.uoloaddocument(userId, panNumber, body);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                try {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        hideLoading();
                        showMessage(response.body().getMessage());
                       // goToActivityWithFinish(context, UploadeKyc.class, null);
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
    private void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("UserID", PreferencesManager.getInstance(context).getPk_userId());
        LoggerUtil.logItem(object);

        Call<ResponseGetKycData> call = apiServices.getKYCData(object);
        call.enqueue(new Callback<ResponseGetKycData>() {
            @Override
            public void onResponse(Call<ResponseGetKycData> call, Response<ResponseGetKycData> response) {
                hideLoading();


                try {
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatus().equalsIgnoreCase("0")) {
                        //  Lstassociate lstassociat =new response.body().getLstassociate();
                      //  Lstassociate lstassociate = new Lstassociate();
                        tvStatus.setText(response.body().getAdharStatus());
                        tvDocumentStatus.setText(response.body().getDocumentStatus());
                        tvStatusPan.setText(response.body().getPanStatus());

                        Glide.with(context).load("https://dost.click/"+response.body().getAdharImage()).into(imgAdhar);
                        Glide.with(context).load("https://dost.click/"+response.body().getPanImage()).into(imgPan);
                        Glide.with(context).load("https://dost.click/"+response.body().getDocumentImage()).into(imgDocument);

                        // Picasso.with(context).load("http://demo1.afluex.com/"+response.body().getProfilePic()).into(imgMember);
                    }
                } catch (Exception e) {
                    showMessage("Server Issue");
                }
            }

            @Override
            public void onFailure(Call<ResponseGetKycData> call, Throwable t) {
                hideLoading();
            }
        });

    }

    @OnClick({R.id.btn_adhar, R.id.btn_pan, R.id.btn_document,R.id.img_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_adhar:
                if (tvAdharNumber.getText().toString().length()==12)
                    showDialog();
                else showMessage("Enter Valid Aadhar Number");
                break;
            case R.id.btn_pan:
                if (etPanNumber.getText().toString().length()==10)
                    showDialog();
                else showMessage("Enter Valid PanCard Number");
                break;
            case R.id.btn_document:
                if (etDocument.getText().toString().length()>=2)
                    showDialog();
                else showMessage("Enter Valid Document Number");
                break;
            case R.id.img_back:
                 onBackPressed();
                break;

        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        
    }
  public void   getKycData()
    {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PageNo","1");
        LoggerUtil.logItem(object);

        Call<ResponseKycStatus> call = apiServices.getKycStatus(object);
        call.enqueue(new Callback<ResponseKycStatus>() {
            @Override
            public void onResponse(Call<ResponseKycStatus> call, Response<ResponseKycStatus> response) {
                hideLoading();
                try {
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                       tvStatus.setText(response.body().getGetKycData().getAdharStatus());
                       tvStatusPan.setText(response.body().getGetKycData().getPanStatus());
                       tvDocumentStatus.setText(response.body().getGetKycData().getDocumentStatus());
                        Glide.with(context).load("https://dost.click"+response.body().getGetKycData().getAdharImage()).into(imgAdhar);
                        Glide.with(context).load("https://dost.click"+response.body().getGetKycData().getPanImage()).into(imgPan);
                        Glide.with(context).load("https://dost.click"+response.body().getGetKycData().getDocumentImage()).into(imgDocument);
                        etDocument.setText(response.body().getGetKycData().getDocumentNumber());
                        tvAdharNumber.setText(response.body().getGetKycData().getAdharNumber());
                        etPanNumber.setText(response.body().getGetKycData().getPanNumber());
                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseKycStatus> call, Throwable t) {
                hideLoading();
            }
        });
    }*/

}
