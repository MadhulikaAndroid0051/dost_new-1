package com.urdost.Fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.urdost.Activity.MyBusinessProfile;
import com.urdost.Activity.NewContainerActivity;
import com.urdost.Adapter.AdapterAnalytics;
import com.urdost.Adapter.AdapterSocialMedia;
import com.urdost.Adapter.AdapterWebLink;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.FileUtils;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseFragment;
import com.urdost.interfaces.NFCWebDelate;
import com.urdost.interfaces.NfcDeletId;
import com.urdost.interfaces.NfcEmailDelate;
import com.urdost.interfaces.NfcSocialDelate;
import com.urdost.interfaces.RediractCheckbox;
import com.urdost.interfaces.WebIsDiract;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.response.BusinessInfo.ResponseBusinessInfo;
import com.urdost.model.response.NFcContact.ResponseGetContact;
import com.urdost.model.response.GeSocialMediaLink.ResponseSocialMediaLink;
import com.urdost.model.response.GetEmail.ResponseGetEmail;
import com.urdost.model.response.GetNfcActicateStatus;
import com.urdost.model.response.ResponseBusinessInformation;
import com.urdost.model.response.ResponseNfcSaveData;
import com.urdost.model.response.ResponseTerms;
import com.urdost.model.response.WebLink.ResponseWebLink;
import com.urdost.model.response.exchangeAnalytics.ResponseExchangeAnalytics;
import com.urdost.model.response.responsePersonalInformation.ResponseGetPersonalInfor;
import com.google.gson.JsonObject;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.urdost.retrofit.CheckMobile;
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

public class NFCEditProfile extends BaseFragment implements NfcDeletId, CheckMobile, WebIsDiract,RediractCheckbox, NfcEmailDelate, NfcSocialDelate, NFCWebDelate, IPickCancel, IPickResult {
    Unbinder unbinder;
    @BindView(R.id.tv_personal)
    TextView tvPersonal;

    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.et_about_me)
    EditText etAboutMe;
    @BindView(R.id.male)
    RadioButton male;
    @BindView(R.id.female)
    RadioButton female;
    @BindView(R.id.rg_sex)
    RadioGroup reLeg;
    @BindView(R.id.cardview1)
    RelativeLayout cardview1;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.linear1)
    LinearLayout linear1;
    @BindView(R.id.base_cardview)
    CardView baseCardview;
    @BindView(R.id.tv_bussiness)
    TextView tvBussiness;
    @BindView(R.id.text11)
    TextView text11;
    @BindView(R.id.et_business_name)
    EditText etBusinessName;
    @BindView(R.id.text12)
    TextView text12;
    @BindView(R.id.et_business_contact)
    EditText etBusinessContact;
    @BindView(R.id.text13)
    TextView text13;
    @BindView(R.id.et_business_mail)
    EditText etBusinessMail;
    @BindView(R.id.text14)
    TextView text14;
    @BindView(R.id.et_Discription)
    EditText etDiscription;
    @BindView(R.id.text15)
    TextView text15;
    @BindView(R.id.et_about)
    EditText etAbout;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.linear2)
    LinearLayout linear2;
    @BindView(R.id.base_cardview6)
    CardView baseCardview6;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.et_contact)
    EditText etContact;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.bt_update)
    Button btUpdate;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.linear3)
    LinearLayout linear3;
    @BindView(R.id.base_cardview1)
    CardView baseCardview1;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.et_Email)
    EditText etEmail;
    @BindView(R.id.bt_updateemail)
    Button btUpdateemail;
    @BindView(R.id.recyclerviewemail)
    RecyclerView recyclerviewemail;
    @BindView(R.id.linear4)
    LinearLayout linear4;
    @BindView(R.id.base_cardview2)
    CardView baseCardview2;
    @BindView(R.id.tv_web)
    TextView tvWeb;
    @BindView(R.id.text_weblink)
    TextView textWeblink;
    @BindView(R.id.et_website)
    EditText etWebsite;
    @BindView(R.id.checkBoxweblink)
    CheckBox checkBoxweblink;
    @BindView(R.id.bt_update_web_link)
    Button btUpdateWebLink;
    @BindView(R.id.recyclerview_Web_link)
    RecyclerView recyclerviewWebLink;
    @BindView(R.id.linear5)
    LinearLayout linear5;
    @BindView(R.id.base_cardview3)
    CardView baseCardview3;
    @BindView(R.id.tv_social_media)
    TextView tvSocialMedia;
    @BindView(R.id.text_social)
    TextView textSocial;
    @BindView(R.id.et_social_media)
    EditText etSocialMedia;
    @BindView(R.id.checkBox_social)
    CheckBox checkBoxSocial;
    @BindView(R.id.bt_update_social)
    Button btUpdateSocial;
    @BindView(R.id.recyclerview_social)
    RecyclerView recyclerviewSocial;
    @BindView(R.id.linear6)
    LinearLayout linear6;
    Bundle bundle;
    String isdownline = "";
    String leg = "L";
    String IsRedirectWeb = "";
    String IsRedirectMEdia = "";

    String isclick = "";
    @BindView(R.id.set_action1)
    ConstraintLayout set_action1;
    RecyclerView.LayoutManager layoutManagermobile, layoutManageremail, layoutmanagerweblink, layoutManagersocialmedia, LayoutAnalytics;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.base_cardview4)
    CardView baseCardview4;
    @BindView(R.id.linear_layout1)
    LinearLayout linearLayout1;
    @BindView(R.id.crd_EXCHANGE)
    CardView crdEXCHANGE;
    @BindView(R.id.crd_my_bublic)
    CardView crdMyBublic;
    @BindView(R.id.tv_social_link)
    TextView tvSocialLink;
    @BindView(R.id.ic_profile)
    ImageView icProfile;
    @BindView(R.id.tv_set_action)
    TextView tvSetAction;
    @BindView(R.id.img_member)
    ImageView imgMember;
    @BindView(R.id.card_view1)
    CardView cardView1;
    @BindView(R.id.tv_list_name)
    TextView tvListName;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private String sociallink = "", weblink = "";
    File documentFile;
    @BindView(R.id.tv_Web_link)
    TextView tvWebLink;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_profile_nfc, container, false);
        unbinder = ButterKnife.bind(this, view);
        //cardView1.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {


            @Override

            public void run() {
                getStatusNfc();
                // Toast.makeText(context, "lnkfdns", Toast.LENGTH_SHORT).show();
            }
        }, 2);


        cardView1.setOnClickListener(new View.OnClickListener() {
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

        layoutManagermobile = new LinearLayoutManager(context);
        layoutManageremail = new LinearLayoutManager(context);
        layoutmanagerweblink = new LinearLayoutManager(context);
        layoutManagersocialmedia = new LinearLayoutManager(context);
        LayoutAnalytics = new LinearLayoutManager(context);
        rvList.setLayoutManager(LayoutAnalytics);
        rvList.setHasFixedSize(true);

        recyclerview.setLayoutManager(layoutManagermobile);
        recyclerview.setHasFixedSize(true);

        recyclerviewemail.setLayoutManager(layoutManageremail);
        recyclerviewemail.setHasFixedSize(true);

        recyclerviewWebLink.setLayoutManager(layoutmanagerweblink);
        recyclerviewWebLink.setHasFixedSize(true);

        recyclerviewSocial.setLayoutManager(layoutManagersocialmedia);
        recyclerviewSocial.setHasFixedSize(true);



        icProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialog();
               // goToActivity(ImageProfile.class,null);
            }
        });
        //for social media link
        checkBoxSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxSocial.isChecked()) {
                    IsRedirectMEdia = "true";

                } else {
                    IsRedirectMEdia = "false";
                }

            }
        });

        crdMyBublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToActivity(MyBusinessProfile.class, bundle);
            }
        });
        tvSocialLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup_sidemenu = new PopupMenu(context, tvSocialLink);
                popup_sidemenu.getMenuInflater().inflate(R.menu.menu_social, popup_sidemenu.getMenu());
                popup_sidemenu.setOnMenuItemClickListener(item -> {
                    if (item.getTitle().equals("Facebook"))
                        sociallink = "https://www.facebook.com/";
                    else if (item.getTitle().equals("Instagram"))
                        sociallink = "https://www.instagram.com/";
                    else if (item.getTitle().equals("Twitter"))
                        sociallink = "https://twitter.com/";
                    else if (item.getTitle().equals("LinkedIn"))
                        sociallink = "https://www.linkedin.com/in/";
                    else if (item.getTitle().equals("YouTube"))
                        sociallink = "https://m.youtube.com/channel/";
                    tvSocialLink.setText(item.getTitle());
                    return true;
                });
                popup_sidemenu.show();
            }
        });

        tvWebLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup_sidemenu1 = new PopupMenu(context, tvWebLink);
                popup_sidemenu1.getMenuInflater().inflate(R.menu.menu_weblink, popup_sidemenu1.getMenu());
                popup_sidemenu1.setOnMenuItemClickListener(item -> {
                    if (item.getTitle().equals("HTTP"))
                        weblink = "http://";
                    else if (item.getTitle().equals("HTTPS"))
                        weblink = "https://";

                    tvWebLink.setText(item.getTitle());
                    return true;
                });
                popup_sidemenu1.show();
            }
        });
        //for profile image
        // imgMember.setOnClickListener(new View.OnClickListener() {
        // @Override
        //  public void onClick(View v) {
        // showDialog();

        //  }
        //  });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (set_action1.getVisibility() == View.GONE) {


                    set_action1.setVisibility(View.VISIBLE);
                } else set_action1.setVisibility(View.GONE);
            }
        });
        //for web link
        checkBoxweblink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxweblink.isChecked()) {
                    IsRedirectWeb = "true";
                } else {
                    IsRedirectWeb = "false";
                }

            }
        });
        //for mobile no
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    isdownline = "true";
                } else {
                    isdownline = "false";
                }

            }
        });
        reLeg.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.male)
                leg = "L";
            else leg = "R";
        });

        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // new Handler().postDelayed(new Runnable() {

                // Using handler with postDelayed called runnable run method

                // @Override

                //  public void run() {
                if (linear3.getVisibility() == View.GONE) {


                    linear3.setVisibility(View.VISIBLE);
                    linear1.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linear4.setVisibility(View.GONE);
                    linear5.setVisibility(View.GONE);
                    linear6.setVisibility(View.GONE);
                    getData();
                } else {
                    linear3.setVisibility(View.GONE);
                    linear1.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linear4.setVisibility(View.GONE);
                    linear5.setVisibility(View.GONE);
                    linear6.setVisibility(View.GONE);
                }

                //   }, 1000);

            }
        });

        return view;

    }

    @OnClick({R.id.tv_personal, R.id.btn_update, R.id.linear1, R.id.tv_bussiness, R.id.btn_save, R.id.linear2,
            R.id.tv_phone, R.id.bt_update, R.id.linear3, R.id.tv_email, R.id.bt_updateemail, R.id.linear4, R.id.tv_web,
            R.id.bt_update_web_link, R.id.linear5, R.id.tv_social_media, R.id.bt_update_social, R.id.linear6})
    public void onClick(View view) {
        switch (view.getId()) {
            //Personal Information
            case R.id.tv_personal:

                //new Handler().postDelayed(new Runnable() {


                //   @Override

                // public void run() {
                if (linear1.getVisibility() == View.GONE) {

                    linear1.setVisibility(View.VISIBLE);
                    linear2.setVisibility(View.GONE);
                    linear3.setVisibility(View.GONE);
                    linear4.setVisibility(View.GONE);
                    linear5.setVisibility(View.GONE);
                    linear6.setVisibility(View.GONE);
                    getDataProfile();
                } else {
                    linear1.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linear3.setVisibility(View.GONE);
                    linear4.setVisibility(View.GONE);
                    linear5.setVisibility(View.GONE);
                    linear6.setVisibility(View.GONE);
                }
                //  }, 1000);

                break;
            case R.id.btn_update:
                if (NetworkUtils.getConnectivityStatus(context) != 0) {
                    if (validatePersonal())
                        getUpdate();
                } else showMessage(R.string.alert_internet);
                break;
            case R.id.linear1:
                break;

            //Business Information
            case R.id.tv_bussiness:
                if (linear2.getVisibility() == View.GONE) {

                    linear2.setVisibility(View.VISIBLE);
                    linear1.setVisibility(View.GONE);
                    linear3.setVisibility(View.GONE);
                    linear4.setVisibility(View.GONE);
                    linear5.setVisibility(View.GONE);
                    linear6.setVisibility(View.GONE);
                    getBusinessInfoData();
                } else {
                    linear2.setVisibility(View.GONE);
                    linear1.setVisibility(View.GONE);
                    linear3.setVisibility(View.GONE);
                    linear4.setVisibility(View.GONE);
                    linear5.setVisibility(View.GONE);
                    linear6.setVisibility(View.GONE);
                }
                //  }
                //  },1000);
                break;
            case R.id.btn_save:

                getUpdateBusinessInfo();


                break;
            case R.id.linear2:

                break;
            case R.id.tv_phone:

                break;

            case R.id.bt_update:
                SaveData();
                etContact.getText().clear();
                checkBox.isChecked();
                break;
            case R.id.linear3:
                break;
            case R.id.tv_email:


                if (linear4.getVisibility() == View.GONE) {
                    showLoading();

                    linear4.setVisibility(View.VISIBLE);
                    linear1.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linear3.setVisibility(View.GONE);
                    linear5.setVisibility(View.GONE);
                    linear6.setVisibility(View.GONE);
                    new Handler().postDelayed(new Runnable() {


                        @Override

                        public void run() {
                            getEmailData();
                            etEmail.getText().clear();
                            checkBox.isChecked();
                            hideLoading();

                        }
                    }, 2500);

                } else {
                    linear4.setVisibility(View.GONE);
                    linear1.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linear3.setVisibility(View.GONE);
                    linear5.setVisibility(View.GONE);
                    linear6.setVisibility(View.GONE);
                }

                break;
            case R.id.bt_updateemail:

                linear4.setVisibility(View.VISIBLE);
                linear1.setVisibility(View.GONE);
                linear2.setVisibility(View.GONE);
                linear3.setVisibility(View.GONE);
                linear5.setVisibility(View.GONE);
                linear6.setVisibility(View.GONE);

                SaveEmailData();
                etEmail.getText().clear();


                break;
            case R.id.tv_web:
                if (linear5.getVisibility() == View.GONE) {
                    linear5.setVisibility(View.VISIBLE);
                    linear1.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linear3.setVisibility(View.GONE);
                    linear4.setVisibility(View.GONE);
                    linear6.setVisibility(View.GONE);

                    etWebsite.getText().clear();
                    getWebLinkData();
                } else {
                    linear5.setVisibility(View.GONE);
                    linear1.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linear3.setVisibility(View.GONE);
                    linear4.setVisibility(View.GONE);
                    linear6.setVisibility(View.GONE);

                }

                break;
            case R.id.bt_update_web_link:

                if (NetworkUtils.getConnectivityStatus(context) != 0)
                    if (validateweblink()) {

                        SaveWebLinkData();
                        etWebsite.getText().clear();
                    } else showMessage(getString(R.string.alert_internet));

                break;
            case R.id.tv_social_media:
                if (linear6.getVisibility() == View.GONE) {
                    linear6.setVisibility(View.VISIBLE);
                    linear1.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linear3.setVisibility(View.GONE);
                    linear4.setVisibility(View.GONE);
                    linear5.setVisibility(View.GONE);
                    etSocialMedia.getText().clear();

                    getSocialMediaData();
                } else {
                    linear6.setVisibility(View.GONE);
                    linear1.setVisibility(View.GONE);
                    linear2.setVisibility(View.GONE);
                    linear3.setVisibility(View.GONE);
                    linear4.setVisibility(View.GONE);
                    linear5.setVisibility(View.GONE);
                }

                break;
            case R.id.bt_update_social:

                if (NetworkUtils.getConnectivityStatus(context) != 0)
                    if (validatesocialmedia())

                        SaveSocialMediaData();
                    else showMessage(getString(R.string.alert_internet));


                break;

            case R.id.linear6:
                break;
        }
    }

    private void getData() {
        showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        Call<ResponseGetContact> call = apiServices.getContact(object);
        call.enqueue(new Callback<ResponseGetContact>() {
            @Override
            public void onResponse(Call<ResponseGetContact> call, Response<ResponseGetContact> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                   // AdapterContact adapter = new AdapterContact(response.body().getLstContact(), context,
                       //     NFCEditProfile.this, NFCEditProfile.this);
                  //  recyclerview.setAdapter(adapter);
                    hideLoading();
                    showMessage(response.body().getMessage());

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseGetContact> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void SaveData() {
        // showLoading();
        JsonObject object = new JsonObject();
       /* {
            "ContentSocialMedia": "string",
                "Pk_NfcProfileId": 0,
                "ContentWebLinks": "string",
                "ContentEmail": "string",
                "IsWhatsapp": true,
                "IsRedirectWeb": true,
                "IsRedirectSocial": true,
                "IsRedirect": true,
                "Contact": "7268998637",
                "Type": "string",
                "PK_UserId": "1"
        }*/
        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Contact", etContact.getText().toString().trim());
        object.addProperty("IsWhatsapp", isdownline);
        object.addProperty("Type", "string");
        Call<ResponseNfcSaveData> call = apiServices.getNfcData(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                // hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());

                    getData();
                    // hideLoading();

                    // goToActivityWithFinish(context,ContactNo.class,null);
                } else showMessage(response.body().getMessage());
                // tvPhone.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                // hideLoading();
            }
        });
    }


    private boolean validatemobile() {
        if (etContact.getText().toString().trim().length() != 10) {
            etContact.setError("Enter valid mobile number");
            return false;
        }
        return true;
    }

    private void RemoveData(String Id) {
        // showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Pk_NfcProfileId", Id);

        Call<ResponseNfcSaveData> call = apiServices.DeletNfcData(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                //  hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());

                    getData();


                    //goToActivityWithFinish(context,ContactNo.class);
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                //  hideLoading();
            }
        });
    }

    @Override
    public void getDeleatId(String pkNfcProfileId) {
        Bundle bundle = new Bundle();
        bundle.putString("Pk_NfcProfileId", pkNfcProfileId);


        RemoveData(pkNfcProfileId);
        // RemoveEmailData(pkNfcProfileId);
        //   RemoveWebLinkData(pkNfcProfileId);
        //  RemoveSocialMediaData(pkNfcProfileId);


    }

    //Start Personal Information
    private void getDataProfile() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        LoggerUtil.logItem(object);

        Call<ResponseGetPersonalInfor> call = apiServices.getPersonalInformation(object);
        call.enqueue(new Callback<ResponseGetPersonalInfor>() {
            @Override
            public void onResponse(Call<ResponseGetPersonalInfor> call, Response<ResponseGetPersonalInfor> response) {
                hideLoading();
                try {
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        Glide.with(context).load(response.body().getData().getProfilePic()).into(icProfile);
                        etFirstName.setText(response.body().getData().getFirstName());
                        etLastName.setText(response.body().getData().getLastName());
                        etAboutMe.setText(response.body().getData().getSummary());

                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseGetPersonalInfor> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void getUpdate() {
        //showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("FirstName", etFirstName.getText().toString().trim());
        object.addProperty("LastName", etLastName.getText().toString().trim());
        object.addProperty("Summary", etAboutMe.getText().toString().trim());
        object.addProperty("Leg", leg);
        LoggerUtil.logItem(object);

        Call<ResponseNfcSaveData> call = apiServices.updatenfcPersonalinfo(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                //  hideLoading();
                try {
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {

                        showMessage(response.body().getMessage());
                        etFirstName.getText().clear();
                        etLastName.getText().clear();
                        etAbout.getText().clear();
                        getDataProfile();
                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                //hideLoading();
            }
        });
    }

    private boolean validatePersonal() {
        if (etFirstName.getText().toString().trim().length() == 0) {
            etFirstName.setError("Enter First Name");
            return false;
        } else if (etLastName.getText().toString().trim().length() == 0) {
            etLastName.setError("Enter Last Name");
            return false;
        } else if (etAboutMe.getText().toString().trim().length() == 0) {
            etAboutMe.setError("Enter About Me");
            return false;
        }
        return true;
    }

    PickImageDialog dialog;

    void showDialo() {
        PickSetup pickSetup = new PickSetup();
        pickSetup.setTitle("Select Image");
        pickSetup.setPickTypes(EPickType.GALLERY, EPickType.CAMERA);
        pickSetup.setGalleryIcon(com.vansuita.pickimage.R.mipmap.gallery_colored);
        pickSetup.setCameraIcon(com.vansuita.pickimage.R.mipmap.camera_colored);
        pickSetup.setCancelTextColor(R.color.colorAccent);
        // If show system dialog..
     // pickSetup.setSystemDialog(true);

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

/*
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
                // Toast.makeText(context, result.getUri()+"", Toast.LENGTH_SHORT).show();
                Log.e("ADDRESS File ", homeWorkFile.toString());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                // Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
//                        Exception error = result.getError();
            }
        }
    }*/


  @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

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
                Toast.makeText(context, result.getUri() + "", Toast.LENGTH_SHORT).show();
                Log.e("ADDRESS File ", homeWorkFile.toString());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
//                        Exception error = result.getError();
            }
            super.onActivityResult(requestCode, resultCode, data);
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

    //End Personal Information

    //Start Business Information

    private void getUpdateBusinessInfo() {
        // showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("BusinessName", etBusinessName.getText().toString().trim());
        object.addProperty("BusinessMail", etBusinessMail.getText().toString().trim());
        object.addProperty("Description", etDiscription.getText().toString().trim());
        object.addProperty("BusinessContact", etBusinessContact.getText().toString().trim());
        object.addProperty("About", etAbout.getText().toString().trim());

        object.addProperty("Leg", leg);
        LoggerUtil.logItem(object);

        Call<ResponseBusinessInformation> call = apiServices.updateBusinessInfo(object);
        call.enqueue(new Callback<ResponseBusinessInformation>() {
            @Override
            public void onResponse(Call<ResponseBusinessInformation> call, Response<ResponseBusinessInformation> response) {

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {

                    showMessage(response.body().getMessage());
                    getBusinessInfoData();
                } else showMessage(response.body().getMessage());

            }


            @Override
            public void onFailure(Call<ResponseBusinessInformation> call, Throwable t) {
                // hideLoading();
            }
        });
    }

    private void getBusinessInfoData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        LoggerUtil.logItem(object);

        Call<ResponseBusinessInfo> call = apiServices.getBusinessInfo(object);
        call.enqueue(new Callback<ResponseBusinessInfo>() {
            @Override
            public void onResponse(Call<ResponseBusinessInfo> call, Response<ResponseBusinessInfo> response) {
                hideLoading();

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {

                    etBusinessName.setText(response.body().getData().getBusinessName());
                    etBusinessMail.setText(response.body().getData().getBusinessMail());
                    etBusinessContact.setText(response.body().getData().getBusinessContact());
                    etAbout.setText(response.body().getData().getAbout());
                    etDiscription.setText(response.body().getData().getDescription());


                } else showMessage(response.body().getMessage());


            }

            @Override
            public void onFailure(Call<ResponseBusinessInfo> call, Throwable t) {
                hideLoading();
            }
        });
    }

    //Start Save Email And show Email List
    private void getEmailData() {
        // showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        Call<ResponseGetEmail> call = apiServices.getEmail(object);
        call.enqueue(new Callback<ResponseGetEmail>() {
            @Override
            public void onResponse(Call<ResponseGetEmail> call, Response<ResponseGetEmail> response) {
                //   hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equals("200")) {
                    //AdapterEmail adapter = new AdapterEmail(response.body().getLstEmail(), context, //NFCEditProfile.this,NFCEditProfile.th);
                    //recyclerviewemail.setAdapter(adapter);
                } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found"))
                {
                    recyclerviewSocial.setVisibility(View.GONE);
                }else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseGetEmail> call, Throwable t) {
                //  hideLoading();
            }
        });
    }

    private void SaveEmailData() {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("ContentEmail", etEmail.getText().toString().trim());
        object.addProperty("Type", "string");
        Call<ResponseNfcSaveData> call = apiServices.getNFCEmail(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getEmailData();

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void RemoveEmailData(String Id) {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Pk_NfcProfileId", Id);

        Call<ResponseNfcSaveData> call = apiServices.DeletNfcData(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    new Handler().postDelayed(new Runnable() {


                        @Override

                        public void run() {
                            getEmailData();
                        }
                    }, 1000);

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                hideLoading();
            }
        });
    }
//End Email

    //Start Web link
    private void getWebLinkData() {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        Call<ResponseWebLink> call = apiServices.getWebLink(object);
        call.enqueue(new Callback<ResponseWebLink>() {
            @Override
            public void onResponse(Call<ResponseWebLink> call, Response<ResponseWebLink> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                   // AdapterWebLink adapter = new AdapterWebLink(response.body().getLstWeb(), context, //NFCEditProfile.this,NFCEditProfile.this);

                    //recyclerviewWebLink.setAdapter(adapter);
                    //adapter.notifyDataSetChanged();
                           // adapter.no();



                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseWebLink> call, Throwable t) {
                hideLoading();
            }
        });
    }


    private void SaveWebLinkData() {
        // showLoading();
        String linkweb = weblink + etWebsite.getText().toString().trim();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("ContentWebLinks", linkweb);
        object.addProperty("IsRedirectWeb", IsRedirectWeb);
        object.addProperty("Type", "string");
        Call<ResponseNfcSaveData> call = apiServices.getNfcWebLink(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                //hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getWebLinkData();
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                // hideLoading();
            }
        });
    }

    private void RemoveWebLinkData(String Id) {
        // showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Pk_NfcProfileId", Id);

        Call<ResponseNfcSaveData> call = apiServices.DeletNfcData(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                // hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getWebLinkData();

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                // hideLoading();
            }
        });
    }

    private boolean validateweblink() {
        if (etWebsite.getText().toString().trim().length() < 2) {
            etWebsite.setError("Enter Web link");
            return false;
        }
        return true;
    }
//End Web Link

    //Start Social Media

    private void getSocialMediaData() {
        // showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        Call<ResponseSocialMediaLink> call = apiServices.getSocialMeida(object);
        call.enqueue(new Callback<ResponseSocialMediaLink>() {
            @Override
            public void onResponse(Call<ResponseSocialMediaLink> call, Response<ResponseSocialMediaLink> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    getSocialMediaData();
                   // AdapterSocialMedia adapter = new AdapterSocialMedia(response.body().getLstEmail(),// context, NFCEditProfile.this,NFCEditProfile.this);
                  ///  recyclerviewSocial.setAdapter(adapter);
                  //  adapter.notifyDataSetChanged();
                  //  recyclerviewSocial.setVisibility(View.VISIBLE);

                } else showMessage(response.body().getMessage());
                //recyclerviewSocial.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseSocialMediaLink> call, Throwable t) {
                //hideLoading();
            }
        });
    }

    private void SaveSocialMediaData() {
        //showLoading();
        String linksocial = sociallink + etSocialMedia.getText().toString().trim();
        // Toast.makeText(context,linksocial+ "", Toast.LENGTH_SHORT).show();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("ContentSocialMedia", linksocial);
        object.addProperty("IsRedirectSocial", IsRedirectMEdia);
        object.addProperty("Type", "string");
        Call<ResponseNfcSaveData> call = apiServices.getSocialMedialink(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                //hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getSocialMediaData();
                    etSocialMedia.getText().clear();
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                // hideLoading();
            }
        });
    }

    private void RemoveSocialMediaData(String Id) {
         showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Pk_NfcProfileId", Id);

        Call<ResponseNfcSaveData> call = apiServices.DeletNfcData(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                 hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                   showMessage(response.body().getMessage());
                    getSocialMediaData();

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                 hideLoading();
            }
        });
    }


    private boolean validatesocialmedia() {
        if (etSocialMedia.getText().toString().trim().length() < 2) {
            etSocialMedia.setError("Enter Social Media Link");
            return false;
        }
        return true;
    }


    //End Social Media Link
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

    @Override
    public void getEmailDeleatId(String pkNfcProfileId) {
        Bundle bundle = new Bundle();
        bundle.putString("Pk_NfcProfileId", pkNfcProfileId);


        //RemoveData(pkNfcProfileId);
        RemoveEmailData(pkNfcProfileId);
        //   RemoveWebLinkData(pkNfcProfileId);
        //  RemoveSocialMediaData(pkNfcProfileId);


    }

    @Override
    public void getWebDelate(String pkNfcProfileId) {
        Bundle bundle = new Bundle();
        bundle.putString("Pk_NfcProfileId", pkNfcProfileId);

         getWebLinkData();
        RemoveWebLinkData(pkNfcProfileId);

    }

    @Override
    public void getSocialDelate(String pkNfcProfileId) {
        Bundle bundle = new Bundle();
        bundle.putString("Pk_NfcProfileId", pkNfcProfileId);


      //getSocialMediaData();

        RemoveSocialMediaData(pkNfcProfileId);

    }

    public void getStatusNfc() {
        JsonObject object1 = new JsonObject();
        object1.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        LoggerUtil.logItem(object1);

        Call<GetNfcActicateStatus> call1 = apiServices.getNfcStatus(object1);
        call1.enqueue(new Callback<GetNfcActicateStatus>() {
            @Override
            public void onResponse(Call<GetNfcActicateStatus> call, Response<GetNfcActicateStatus> response) {
                LoggerUtil.logItem(response.body());
                if (response.body().getIsActivated().equalsIgnoreCase("false")) {
                   //showMessage( response.body().getStatusCode());
                    cardView1.setVisibility(View.VISIBLE);
                    cardView.setVisibility(View.GONE);
                    crdMyBublic.setVisibility(View.GONE);
                    crdEXCHANGE.setVisibility(View.GONE);
                    rvList.setVisibility(View.GONE);
                      getStatusNfc();
                } else {
                    cardView1.setVisibility(View.GONE);
                    cardView.setVisibility(View.VISIBLE);
                    crdMyBublic.setVisibility(View.VISIBLE);
                    crdEXCHANGE.setVisibility(View.VISIBLE);
                    tvListName.setVisibility(View.VISIBLE);
                    rvList.setVisibility(View.VISIBLE);

                    getAnalytics();


                            /*if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, 100);
                             else
                                goToActivity(context,Scanner.class, null);*/
                    /*if (!(currentFragment instanceof NFCEditProfile))
                        replaceFragment(new NFcCardActivate(), "Hii "+", " +PreferencesManager.getInstance(context).getFullname());


                }
                else {
                    if (!(currentFragment instanceof NFCEditProfile))
                        replaceFragment(new NFCEditProfile(), "Hii "+", " +PreferencesManager.getInstance(context).getFullname());

                }*/
                }
            }

            @Override
            public void onFailure(Call<GetNfcActicateStatus> call, Throwable t) {
            }
        });

    }

public void getAnalytics()
{
  //  showLoading();

    JsonObject object = new JsonObject();

    object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
    Call<ResponseExchangeAnalytics> call = apiServices.getAnalytics(object);
    call.enqueue(new Callback<ResponseExchangeAnalytics>() {
        @Override
        public void onResponse(Call<ResponseExchangeAnalytics> call, Response<ResponseExchangeAnalytics> response) {
           // hideLoading();
            LoggerUtil.logItem(response.body());
            if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                AdapterAnalytics adapter = new AdapterAnalytics(response.body().getLst(), context);
                rvList.setAdapter(adapter);
                hideLoading();
               // showMessage(response.body().getMessage());

            } else showMessage(response.body().getMessage());
        }

        @Override
        public void onFailure(Call<ResponseExchangeAnalytics> call, Throwable t) {
            hideLoading();
        }
    });
}
    private void GetKnowMore() {
        showLoading();
        Call<ResponseTerms> call = apiServices.GetKnowMore();
        call.enqueue(new Callback<ResponseTerms>() {
            @Override
            public void onResponse(Call<ResponseTerms> call, Response<ResponseTerms> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                createInfoDialog(context, "Distributor Program\n\n", response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseTerms> call, Throwable t) {
                hideLoading();
            }
        });
    }

    @Override
    public void rediractCheckbox(String Id, Boolean checkbox) {
        updateCheckbox(Id,checkbox);

    }
    private void updateCheckbox(String Id ,boolean tfid ) {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Pk_NfcProfileId", Id);
        object.addProperty("IsRedirect",tfid);

        Call<ResponseStatusMessage> call = apiServices.updatecheckbox(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getSocialMediaData();

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }
    private void updateCheckbox2(String Id ,boolean tfid ) {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Pk_NfcProfileId", Id);
        object.addProperty("IsRedirect",tfid);

        Call<ResponseStatusMessage> call = apiServices.updatecheckbox(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
getData();
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }



    @Override
    public void webcheckbox(String Id, Boolean checkbox) {
        updateCheckbox1(Id,checkbox);
    }

    @Override
    public void MobileCheck(String Id, Boolean checkbox) {
        updateCheckbox2(Id,checkbox);
    }
    private void updateCheckbox1(String Id ,boolean tfid ) {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Pk_NfcProfileId", Id);
        object.addProperty("IsRedirect",tfid);

        Call<ResponseStatusMessage> call = apiServices.updatecheckbox(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                //hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getSocialMediaData();

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                //hideLoading();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getStatusNfc();
    }


}
