package com.urdost.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.FileUtils;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseFragment;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.response.ResponseAadharData;
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
import butterknife.Unbinder;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class AadharCardFront extends BaseFragment implements IPickCancel, IPickResult {

    Unbinder unbinder;
    @BindView(R.id.border_layout1)
    ImageView borderLayout1;
    @BindView(R.id.img_adhar_profile)
    ImageView imgAdharProfile;
    @BindView(R.id.imge_adhar)
    ImageView imgeAdhar;
    @BindView(R.id.tv_name_adahr)
    TextView tvNameAdahr;
    @BindView(R.id.tv_adhar_father)
    TextView tvAdharFather;
    @BindView(R.id.tv_address_adhar)
    TextView tvAddressAdhar;
    @BindView(R.id.tv_barth_adhar)
    TextView tvBarthAdhar;
    @BindView(R.id.tv_adhar_number)
    TextView tvAdharNumber;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.cv_adhar_card)
    CardView cvAdharCard;
    private String aadahrpk_id;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aadhar_front, container, false);
        unbinder = ButterKnife.bind(this, view);
        new Handler().postDelayed(() -> {
            getData();

        }, 100);
        return view;
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
        dialog.show(getParentFragmentManager());

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
                compressedImageFile = new Compressor.Builder(context)
                        .setMaxWidth(800)
                        .setMaxHeight(640)
                        .setQuality(100)
                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES).getAbsolutePath())
                        .build()
                        .compressToFile(homeWorkFile);
                uplodeimage();                //uploadProfilePic();
                //uploadeDocument();
                /// Toast                               .makeText(context, result.getUri()+"", Toast.LENGTH_SHORT).show();
                Log.e("ADDRESS File ", homeWorkFile.toString());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
            }

        }
    }


    @OnClick(R.id.img_adhar_profile)
    public void onClick() {
        showDialog();
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
                    tvNameAdahr.setText(response.body().getNameH());
                    tvAdharFather.setText(response.body().getName());
                    tvAddressAdhar.setText("जन्म तिथि/DOB:"+" "+response.body().getDob());
                    tvBarthAdhar.setText(response.body().getGenderH()+"/ "+response.body().getGender());
                    tvAdharNumber.setText(response.body().getAdharNo());
                    aadahrpk_id=response.body().getPKCardId();
                    Glide.with(context).load(response.body().getPhoto()).into(imgAdharProfile);
                    Glide.with(context).load(response.body().getQrImage()).into(imgeAdhar);
                 //   Toast.makeText(context, aadahrpk_id+"", Toast.LENGTH_SHORT).show();

                    //  tvBarth.setText(response.body().getD);

                }
            }

            @Override
            public void onFailure(Call<ResponseAadharData> call, Throwable t) {
                // hideLoading();
            }
        });

    }
    private void uplodeimage() {
        LoggerUtil.logItem(compressedImageFile.length());
        LoggerUtil.logItem(homeWorkFile.length());
        showLoading();
        RequestBody requestBody;
        MultipartBody.Part body = null;
        requestBody = RequestBody.create(MediaType.parse("image/*"), homeWorkFile);
        body = MultipartBody.Part.createFormData("Photo", homeWorkFile.getName(), requestBody);
        RequestBody userId = RequestBody.create(MediaType.parse("text/plain"), PreferencesManager.getInstance(context).getPk_userId());
        RequestBody pk_cardId = RequestBody.create(MediaType.parse("text/plain"), aadahrpk_id);

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
                        // getHealthCard();

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

}
