package com.urdost.Fragment.NfcSetActionSection;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.urdost.Activity.EventDetails;
import com.urdost.Activity.NewContainerActivity;
import com.urdost.Adapter.AdapterBusinessProfileLst;
import com.urdost.Fragment.Scanner;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.FileUtils;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseFragment;
import com.urdost.interfaces.BusinessProfileId;
import com.urdost.interfaces.Chackboxbusinessprofile;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.response.BusinessProfile.ResponseBusinessProfile;
import com.urdost.model.response.CreateProfileResponse;
import com.urdost.model.response.GetNfcActicateStatus;
import com.urdost.model.response.ResponseCreateProfile;
import com.urdost.model.response.ResponseGetEditBusinessProfile;
import com.urdost.model.response.ResponseSaveHealth;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickCancel;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class SetActionNew extends BaseFragment implements BusinessProfileId, Chackboxbusinessprofile , IPickCancel, IPickResult {

    Unbinder unbinder;
    @BindView(R.id.simpleSwitch)
    Switch simpleSwitch;
    @BindView(R.id.carviewScanner)
    CardView carviewScanner;
    @BindView(R.id.textview_action)
    TextView textviewAction;
    @BindView(R.id.card_view1)
    CardView cardView1;
    @BindView(R.id.linear_layout1)
    LinearLayout linearLayout1;
    @BindView(R.id.img5)
    ImageView img5;
    @BindView(R.id.cv_exchange_analytic)
    CardView cvExchangeAnalytic;
    @BindView(R.id.img8)
    ImageView img8;
    @BindView(R.id.cv_my_profile)
    CardView cvMyProfile;
    @BindView(R.id.inf_contact_no)
    TextView infContactNo;
    @BindView(R.id.up_info_contact_number)
    EditText upInfoContactNumber;
    @BindView(R.id.btnupdate_info_contact)
    Button btnupdateInfoContact;
    @BindView(R.id.cv_info_contact_update)
    CardView cvInfoContactUpdate;
    @BindView(R.id.inf_email)
    TextView infEmail;
    @BindView(R.id.up_info_email)
    EditText upInfoEmail;
    @BindView(R.id.btnupadte_info_Email)
    Button btnupadteInfoEmail;
    @BindView(R.id.cv_info_email_update)
    CardView cvInfoEmailUpdate;
    @BindView(R.id.inf_web)
    TextView infWeb;
    @BindView(R.id.up_info_web_url)
    EditText upInfoWebUrl;
    @BindView(R.id.btnupadte_info_Web)
    Button btnupadteInfoWeb;
    @BindView(R.id.cv_info_web_update)
    CardView cvInfoWebUpdate;
    @BindView(R.id.inf_social_profile)
    TextView infSocialProfile;
    @BindView(R.id.up_info_social_url)
    EditText upInfoSocialUrl;
    @BindView(R.id.btnupadte_info_social)
    Button btnupadteInfoSocial;
    @BindView(R.id.cv_info_social_profile)
    CardView cvInfoSocialProfile;
    @BindView(R.id.my_info_updates_layouts)
    ConstraintLayout myInfoUpdatesLayouts;
    @BindView(R.id.img6)
    ImageView img6;
    @BindView(R.id.cv_myprofile_profile)
    CardView cvMyprofileProfile;
    @BindView(R.id.img7)
    ImageView img7;
    @BindView(R.id.cv_myprofile_createnew)
    CardView cvMyprofileCreatenew;
    @BindView(R.id.tv_profile_name)
    TextView tvProfileName;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.contaner_myprofile_profiles)
    ConstraintLayout contanerMyprofileProfiles;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.et_myprofile_profile_name)
    EditText etMyprofileProfileName;
    @BindView(R.id.textview1)
    TextView textview1;
    @BindView(R.id.contaner_profile_create)
    ConstraintLayout contanerProfileCreate;
    @BindView(R.id.container)
    ConstraintLayout container;
    @BindView(R.id.setconatner_layouts)
    ConstraintLayout setconatnerLayouts;
    @BindView(R.id.my_info_myprofiles)
    ConstraintLayout myInfoMyprofiles;
    @BindView(R.id.create_contact_card_ck)
    RadioButton createContactCardCk;
    @BindView(R.id.profiles_rediraction_ck)
    RadioButton profilesRediractionCk;
    @BindView(R.id.rg_profile_type)
    RadioGroup rgProfileType;
    String profiletype = "Contact Card";
    @BindView(R.id.btn_create_profile)
    Button btnCreateProfile;
    private LinearLayoutManager businessprofilelyout;
    BottomSheetDialog editProfileDilog;
    private String businessProfileId;
    private String first_name, last_name,email,url,socialMedia,birthday,notes;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_action, container, false);
        unbinder = ButterKnife.bind(this, view);
        getStatusNfc();


        businessprofilelyout = new LinearLayoutManager(context);
        rvList.setLayoutManager(businessprofilelyout);
        rvList.setHasFixedSize(true);

        getBusinessListData();

        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewProfile();
            }
        });

        cvMyprofileProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (contanerMyprofileProfiles.getVisibility()==View.GONE)
               {
                   contanerMyprofileProfiles.setVisibility(View.VISIBLE);
                   contanerProfileCreate.setVisibility(View.GONE);

                   getBusinessListData();

               }else
               {
                   contanerMyprofileProfiles.setVisibility(View.GONE);
               }
            }
        });
        rgProfileType.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rg_profile_type)
                profiletype = "Contact Card";
            else profiletype = "Redirection";
        });

        carviewScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // getAnalytics();
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, 100);
                else
                    goToActivity(Scanner.class, null);
                getStatusNfc();
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setconatnerLayouts.getVisibility() == View.GONE) {
                    setconatnerLayouts.setVisibility(View.VISIBLE);
                } else {
                    setconatnerLayouts.setVisibility(View.GONE);
                }
            }
        });

        cvMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myInfoUpdatesLayouts.getVisibility() == View.GONE) {
                    myInfoUpdatesLayouts.setVisibility(View.VISIBLE);
                    myInfoMyprofiles.setVisibility(View.GONE);

                } else {
                    myInfoUpdatesLayouts.setVisibility(View.GONE);
                }
            }
        });

        cvExchangeAnalytic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myInfoMyprofiles.getVisibility() == View.GONE) {
                    myInfoMyprofiles.setVisibility(View.VISIBLE);
                    myInfoUpdatesLayouts.setVisibility(View.GONE);

                } else {
                    myInfoMyprofiles.setVisibility(View.GONE);
                }
            }
        });
        cvMyprofileCreatenew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contanerProfileCreate.getVisibility() == View.GONE) {
                    contanerProfileCreate.setVisibility(View.VISIBLE);
                    contanerMyprofileProfiles.setVisibility(View.GONE);

                } else {
                    contanerProfileCreate.setVisibility(View.GONE);
                }
            }
        });
        return view;
    }
    private void getBusinessListData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_ProfileId","");
        Call<ResponseBusinessProfile> call = apiServices.getBusinessProfileList(object);
        call.enqueue(new Callback<ResponseBusinessProfile>() {
            @Override
            public void onResponse(Call<ResponseBusinessProfile> call, Response<ResponseBusinessProfile> response) {
                hideLoading();
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterBusinessProfileLst adapterBusinessProfileLst = new AdapterBusinessProfileLst(response.body().getLst(), context, SetActionNew.this, SetActionNew.this);
                    rvList.setAdapter(adapterBusinessProfileLst);
                   // showMessage(response.body().getMessage());
                }
                else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseBusinessProfile> call, Throwable t) {
                hideLoading();
            }
        });
    }


    public void getStatusNfc() {
        //showLoading();
        JsonObject object1 = new JsonObject();
        object1.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        LoggerUtil.logItem(object1);

        Call<GetNfcActicateStatus> call1 = apiServices.getNfcStatus(object1);
        call1.enqueue(new Callback<GetNfcActicateStatus>() {
            @Override
            public void onResponse(Call<GetNfcActicateStatus> call, Response<GetNfcActicateStatus> response) {
                LoggerUtil.logItem(response.body());
                //hideLoading();
                if (response.body().getIsActivated().equalsIgnoreCase("false")) {
                    // hideLoading();
                    //showMessage( response.body().getStatusCode());
                    carviewScanner.setVisibility(View.VISIBLE);
                    cardView1.setVisibility(View.GONE);
                    getStatusNfc();
                } else {
                    // cardView1.setVisibility(View.GONE);
                    carviewScanner.setVisibility(View.GONE);
                    cardView1.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<GetNfcActicateStatus> call, Throwable t) {
                hideLoading();
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                goToActivityWithFinish(NewContainerActivity.class, null);

                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }
    private void getBusinessProfileEdit(String Id) {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_ProfileId", Id);

        Call<ResponseGetEditBusinessProfile> call = apiServices.getBusinessProfileEdit(object);
        call.enqueue(new Callback<ResponseGetEditBusinessProfile>() {
            @Override
            public void onResponse(Call<ResponseGetEditBusinessProfile> call, Response<ResponseGetEditBusinessProfile> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    //showMessage(response.body().getMessage());
                    first_name=response.body().getFirstName();
                    last_name=response.body().getLastName();


                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseGetEditBusinessProfile> call, Throwable t) {
                hideLoading();
            }
        });
    }

    public void createNewProfile() {
        showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("ProfileName", etMyprofileProfileName.getText().toString().trim());
        object.addProperty("ProfileType",profiletype);

        Call<CreateProfileResponse> call = apiServices.CreateNewProfile(object);
        call.enqueue(new Callback<CreateProfileResponse>() {
            @Override
            public void onResponse(Call<CreateProfileResponse> call, Response<CreateProfileResponse> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());


                } else {
                    showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<CreateProfileResponse> call, Throwable t) {
                hideLoading();
            }
        });

    }

    @Override
    public void getbusinessId(String Id, String pk_userid) {

        Bundle  bundle=new Bundle();
        bundle.putString("Pk_profileId",Id);
        businessProfileId=Id;
        //   goToActivity(SetAction.class,bundle);

        //  Toast.makeText(context,pk_profileid+"", Toast.LENGTH_SHORT).show();
           // getBusinessProfileEdit(Id);
          /*  btnBusinessUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  //  upadetBusinessProfile(Id, pk_userid);
                }
            });*/
       EditBusinessProfile();
        getBusinessProfileEdit(businessProfileId);

    }

    @Override
    public void getBusinessprofilecheckbox(String Id, Boolean checkbox) {
        updateincludebusinessprofile(Id,checkbox);
    }

    private void updateincludebusinessprofile(String Id, boolean status) {
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("IsActive", status);
        object.addProperty("PK_ProfileId", Id);

        Call<ResponseStatusMessage> call = apiServices.updateBusinessProfileStatus(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                // hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getBusinessListData();
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                //hideLoading();
            }
        });
    }
    private void EditBusinessProfile() {
        editProfileDilog = new BottomSheetDialog(context);
//        editProfileDilog.setPeekHeight(view.getMeasuredHeight());
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_edit_profile, null);
        editProfileDilog.setContentView(sheetView);
       // recyclerView = sheetView.findViewById(R.id.nfc_list);
        Button btn_cancle_profile = sheetView.findViewById(R.id.btn_cancle_profile);
        Button btn_update_profile = sheetView.findViewById(R.id.btn_update_profile);
        EditText et_First_name_profile=sheetView.findViewById(R.id.et_First_name_profile);
       EditText et_last_name_profile=sheetView.findViewById(R.id.et_last_name_profile);
       EditText et_Email_profile=sheetView.findViewById(R.id.et_Email_profile);
        EditText et_url_profile=sheetView.findViewById(R.id.et_url_profile);
       EditText et_social_media_profile=sheetView.findViewById(R.id.et_social_media_profile);
      TextView et_birthday_profile=sheetView.findViewById(R.id.et_birthday_profile);
       EditText et_notes_profile=sheetView.findViewById(R.id.et_notes_profile);
       ImageView img_member=sheetView.findViewById(R.id.img_member);
       img_member.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             showDialog();
           }
       });
        et_First_name_profile.setText(first_name);
       et_last_name_profile.setText(last_name);
        btn_cancle_profile.setOnClickListener(v -> {
            editProfileDilog.dismiss();
        });


        btn_update_profile.setOnClickListener(v -> {
            editProfileDilog.dismiss();
            upadetBusinessProfile(et_First_name_profile.getText().toString().trim(),et_last_name_profile.getText().toString().trim());

        });

        editProfileDilog.setCancelable(false);
        editProfileDilog.setCanceledOnTouchOutside(false);
        editProfileDilog.show();
    }
    private void upadetBusinessProfile(String first_name,String last_name) {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("FirstName", first_name);
        object.addProperty("LastName",last_name);
        object.addProperty("PK_ProfileId",businessProfileId);
        Call<ResponseStatusMessage> call = apiServices.updateBusinessProfile(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    // etName.setKeyListener(null);
                    //  etSirName.setKeyListener(null);

                    getBusinessListData();

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
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

    File homeWorkFile, compressedImageFile;

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
