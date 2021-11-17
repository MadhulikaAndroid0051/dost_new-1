package com.urdost.Fragment.NfcSetActionSection;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
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

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.urdost.Activity.MyBusinessProfile;
import com.urdost.Activity.NewContainerActivity;
import com.urdost.Adapter.AdapterAnalytics;
import com.urdost.Adapter.AdapterBusinessProfileLst;
import com.urdost.Adapter.AdapterContact;
import com.urdost.Adapter.AdapterContactInclude;
import com.urdost.Adapter.AdapterContactList;
import com.urdost.Adapter.AdapterEmail;
import com.urdost.Adapter.AdapterIncludeEmail;
import com.urdost.Adapter.AdapterIncludeSocialMedia;
import com.urdost.Adapter.AdapterIncludeWeb;
import com.urdost.Adapter.AdapterSocialMedia;
import com.urdost.Adapter.AdapterWebLink;
import com.urdost.Adapter.ContactIncludeProfile;
import com.urdost.Fragment.Scanner;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseFragment;
import com.urdost.interfaces.BusinessProfileId;
import com.urdost.interfaces.Chackboxbusinessprofile;
import com.urdost.interfaces.DeleteContactItem;
import com.urdost.interfaces.IncludeEmailProfile;
import com.urdost.interfaces.IncludeSocialMedia;
import com.urdost.interfaces.IncludeWebProfile;
import com.urdost.interfaces.IsReDiractSocial;
import com.urdost.interfaces.NFCWebDelate;
import com.urdost.interfaces.NfcEmailDelate;
import com.urdost.interfaces.NfcSocialDelate;
import com.urdost.interfaces.RedirectSocial;
import com.urdost.interfaces.RedirectWeb;
import com.urdost.interfaces.WebIsDiract;
import com.urdost.interfaces.contactList;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.response.BusinessProfile.ResponseBusinessProfile;
import com.urdost.model.response.GeSocialMediaLink.ResponseSocialMediaLink;
import com.urdost.model.response.GetEmail.ResponseGetEmail;
import com.urdost.model.response.GetNfcActicateStatus;
import com.urdost.model.response.NFcContact.ResponseGetContact;
import com.urdost.model.response.ResponseGetEditBusinessProfile;
import com.urdost.model.response.ResponseNfcSaveData;
import com.urdost.model.response.ResponseSaveProfile;
import com.urdost.model.response.WebLink.ResponseWebLink;
import com.urdost.model.response.exchangeAnalytics.ResponseExchangeAnalytics;
import com.urdost.retrofit.CheckMobile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetAction extends BaseFragment implements BusinessProfileId, Chackboxbusinessprofile, DeleteContactItem, CheckMobile, contactList, ContactIncludeProfile, IncludeEmailProfile, IncludeWebProfile, IncludeSocialMedia, NfcEmailDelate, NFCWebDelate, NfcSocialDelate , WebIsDiract, IsReDiractSocial, RedirectSocial, RedirectWeb {
    @BindView(R.id.textview15)
    TextView textview15;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.textview_action)
    TextView   textview_action;
    private String businessProfileId;

    @BindView(R.id.tv_list_name)
    TextView tvListName;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.simpleSwitch)
    Switch simpleSwitch;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.textView38)
    TextView textView38;
    @BindView(R.id.text7)
    TextView text7;
    @BindView(R.id.cv_exchange_analytic)
    CardView cvExchangeAnalytic;
    @BindView(R.id.cv_my_profile)
    CardView cvMyProfile;
    private String pk_profileid;
    private TextView textview_name;
    private Button add_new_data;
    @BindView(R.id.et_Email)
    EditText etEmail;
    @BindView(R.id.chakbox_Email)
    CheckBox chakboxEmail;

    @BindView(R.id.cv_social)
    CardView cv_social;
    @BindView(R.id.rec_email)
    RecyclerView recEmail;
    @BindView(R.id.layoutEmail)
    ConstraintLayout layoutEmail;
    @BindView(R.id.et_website)
    EditText etWebsite;
    @BindView(R.id.chakbox_Weblink)
    CheckBox chakboxWeblink;
    @BindView(R.id.btn_save_weblink)
    Button btnSaveWeblink;
    @BindView(R.id.rec_weblink)
    RecyclerView recWeblink;
    @BindView(R.id.layoutWeb)
    ConstraintLayout layoutWeb;
    @BindView(R.id.textview1)
    TextView textview1;
    @BindView(R.id.textview2)
    TextView textview2;
    @BindView(R.id.textview3)
    TextView textview3;
    @BindView(R.id.btn_save_Email)
    Button btnSaveEmail;
    @BindView(R.id.tv_Web_link)
    TextView tvWebLink;

    @BindView(R.id.tv_social_link)
    TextView tvSocialLink;
    @BindView(R.id.et_social_media)
    EditText etSocialMedia;
    @BindView(R.id.checkBox_social)
    CheckBox checkBoxSocial;
    @BindView(R.id.check_social_include_profile)
    CheckBox checkSocialIncludeProfile;
    @BindView(R.id.btn_update_social)
    Button btnUpdateSocial;
    @BindView(R.id.rec_social)
    RecyclerView recSocial;
    @BindView(R.id.layoutreferral)
    ConstraintLayout layoutreferral;
    @BindView(R.id.left)
    RadioButton left;
    @BindView(R.id.Right)
    RadioButton Right;
    @BindView(R.id.re_leg)
    RadioGroup reLeg;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.layoutRefreeSetting)
    ConstraintLayout layoutRefreeSetting;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.textview10)
    TextView textview10;
    @BindView(R.id.etv_profile_name)
    EditText etvProfileName;
    @BindView(R.id.text6)
    TextView text6;
    @BindView(R.id.text_social)
    TextView textSocial;
    @BindView(R.id.btn_business_update)
    Button btnBusinessUpdate;
    private RecyclerView recyclerView;
    BottomSheetDialog searchDialog;
    private RecyclerView rv_contact;
    Unbinder unbinder;
    @BindView(R.id.card_view1)
    CardView cardView1;
    @BindView(R.id.linear_layout1)
    LinearLayout linearLayout1;
    @BindView(R.id.layout_btn)
    LinearLayout layoutbtn;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.cv_business_profile)
    CardView cvBusinessProfile;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.cv_call)
    CardView cvCall;
    @BindView(R.id.container1)
    ConstraintLayout container1;

    @BindView(R.id.container12)
    ConstraintLayout container12;
    @BindView(R.id.img3)
    ImageView img3;
    @BindView(R.id.cv_email)
    CardView cvEmail;
    @BindView(R.id.img4)
    ImageView img4;
    @BindView(R.id.cv_weblink)
    CardView cvWeblink;
    @BindView(R.id.btn_add_business_profile)
    Button btnAddBusinessProfile;
    @BindView(R.id.et_profile_name)
    EditText etProfileName;
    @BindView(R.id.btn_save_profile_name)
    Button btnSaveProfileName;
    @BindView(R.id.layout_profile_name)
    ConstraintLayout layoutProfileName;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.img_member)
    ImageView imgMember;
    @BindView(R.id.relative_layout_img)
    RelativeLayout relativeLayoutImg;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_sir_name)
    EditText etSirName;
    @BindView(R.id.et_degination)
    EditText etDegination;
    @BindView(R.id.et_business_name)
    EditText etBusinessName;
    @BindView(R.id.et_Discription)
    EditText etDiscription;
    @BindView(R.id.btn_business_save)
    Button btnBusinessSave;
    @BindView(R.id.layout_profile_save)
    ConstraintLayout layoutProfileSave;


    @BindView(R.id.lst_business_profile)
    RecyclerView lstBusinessProfile;
    @BindView(R.id.layout_business_profile)
    ConstraintLayout layoutBusinessProfile;
    @BindView(R.id.container)
    ConstraintLayout container;

    @BindView(R.id.textview4)
    TextView textview4;
    @BindView(R.id.textview5)
    TextView textview5;
    @BindView(R.id.et_contact)
    EditText etContact;
    @BindView(R.id.chakboxMobile)
    CheckBox chakboxMobile;
    @BindView(R.id.mob_including_prifile)
    CheckBox mobIncludingPrifile;
    @BindView(R.id.bt_save_contact)
    Button btSaveContact;
    @BindView(R.id.recyclerview_contact)
    RecyclerView recyclerviewContact;
    @BindView(R.id.layout_phoneCall)
    ConstraintLayout layoutPhoneCall;
    @BindView(R.id.btn_contact)
    TextView btnContact;
    @BindView(R.id.btn_Email)
    TextView btnEmail;
    @BindView(R.id.btn_web)
    TextView btnWeb;
    @BindView(R.id.btn_social)
    TextView btnSocial;
    @BindView(R.id.container_Set_action)
    ConstraintLayout container_Set_action;
    @BindView(R.id.carviewScanner)
    CardView carviewScanner;
    private String sociallink = "", weblink = "";
    String IsRedirectWeb = "", webincludeprofile = " ", isRedirctEmail = "";
    String IsRediractSocial = "", socialIncludeProfile = " ";
    String includeEmailProfile = "", mobilechackbox = "", includeMobile = "";
    private LinearLayoutManager businessprofilelyout;
    private LinearLayoutManager contactlayoutManager;
    private LinearLayoutManager lstRecyclerview;
    private LinearLayoutManager emaillayoutmanger;
    private LinearLayoutManager welinklayoutmanager, sociallayoutmanager, LayoutAnalytics;
    private LinearLayoutManager linearLayoutManager;
    Dialog contactDilog, emailDialog, WeblinkDialog, socialMediaDialog;
    String leg = "L";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_nfc_action, container, false);
        unbinder = ButterKnife.bind(this, view);
        //for Business Profile
        businessprofilelyout = new LinearLayoutManager(context);
        lstBusinessProfile.setLayoutManager(businessprofilelyout);
        lstBusinessProfile.setHasFixedSize(true);

        //for Contact
        contactlayoutManager = new LinearLayoutManager(context);
        recyclerviewContact.setLayoutManager(contactlayoutManager);
        recyclerviewContact.setHasFixedSize(true);

        //for Email
        emaillayoutmanger = new LinearLayoutManager(context);
        recEmail.setLayoutManager(emaillayoutmanger);
        recEmail.setHasFixedSize(true);

        //for weblink

        welinklayoutmanager = new LinearLayoutManager(context);
        recWeblink.setLayoutManager(welinklayoutmanager);
        recWeblink.setHasFixedSize(true);

        LayoutAnalytics = new LinearLayoutManager(context);
        rvList.setLayoutManager(LayoutAnalytics);
        rvList.setHasFixedSize(true);
        //for social Media
        sociallayoutmanager = new LinearLayoutManager(context);
        recSocial.setLayoutManager(sociallayoutmanager);
        recSocial.setHasFixedSize(true);

        cvMyProfile.setOnClickListener(view1 -> goToActivity(MyBusinessProfile.class, null));
        btnSocial.setOnClickListener(view1 -> openSocialMediaDialog());
        btnContact.setOnClickListener(view1 -> openIncludeContactList());
        btnEmail.setOnClickListener(view1 -> openEmailIncludeDialog());
        btnWeb.setOnClickListener(view1 -> openWeblinkDialog());
        btSaveContact.setOnClickListener(view1 -> SaveContactData());
        btnSaveEmail.setOnClickListener(view1 -> SaveEmailData());
        btnSaveWeblink.setOnClickListener(view1 -> SaveWebLinkData());
        btnUpdate.setOnClickListener(view1 -> updateLeg());
        btnUpdateSocial.setOnClickListener(view1 -> SaveSocialMediaData());
        btnBusinessSave.setOnClickListener(view1 -> BusinessProfileAdd());

        getStatusNfc();



        cvExchangeAnalytic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                container_Set_action.setVisibility(View.GONE);
            }
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

        chakboxWeblink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chakboxWeblink.isChecked()) {
                    IsRedirectWeb = "true";
                } else {
                    IsRedirectWeb = "false";
                }

            }
        });


        reLeg.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.left)
                leg = "L";
            else leg = "R";
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
                        sociallink = "https://m.youtube.com/";
                    tvSocialLink.setText(item.getTitle());
                    return true;
                });
                popup_sidemenu.show();
            }
        });

        chakboxMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chakboxMobile.isChecked()) {
                    mobilechackbox = "true";

                } else {
                    mobilechackbox = "false";
                }

            }
        });
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (container_Set_action.getVisibility() == View.GONE) {


                    container_Set_action.setVisibility(View.VISIBLE);
                } else container_Set_action.setVisibility(View.GONE);
            }
        });

        chakboxEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chakboxEmail.isChecked()) {
                    isRedirctEmail = "true";

                } else {
                    isRedirctEmail = "false";
                }

            }
        });
        checkBoxSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxSocial.isChecked()) {
                    IsRediractSocial = "true";

                } else {
                    IsRediractSocial = "false";
                }

            }
        });

        return view;

    }

    @OnClick({R.id.card_view1, R.id.cv_business_profile, R.id.cv_call, R.id.container1, R.id.container12, R.id.cv_email, R.id.cv_weblink, R.id.btn_add_business_profile, R.id.btn_save_profile_name, R.id.layout_profile_name, R.id.layout_business_profile, R.id.cv_social})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_view1:
                break;
            case R.id.cv_business_profile:
                if (layoutBusinessProfile.getVisibility() == View.GONE) {
                    layoutBusinessProfile.setVisibility(View.VISIBLE);
                    layoutPhoneCall.setVisibility(View.GONE);
                    layoutreferral.setVisibility(View.GONE);
                    layoutRefreeSetting.setVisibility(View.GONE);
                    layoutWeb.setVisibility(View.GONE);
                    layoutEmail.setVisibility(View.GONE);
                    getBusinessListData();
                } else {
                    layoutBusinessProfile.setVisibility(View.GONE);
                }
                break;
            case R.id.cv_call:
                if (layoutPhoneCall.getVisibility() == View.GONE) {
                    layoutPhoneCall.setVisibility(View.VISIBLE);
                    layoutBusinessProfile.setVisibility(View.GONE);
                    layoutreferral.setVisibility(View.GONE);
                    layoutRefreeSetting.setVisibility(View.GONE);
                    layoutWeb.setVisibility(View.GONE);
                    layoutEmail.setVisibility(View.GONE);
                    getContactData();
                } else {
                    layoutPhoneCall.setVisibility(View.GONE);
                }
                break;
            case R.id.container1:
                break;
            case R.id.container12:
                break;
            case R.id.cv_email:
                if (layoutEmail.getVisibility() == View.GONE) {
                    layoutEmail.setVisibility(View.VISIBLE);
                    layoutBusinessProfile.setVisibility(View.GONE);
                    layoutPhoneCall.setVisibility(View.GONE);
                    layoutreferral.setVisibility(View.GONE);
                    layoutRefreeSetting.setVisibility(View.GONE);
                    layoutWeb.setVisibility(View.GONE);

                    getEmailListData();
                } else {
                    layoutEmail.setVisibility(View.GONE);
                }
                break;
            case R.id.cv_weblink:
                if (layoutWeb.getVisibility() == View.GONE) {
                    layoutWeb.setVisibility(View.VISIBLE);
                    layoutBusinessProfile.setVisibility(View.GONE);
                    layoutPhoneCall.setVisibility(View.GONE);
                    layoutEmail.setVisibility(View.GONE);
                    layoutRefreeSetting.setVisibility(View.GONE);
                    layoutreferral.setVisibility(View.GONE);

                    getWebLinkData();
                } else {
                    layoutWeb.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_add_business_profile:
                if (layoutProfileName.getVisibility() == View.GONE) {
                    layoutProfileName.setVisibility(View.VISIBLE);
                    btnBusinessSave.setVisibility(View.VISIBLE);


                } else {
                    layoutProfileName.setVisibility(View.GONE);
                    etProfileName.getText().clear();
                }
                break;
            case R.id.btn_save_profile_name:
                if (etProfileName.getText().toString().length() >=1) {

                    saveBusinessProfileData();
                    layoutbtn.setVisibility(View.VISIBLE);
                    etvProfileName.setText(etProfileName.getText().toString().trim());
                } else {


                    showMessage("Enter Profile Name");
                }
                break;

            case R.id.cv_social:
                if (layoutreferral.getVisibility() == View.GONE) {
                    layoutRefreeSetting.setVisibility(View.GONE);
                    layoutreferral.setVisibility(View.VISIBLE);

                    layoutBusinessProfile.setVisibility(View.GONE);
                    layoutPhoneCall.setVisibility(View.GONE);
                    layoutEmail.setVisibility(View.GONE);
                    layoutWeb.setVisibility(View.GONE);
                    getSocialMediaData();

                } else {
                    layoutreferral.setVisibility(View.GONE);

                }
                break;
            case R.id.layout_profile_name:

                break;

            case R.id.layout_business_profile:
                break;
        }
    }

    private void getBusinessListData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        Call<ResponseBusinessProfile> call = apiServices.getBusinessProfileList(object);
        call.enqueue(new Callback<ResponseBusinessProfile>() {
            @Override
            public void onResponse(Call<ResponseBusinessProfile> call, Response<ResponseBusinessProfile> response) {
                hideLoading();
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterBusinessProfileLst adapterBusinessProfileLst = new AdapterBusinessProfileLst(response.body().getLst(), context, SetAction.this, SetAction.this);
                    lstBusinessProfile.setAdapter(adapterBusinessProfileLst);
                }
            }

            @Override
            public void onFailure(Call<ResponseBusinessProfile> call, Throwable t) {
                hideLoading();
            }
        });
    }


    @Override
    public void getBusinessprofilecheckbox(String Id, Boolean checkbox) {
        updateincludebusinessprofile(Id, checkbox);
    }

    private void saveBusinessProfileData() {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("ProfileName", etProfileName.getText().toString().trim());
        Call<ResponseSaveProfile> call = apiServices.saveprofilename(object);
        call.enqueue(new Callback<ResponseSaveProfile>() {
            @Override
            public void onResponse(Call<ResponseSaveProfile> call, Response<ResponseSaveProfile> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    layoutProfileSave.setVisibility(View.VISIBLE);
                    layoutProfileName.setVisibility(View.GONE);
                    etProfileName.getText().clear();
                    pk_profileid = response.body().getPKProfileId();
                    // layoutProfileSave.setVisibility(View.VISIBLE);
                    getBusinessListData();

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseSaveProfile> call, Throwable t) {
                hideLoading();
            }
        });
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
                    etName.setText(response.body().getFirstName());
                    etSirName.setText(response.body().getLastName());
                    etBusinessName.setText(response.body().getBusinessName());
                    etDiscription.setText(response.body().getDesignation());
                    etDegination.setText(response.body().getDescription());
                    etvProfileName.setText(response.body().getProfileName());

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseGetEditBusinessProfile> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void upadetBusinessProfile(String Id, String pk_userid) {
        showLoading();
        JsonObject object = new JsonObject();
        businessProfileId=Id;
        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("FirstName", etName.getText().toString().trim());
        object.addProperty("LastName", etSirName.getText().toString().trim());
        object.addProperty("PK_ProfileId", Id);
        object.addProperty("Designation", etDegination.getText().toString().trim());
        object.addProperty("BusinessName", etBusinessName.getText().toString().trim());
        object.addProperty("Description", etDiscription.getText().toString().trim());
        object.addProperty("ProfileName", etvProfileName.getText().toString().trim());
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
                    layoutProfileSave.setVisibility(View.GONE);

                    getBusinessListData();

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void BusinessProfileAdd() {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("FirstName", etName.getText().toString().trim());
        object.addProperty("LastName", etSirName.getText().toString().trim());
        object.addProperty("PK_ProfileId", pk_profileid);
        object.addProperty("Designation", etDegination.getText().toString().trim());
        object.addProperty("BusinessName", etBusinessName.getText().toString().trim());
        object.addProperty("Description", etDiscription.getText().toString().trim());
        // object.addProperty("ProfileName",etvProfileName.getText().toString().trim());
        Call<ResponseStatusMessage> call = apiServices.updateBusinessProfile(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());

                    layoutProfileSave.setVisibility(View.GONE);
                    etvProfileName.getText().clear();
                    etName.getText().clear();
                    etSirName.getText().clear();
                    etBusinessName.getText().clear();
                    etDiscription.getText().clear();
                    etDegination.getText().clear();

                    getBusinessListData();

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }
    //Start Contact Section

    private void getContactData() {
        showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_ProfileId","");
        Call<ResponseGetContact> call = apiServices.getContact(object);
        call.enqueue(new Callback<ResponseGetContact>() {
            @Override
            public void onResponse(Call<ResponseGetContact> call, Response<ResponseGetContact> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterContact adapter = new AdapterContact(response.body().getLstContact(), context, SetAction.this, SetAction.this);
                    recyclerviewContact.setAdapter(adapter);
                    recyclerviewContact.setVisibility(View.VISIBLE);

                } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    recyclerviewContact.setVisibility(View.GONE);
                } else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    recyclerviewContact.setVisibility(View.VISIBLE);
                } else {
                    showMessage(response.body().getMessage());
                    recyclerviewContact.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetContact> call, Throwable t) {
                hideLoading();
            }
        });
    }


    private void RemoveContact(String Id) {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Pk_NfcProfileId", Id);

        Call<ResponseNfcSaveData> call = apiServices.DeletNfcData(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getContactData();
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void updatecontactChekbox(String Id, boolean tfid) {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_NFCProfileId", Id);
        object.addProperty("IsWhatsapp", tfid);


        Call<ResponseStatusMessage> call = apiServices.deleteContact(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getContactData();
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void SaveContactData() {
        // showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("ContactNo", etContact.getText().toString().trim());
        object.addProperty("IsWhatsapp", mobilechackbox);
        object.addProperty("IsInclude", includeEmailProfile);
        Call<ResponseNfcSaveData> call = apiServices.getNfcData(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                // hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());

                    getContactData();
                    etContact.getText().clear();
                    chakboxMobile.setChecked(false);


                } else showMessage(response.body().getMessage());
                // tvPhone.setVisibility(View.VISIBLE);

            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                // hideLoading();
            }
        });
    }


    @Override
    public void getDeleteContact(String pkNfcProfileId) {
        RemoveContact(pkNfcProfileId);
    }

    @Override
    public void MobileCheck(String Id, Boolean checkbox) {
        updatecontactChekbox(Id, checkbox);

    }

    private void searchhDialog() {
        searchDialog = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dialog_nfc_list, null);
        searchDialog.setContentView(sheetView);
        recyclerView = sheetView.findViewById(R.id.nfc_list);
        Button btn_cancel = sheetView.findViewById(R.id.btn_cancel);
        Button btn_search = sheetView.findViewById(R.id.btn_search);
        lstRecyclerview = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(lstRecyclerview);
        recyclerView.setHasFixedSize(true);
        getConatcData();
        btn_cancel.setOnClickListener(v -> {
            searchDialog.dismiss();
        });


        btn_search.setOnClickListener(v -> {
            searchDialog.dismiss();

        });

        searchDialog.setCancelable(false);
        searchDialog.setCanceledOnTouchOutside(false);
        searchDialog.show();
    }

    private void getConatcData() {
        showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_ProfileId","");

        Call<ResponseGetContact> call = apiServices.getContact(object);
        call.enqueue(new Callback<ResponseGetContact>() {
            @Override
            public void onResponse(Call<ResponseGetContact> call, Response<ResponseGetContact> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterContactList adapter = new AdapterContactList(response.body().getLstContact(), context, SetAction.this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setVisibility(View.VISIBLE);

                    //  } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    //       recyclerviewContact.setVisibility(View.GONE);
                    //  }
                    //     else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    //      recyclerviewContact.setVisibility(View.VISIBLE);
                } else {
                    showMessage(response.body().getMessage());
                    recyclerviewContact.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<ResponseGetContact> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void updateEmailIncludeProfile(String Id, boolean status) {
        // showLoading();



        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("IsIncluded", status);
        object.addProperty("Pk_NfcProfileId", Id);
        object.addProperty("PK_ProfileId",businessProfileId);

        Call<ResponseStatusMessage> call = apiServices.includeEmailProfile(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                // hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getConatcData();
                    //  Toast.makeText(context, pk_profileid+"", Toast.LENGTH_SHORT).show();
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                //hideLoading();
            }
        });
    }

    @Override
    public void getContactList(String Id, Boolean checkbox) {
        updateEmailIncludeProfile(Id, checkbox);
    }

    private void openIncludeContactList() {
        contactDilog = new Dialog(context);
        contactDilog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        contactDilog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        contactDilog.setContentView(R.layout.dailog_contact);
        TextView textView = contactDilog.findViewById(R.id.textView3);
        rv_contact = contactDilog.findViewById(R.id.rv_contact);
        textview_name = contactDilog.findViewById(R.id.textview_name);
        linearLayoutManager = new LinearLayoutManager(context);
        rv_contact.setLayoutManager(linearLayoutManager);
        rv_contact.setHasFixedSize(true);
        getIncludeContactData();
        textview_name.setText("Contact Number Add");

        add_new_data = contactDilog.findViewById(R.id.add_new_data);
        Button btn_apply = contactDilog.findViewById(R.id.btn_apply);
        Button btn_cancel = contactDilog.findViewById(R.id.btn_cancel);
        add_new_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactDilog.dismiss();
                layoutBusinessProfile.setVisibility(View.GONE);
                layoutPhoneCall.setVisibility(View.VISIBLE);
            }
        });

        btn_cancel.setOnClickListener(v -> contactDilog.dismiss());

        btn_apply.setOnClickListener(v -> {
            contactDilog.dismiss();
        });


        contactDilog.setCancelable(false);
        contactDilog.setCanceledOnTouchOutside(false);
        contactDilog.show();
    }

    private void getIncludeContactData() {
        showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_ProfileId",businessProfileId);
        Call<ResponseGetContact> call = apiServices.getContact(object);
        call.enqueue(new Callback<ResponseGetContact>() {
            @Override
            public void onResponse(Call<ResponseGetContact> call, Response<ResponseGetContact> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterContactInclude adapter = new AdapterContactInclude(response.body().getLstContact(), context, SetAction.this);
                    rv_contact.setAdapter(adapter);
                    rv_contact.setVisibility(View.VISIBLE);
                    add_new_data.setVisibility(View.GONE);
                    textview_name.setVisibility(View.GONE);
                    //  } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    //       recyclerviewContact.setVisibility(View.GONE);
                    //  }
                    //     else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    //      recyclerviewContact.setVisibility(View.VISIBLE);
                } else {
                    showMessage(response.body().getMessage());
                    rv_contact.setVisibility(View.GONE);
                    textview_name.setVisibility(View.VISIBLE);
                    add_new_data.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetContact> call, Throwable t) {
                hideLoading();
            }
        });
    }


    private void updateIncludeContact(String Id, boolean status) {
        // showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("IsIncluded", status);
        object.addProperty("Pk_NfcProfileId", Id);
        object.addProperty("PK_ProfileId",businessProfileId);
        // Toast.makeText(context, businessProfileId+"", Toast.LENGTH_SHORT).show();
        Call<ResponseStatusMessage> call = apiServices.includeEmailProfile(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                // hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getIncludeContactData();
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                //hideLoading();
            }
        });
    }

    @Override
    public void getcontactIncludeProfile(String Id, boolean chackbox) {
        updateIncludeContact(Id, chackbox);
    }

    //include email profile
    @Override
    public void getIncludeEmailProfile(String Id, Boolean checkbox) {
        updateEmailInclude(Id, checkbox);
    }

    //start Email Details For include profile

    private void openEmailIncludeDialog() {
        emailDialog = new Dialog(context);
        emailDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        emailDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        emailDialog.setContentView(R.layout.dailog_contact);
        add_new_data = emailDialog.findViewById(R.id.add_new_data);
        textview_name = emailDialog.findViewById(R.id.textview_name);
        textview_name.setText("Email Text Add");
        TextView textView = emailDialog.findViewById(R.id.textView3);
        textView.setText("Email");
        rv_contact = emailDialog.findViewById(R.id.rv_contact);
        linearLayoutManager = new LinearLayoutManager(context);
        rv_contact.setLayoutManager(linearLayoutManager);
        rv_contact.setHasFixedSize(true);
        getOpenEmailIncludeprofile();


        Button btn_apply = emailDialog.findViewById(R.id.btn_apply);
        Button btn_cancel = emailDialog.findViewById(R.id.btn_cancel);
        add_new_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailDialog.dismiss();
                layoutBusinessProfile.setVisibility(View.GONE);
                layoutEmail.setVisibility(View.VISIBLE);
            }
        });

        btn_cancel.setOnClickListener(v -> emailDialog.dismiss());

        btn_apply.setOnClickListener(v -> {
            emailDialog.dismiss();
        });


        emailDialog.setCancelable(false);
        emailDialog.setCanceledOnTouchOutside(false);
        emailDialog.show();
    }

    private void getOpenEmailIncludeprofile() {
        showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_ProfileId",businessProfileId);

        Call<ResponseGetEmail> call = apiServices.getEmail(object);
        call.enqueue(new Callback<ResponseGetEmail>() {
            @Override
            public void onResponse(Call<ResponseGetEmail> call, Response<ResponseGetEmail> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterIncludeEmail adapter = new AdapterIncludeEmail(response.body().getLstEmail(), context, SetAction.this);
                    rv_contact.setAdapter(adapter);
                    rv_contact.setVisibility(View.VISIBLE);
                    add_new_data.setVisibility(View.GONE);
                    textview_name.setVisibility(View.GONE);
                    //  } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    //       recyclerviewContact.setVisibility(View.GONE);
                    //  }
                    //     else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    //      recyclerviewContact.setVisibility(View.VISIBLE);
                } else {
                    showMessage(response.body().getMessage());
                    rv_contact.setVisibility(View.GONE);
                    add_new_data.setVisibility(View.VISIBLE);
                    textview_name.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetEmail> call, Throwable t) {
                hideLoading();
            }
        });
    }


    private void updateEmailInclude(String Id, boolean status) {
        // showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("IsIncluded", status);
        object.addProperty("Pk_NfcProfileId", Id);
        object.addProperty("PK_ProfileId",businessProfileId);


        Call<ResponseStatusMessage> call = apiServices.includeEmailProfile(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                // hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getOpenEmailIncludeprofile();
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                //hideLoading();
            }
        });
    }
//End Email Include Profile Section


    //Start WebLink Include Profile Section
    @Override
    public void getIncludeWeb(String Id, Boolean checkbox) {
        updateWebIncludeProfile(Id, checkbox);
    }

    private void openWeblinkDialog() {
        WeblinkDialog = new Dialog(context);
        WeblinkDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WeblinkDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        WeblinkDialog.setContentView(R.layout.dailog_contact);
        TextView textView = WeblinkDialog.findViewById(R.id.textView3);
        add_new_data = WeblinkDialog.findViewById(R.id.add_new_data);
        textview_name = WeblinkDialog.findViewById(R.id.textview_name);
        textView.setText("Web Link Add");
        textView.setText("Web");
        rv_contact = WeblinkDialog.findViewById(R.id.rv_contact);
        linearLayoutManager = new LinearLayoutManager(context);
        rv_contact.setLayoutManager(linearLayoutManager);
        rv_contact.setHasFixedSize(true);
        getOpenWeblinkInclude();

        Button btn_apply = WeblinkDialog.findViewById(R.id.btn_apply);
        Button btn_cancel = WeblinkDialog.findViewById(R.id.btn_cancel);

        add_new_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WeblinkDialog.dismiss();
                layoutBusinessProfile.setVisibility(View.GONE);
                layoutWeb.setVisibility(View.VISIBLE);
            }
        });
        btn_cancel.setOnClickListener(v -> WeblinkDialog.dismiss());

        btn_apply.setOnClickListener(v -> {
            WeblinkDialog.dismiss();
        });


        WeblinkDialog.setCancelable(false);
        WeblinkDialog.setCanceledOnTouchOutside(false);
        WeblinkDialog.show();
    }

    private void getOpenWeblinkInclude() {
        // showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_ProfileId",businessProfileId);

        Call<ResponseWebLink> call = apiServices.getWebLink(object);
        call.enqueue(new Callback<ResponseWebLink>() {
            @Override
            public void onResponse(Call<ResponseWebLink> call, Response<ResponseWebLink> response) {
                //  hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterIncludeWeb adapter = new AdapterIncludeWeb(response.body().getLstWeb(), context, SetAction.this,SetAction.this);
                    rv_contact.setAdapter(adapter);
                    rv_contact.setVisibility(View.VISIBLE);
                    layoutWeb.setVisibility(View.GONE);
                    add_new_data.setVisibility(View.GONE);
                    textview_name.setVisibility(View.GONE);

                    //  } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    //       recyclerviewContact.setVisibility(View.GONE);
                    //  }
                    //     else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    //      recyclerviewContact.setVisibility(View.VISIBLE);
                } else {
                    showMessage(response.body().getMessage());
                    rv_contact.setVisibility(View.GONE);
                    layoutWeb.setVisibility(View.VISIBLE);
                    textview_name.setVisibility(View.VISIBLE);
                    add_new_data.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<ResponseWebLink> call, Throwable t) {
                // hideLoading();
            }
        });
    }


    private void updateWebIncludeProfile(String Id, boolean status) {
        // showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("IsIncluded", status);
        object.addProperty("Pk_NfcProfileId", Id);
        object.addProperty("PK_ProfileId",businessProfileId);

        Call<ResponseStatusMessage> call = apiServices.includeEmailProfile(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                // hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getOpenWeblinkInclude();
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                //hideLoading();
            }
        });
    }
//End Web Include profile Section

    //start Social Media Include Profile Section
    @Override
    public void getIncludeMedia(String Id, Boolean checkbox) {
        updatesocialMediaInclude(Id, checkbox);

    }

    private void openSocialMediaDialog() {
        socialMediaDialog = new Dialog(context);
        socialMediaDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        socialMediaDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        socialMediaDialog.setContentView(R.layout.dailog_contact);
        TextView textView = socialMediaDialog.findViewById(R.id.textView3);
        add_new_data = socialMediaDialog.findViewById(R.id.add_new_data);
        textview_name = socialMediaDialog.findViewById(R.id.textview_name);
        textview_name.setText("Social Media link Add");
        textView.setText("Social Media");
        rv_contact = socialMediaDialog.findViewById(R.id.rv_contact);
        linearLayoutManager = new LinearLayoutManager(context);
        rv_contact.setLayoutManager(linearLayoutManager);
        rv_contact.setHasFixedSize(true);
        getOpenSocialMediaInclude();
        Button btn_apply = socialMediaDialog.findViewById(R.id.btn_apply);
        Button btn_cancel = socialMediaDialog.findViewById(R.id.btn_cancel);

        add_new_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                socialMediaDialog.dismiss();
                layoutBusinessProfile.setVisibility(View.GONE);
                layoutreferral.setVisibility(View.VISIBLE);
            }
        });
        btn_cancel.setOnClickListener(v -> socialMediaDialog.dismiss());

        btn_apply.setOnClickListener(v -> {
            socialMediaDialog.dismiss();
        });


        socialMediaDialog.setCancelable(false);
        socialMediaDialog.setCanceledOnTouchOutside(false);
        socialMediaDialog.show();
    }

    private void getOpenSocialMediaInclude() {
        showLoading();

        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_ProfileId",businessProfileId);

        Call<ResponseSocialMediaLink> call = apiServices.getSocialMeida(object);
        call.enqueue(new Callback<ResponseSocialMediaLink>() {
            @Override
            public void onResponse(Call<ResponseSocialMediaLink> call, Response<ResponseSocialMediaLink> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterIncludeSocialMedia adapter = new AdapterIncludeSocialMedia(response.body().getLstEmail(), context, SetAction.this,SetAction.this);
                    rv_contact.setAdapter(adapter);
                    rv_contact.setVisibility(View.VISIBLE);
                    add_new_data.setVisibility(View.GONE);
                    textview_name.setVisibility(View.GONE);

                    //  } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    //       recyclerviewContact.setVisibility(View.GONE);
                    //  }
                    //     else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    //      recyclerviewContact.setVisibility(View.VISIBLE);
                } else {
                    showMessage(response.body().getMessage());
                    rv_contact.setVisibility(View.GONE);
                    add_new_data.setVisibility(View.VISIBLE);
                    textview_name.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<ResponseSocialMediaLink> call, Throwable t) {
                hideLoading();
            }
        });
    }


    private void updatesocialMediaInclude(String Id, boolean status) {
        // showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("IsIncluded", status);
        object.addProperty("Pk_NfcProfileId", Id);
        object.addProperty("PK_ProfileId",businessProfileId);


        Call<ResponseStatusMessage> call = apiServices.includeEmailProfile(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                // hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getOpenSocialMediaInclude();
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                //hideLoading();
            }
        });
    }

    //Start Save Email Data Section

    private void getEmailListData() {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_ProfileId","");

        Call<ResponseGetEmail> call = apiServices.getEmail(object);
        call.enqueue(new Callback<ResponseGetEmail>() {
            @Override
            public void onResponse(Call<ResponseGetEmail> call, Response<ResponseGetEmail> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterEmail adapter = new AdapterEmail(response.body().getLstEmail(), context, SetAction.this);
                    recEmail.setAdapter(adapter);
                    // showMessage(response.body().getMessage());
                    recEmail.setVisibility(View.VISIBLE);

                } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    recEmail.setVisibility(View.GONE);
                } else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    recEmail.setVisibility(View.VISIBLE);
                } else {
                    showMessage(response.body().getMessage());
                    recEmail.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetEmail> call, Throwable t) {
                hideLoading();
            }
        });
    }


    @Override
    public void getEmailDeleatId(String pkNfcProfileId) {
        RemoveEmailData(pkNfcProfileId);
    }

    private void SaveEmailData() {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Email", etEmail.getText().toString().trim());

        Call<ResponseNfcSaveData> call = apiServices.getNFCEmail(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    etEmail.getText().clear();
                    chakboxEmail.setChecked(false);
                    getEmailListData();

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

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Pk_NfcProfileId", Id);

        Call<ResponseNfcSaveData> call = apiServices.DeletNfcData(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getEmailListData();

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                hideLoading();
            }
        });
    }
    //End Email Section

    //Start Weblink Section

    private void getWebLinkData() {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_ProfileId","");
        Call<ResponseWebLink> call = apiServices.getWebLink(object);
        call.enqueue(new Callback<ResponseWebLink>() {
            @Override
            public void onResponse(Call<ResponseWebLink> call, Response<ResponseWebLink> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterWebLink adapter = new AdapterWebLink(response.body().getLstWeb(), context, SetAction.this,SetAction.this);

                    recWeblink.setAdapter(adapter);
                    adapter.notifyDataSetChanged();


                } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    recWeblink.setVisibility(View.GONE);
                } else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    recWeblink.setVisibility(View.VISIBLE);
                } else {
                    showMessage(response.body().getMessage());
                    recWeblink.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ResponseWebLink> call, Throwable t) {
                hideLoading();
            }
        });
    }

    @Override
    public void getWebDelate(String pkNfcProfileId) {
        RemoveWebLinkData(pkNfcProfileId);


    }


    private void SaveWebLinkData() {
        // showLoading();
        String linkweb = weblink + etWebsite.getText().toString().trim();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("WebLink", linkweb);
        // object.addProperty("IsRedirect", IsRedirectWeb);
        object.addProperty("IsRedirect", IsRedirectWeb);
        Call<ResponseNfcSaveData> call = apiServices.getNfcWebLink(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                //hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getWebLinkData();
                    etWebsite.getText().clear();
                    chakboxWeblink.setChecked(false);

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

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
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

    //Start Leg

    private void updateLeg() {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Leg", leg);


        Call<ResponseStatusMessage> call = apiServices.updateLeg(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }

    //Start Social Media Section
    private void SaveSocialMediaData() {
        //showLoading();
        String linksocial = sociallink + etSocialMedia.getText().toString().trim();
        // Toast.makeText(context,linksocial+ "", Toast.LENGTH_SHORT).show();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("SocialMedia", linksocial);
        object.addProperty("IsRedirect", IsRediractSocial);
        object.addProperty("IsInclude", socialIncludeProfile);
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
                    checkBoxSocial.setChecked(false);
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

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
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


    @Override
    public void getSocialDelate(String pkNfcProfileId) {
        Bundle bundle = new Bundle();
        bundle.putString("Pk_NfcProfileId", pkNfcProfileId);


        //getSocialMediaData();

        RemoveSocialMediaData(pkNfcProfileId);

    }

    private void getSocialMediaData() {
        // showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_ProfileId","");

        Call<ResponseSocialMediaLink> call = apiServices.getSocialMeida(object);
        call.enqueue(new Callback<ResponseSocialMediaLink>() {
            @Override
            public void onResponse(Call<ResponseSocialMediaLink> call, Response<ResponseSocialMediaLink> response) {
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    getSocialMediaData();
                    AdapterSocialMedia adapter = new AdapterSocialMedia(response.body().getLstEmail(), context, SetAction.this,SetAction.this);
                    recSocial.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    //  recyclerviewSocial.setVisibility(View.VISIBLE);

                } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    recSocial.setVisibility(View.GONE);
                } else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    recSocial.setVisibility(View.VISIBLE);
                } else {
                    showMessage(response.body().getMessage());
                    recSocial.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseSocialMediaLink> call, Throwable t) {
                //hideLoading();
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
                    cvMyProfile.setVisibility(View.GONE);
                    cvCall.setVisibility(View.GONE);
                    cvBusinessProfile.setVisibility(View.GONE);
                    cvEmail.setVisibility(View.GONE);
                    cvWeblink.setVisibility(View.GONE);
                    cv_social.setVisibility(View.GONE);
                    rvList.setVisibility(View.GONE);
                    cvExchangeAnalytic.setVisibility(View.GONE);
                    getStatusNfc();
                } else {
                    // cardView1.setVisibility(View.GONE);
                    carviewScanner.setVisibility(View.GONE);
                    cardView1.setVisibility(View.VISIBLE);
                    cvMyProfile.setVisibility(View.VISIBLE);
                    cvCall.setVisibility(View.VISIBLE);
                    cvBusinessProfile.setVisibility(View.VISIBLE);
                    cvEmail.setVisibility(View.VISIBLE);
                    cvWeblink.setVisibility(View.VISIBLE);
                    cv_social.setVisibility(View.VISIBLE);
                    rvList.setVisibility(View.VISIBLE);
                    cvExchangeAnalytic.setVisibility(View.VISIBLE);
                    tvListName.setVisibility(View.VISIBLE);
                    //  hideLoading();

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
                hideLoading();
            }
        });

    }

    public void getAnalytics() {
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
                    // hideLoading();
                    // showMessage(response.body().getMessage());

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseExchangeAnalytics> call, Throwable t) {
                // hideLoading();
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

    @Override
    public void webcheckbox(String Id, Boolean checkbox) {
        updateWebIsRediract(Id,checkbox);
    }
    public void updateWebIsRediract(String Id,boolean tfid)
    {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_NFCProfileId", Id);
        object.addProperty("IsRedirect", tfid);

        Call<ResponseStatusMessage> call = apiServices.updatecheckbox(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getWebLinkData();

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }

    public void updateIsRediractSocial(String Id,boolean tfid)
    {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_NFCProfileId", Id);
        object.addProperty("IsRedirect", tfid);

        Call<ResponseStatusMessage> call = apiServices.updatecheckbox(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getSocialMediaData();
                    //getOpenSocialMediaInclude();

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }

    @Override
    public void getSocialIsRedirect(String Id, Boolean checkbox) {
        updateIsRediractSocial(Id,checkbox);
    }

    @Override
    public void redirectSocial(String Id, Boolean checkbox) {
        IsRedirectSocial(Id,checkbox);
    }


    @Override
    public void redirectWeb(String Id, Boolean checkbox) {
        WebRedirct(Id,checkbox);    }

    public void IsRedirectSocial(String Id,boolean tfid)
    {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_NFCProfileId", Id);
        object.addProperty("IsRedirect", tfid);

        Call<ResponseStatusMessage> call = apiServices.updatecheckbox(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    // getSocialMediaData();
                    getOpenSocialMediaInclude();

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }
    public void WebRedirct(String Id,boolean tfid)
    {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_NFCProfileId", Id);
        object.addProperty("IsRedirect", tfid);

        Call<ResponseStatusMessage> call = apiServices.updatecheckbox(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    // getWebLinkData();
                    getOpenWeblinkInclude();
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }

    @Override
    public void getbusinessId(String Id, String pk_userid, String profileName) {
        Bundle  bundle=new Bundle();
        bundle.putString("Pk_profileId",Id);
        businessProfileId=Id;
        //   goToActivity(SetAction.class,bundle);

        //  Toast.makeText(context,pk_profileid+"", Toast.LENGTH_SHORT).show();
        if (layoutProfileSave.getVisibility() == View.GONE) {
            layoutProfileSave.setVisibility(View.VISIBLE);

            btnBusinessUpdate.setVisibility(View.VISIBLE);
            btnBusinessSave.setVisibility(View.GONE);

            layoutProfileName.setVisibility(View.GONE);
            layoutbtn.setVisibility(View.VISIBLE);

            getBusinessProfileEdit(Id);
            btnBusinessUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    upadetBusinessProfile(Id, pk_userid);
                }
            });
        } else {
            layoutProfileSave.setVisibility(View.GONE);


        }


    }
}
