package com.urdost.Fragment;

import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.urdost.Activity.NewContainerActivity;
import com.urdost.Adapter.AdapterBusinessProfileLst;
import com.urdost.Adapter.AdapterContact;
import com.urdost.Adapter.AdapterEmail;
import com.urdost.Adapter.AdapterSocialMedia;
import com.urdost.Adapter.AdapterWebLink;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseFragment;
import com.urdost.interfaces.BusinessProfileId;
import com.urdost.interfaces.Chackboxbusinessprofile;
import com.urdost.interfaces.DeleteContactItem;
import com.urdost.interfaces.NFCWebDelate;
import com.urdost.interfaces.NfcEmailDelate;
import com.urdost.interfaces.NfcSocialDelate;
import com.urdost.interfaces.RediractCheckbox;
import com.urdost.interfaces.UpdateEmailIncludeProfile;
import com.urdost.interfaces.WebIsDiract;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.response.BusinessProfile.ResponseBusinessProfile;
import com.urdost.model.response.GeSocialMediaLink.ResponseSocialMediaLink;
import com.urdost.model.response.NFcContact.ResponseGetContact;
import com.urdost.model.response.GetEmail.ResponseGetEmail;
import com.urdost.model.response.ResponseGetEditBusinessProfile;
import com.urdost.model.response.ResponseNfcSaveData;
import com.urdost.model.response.WebLink.ResponseWebLink;
import com.urdost.retrofit.CheckMobile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NfcSetAction extends BaseFragment implements BusinessProfileId, Chackboxbusinessprofile , NfcEmailDelate , UpdateEmailIncludeProfile, DeleteContactItem, CheckMobile , NFCWebDelate, WebIsDiract , NfcSocialDelate, RediractCheckbox {
    Unbinder unbinder;

    @BindView(R.id.card_view1)
    CardView cardView1;
    @BindView(R.id.tv_bussiness)
    TextView tvBussiness;
    @BindView(R.id.tv_add_new_business)
    TextView tvAddNewBusiness;
    @BindView(R.id.lst_business_profile)
    RecyclerView lstBusinessProfile;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.img_member)
    ImageView imgMember;
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
    @BindView(R.id.btn_business_update)
    Button btnBusinessUpdate;
    @BindView(R.id.base_cardview)
    CardView baseCardview;
    @BindView(R.id.Right)
    RadioButton Right;
    @BindView(R.id.left)
    RadioButton left;
    @BindView(R.id.re_leg)
    RadioGroup rgLeg;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.container1)
    ConstraintLayout container1;
    @BindView(R.id.container2)
    LinearLayout container2;
    @BindView(R.id.base_cardview1)
    CardView baseCardview1;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_contact)
    EditText etContact;
    @BindView(R.id.chakboxMobile)
    CheckBox chakboxMobile;
    @BindView(R.id.mob_including_prifile)
    CheckBox mobIncludingPrifile;
    @BindView(R.id.bt_save_contact)
    Button btnContactSave;
    @BindView(R.id.recyclerview_contact)
    RecyclerView recyclerviewContact;
    @BindView(R.id.linear3)
    LinearLayout linear3;
    @BindView(R.id.base_cardview5)
    CardView baseCardview5;
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
    @BindView(R.id.tv_Web_link)
    TextView tvWebLink;
    @BindView(R.id.text_weblink)
    TextView textWeblink;
    @BindView(R.id.et_website)
    EditText etWebsite;
    @BindView(R.id.checkBoxweblink)
    CheckBox checkBoxweblink;
    @BindView(R.id.web_include_profile)
    CheckBox webIncludeProfile;
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
    @BindView(R.id.tv_social_link)
    TextView tvSocialLink;
    @BindView(R.id.text_social)
    TextView textSocial;
    @BindView(R.id.et_social_media)
    EditText etSocialMedia;
    @BindView(R.id.checkBox_social)
    CheckBox checkBoxSocial;
    @BindView(R.id.check_social_include_profile)
    CheckBox checkSocialIncludeProfile;
    @BindView(R.id.bt_update_social)
    Button btUpdateSocial;
    @BindView(R.id.recyclerview_social)
    RecyclerView recyclerviewSocial;
    @BindView(R.id.linear6)
    LinearLayout linear6;
    @BindView(R.id.base_cardview4)
    CardView baseCardview4;
    @BindView(R.id.linear_layout1)
    LinearLayout linearLayout1;
    @BindView(R.id.container)
    ConstraintLayout container;
    @BindView(R.id.chack_email_status)
    CheckBox checkBoxemail;
    @BindView(R.id.layout_business_profile)
            ConstraintLayout layoutBusinessprofile;
    private String sociallink = "", weblink = "";
    String IsRedirectWeb = "", webincludeprofile=" ";
    String IsRediractSocial = "", socialIncludeProfile=" ";

    RecyclerView.LayoutManager businesslayotmanagr,layoutManageremail,layoutManagerContact,layoutManagerWeblink
            ,linerLayoutSocialMedia;
  Bundle bundle;
    String leg = "L";
    String includeEmailProfile="",mobilechackbox="",includeMobile="";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_nfc_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        businesslayotmanagr=new LinearLayoutManager(context);
        layoutManageremail=new LinearLayoutManager(context);
        layoutManagerContact=new LinearLayoutManager(context);
        layoutManagerWeblink=new LinearLayoutManager(context);
        linerLayoutSocialMedia=new LinearLayoutManager(context);

        recyclerviewSocial.setLayoutManager(linerLayoutSocialMedia);
        recyclerviewSocial.setHasFixedSize(true);
        lstBusinessProfile.setLayoutManager(businesslayotmanagr);
        lstBusinessProfile.setHasFixedSize(true);
        recyclerviewemail.setLayoutManager(layoutManageremail);
        recyclerviewemail.setHasFixedSize(true);

        recyclerviewContact.setLayoutManager(layoutManagerContact);
        recyclerviewContact.setHasFixedSize(true);
        recyclerviewWebLink.setLayoutManager(layoutManagerWeblink);
        recyclerviewWebLink.setHasFixedSize(true);

        // bundle = getArguments().get(PAYLOAD_BUNDLE);
        //getBusinessProfile();
      // getEmailData();

        btUpdateSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSocialMediaData();
            }
        });
        webIncludeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webIncludeProfile.isChecked()) {
                    webincludeprofile = "true";
                } else {
                    webincludeprofile = "false";
                }

            }
        });
        checkSocialIncludeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSocialIncludeProfile.isChecked()) {
                    socialIncludeProfile = "true";
                } else {
                    socialIncludeProfile = "false";
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
                        sociallink = "https://m.youtube.com/channel/";
                    tvSocialLink.setText(item.getTitle());
                    return true;
                });
                popup_sidemenu.show();
            }
        });

        tvAddNewBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layoutBusinessprofile.getVisibility() == View.GONE) {
                    etBusinessName.getText().clear();
                    etName.getText().clear();
                    etSirName.getText().clear();
                    etDegination.getText().clear();
                    etDiscription.getText().clear();
                    layoutBusinessprofile.setVisibility(View.VISIBLE);
                    btnBusinessSave.setVisibility(View.VISIBLE);
                    btnUpdate.setVisibility(View.GONE);
                } else layoutBusinessprofile.setVisibility(View.GONE);
                btnUpdate.setVisibility(View.GONE);

            }
        });
        rgLeg.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.left)
                leg = "L";
            else leg = "R";
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLeg();
            }
        });

        btnContactSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveContactData();
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

        mobIncludingPrifile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mobIncludingPrifile.isChecked()) {
                    includeMobile = "true";

                } else {
                    includeMobile = "false";
                }

            }
        });
        //Chackbox for Email
        checkBoxemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxSocial.isChecked()) {
                    includeEmailProfile = "true";

                } else {
                    includeEmailProfile = "false";
                }

            }
        });
        btUpdateWebLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveWebLinkData();
            }
        });
        btUpdateemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveEmailData();
            }
        });
        return view;
    }


    @OnClick({R.id.card_view1, R.id.img, R.id.img_member, R.id.btn_business_save, R.id.base_cardview, R.id.btn_update,R.id.base_cardview1,  R.id.base_cardview5, R.id.base_cardview2, R.id.bt_update_web_link, R.id.base_cardview3, R.id.bt_update_social, R.id.base_cardview4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_view1:
                break;
            case R.id.img:
                break;
            case R.id.img_member:
                break;
            case R.id.btn_business_save:
                saveBusinessProfileData();
               // btnBusinessUpdate.setVisibility(View.GONE);
              //  btnBusinessSave.setVisibility(View.VISIBLE);


                break;
            case R.id.base_cardview:
                if (container1.getVisibility() == View.GONE) {
                    container1.setVisibility(View.VISIBLE);
                    container2.setVisibility(View.GONE);
                    getBusinessProfile();
                }else container1.setVisibility(View.GONE);

                break;
            case R.id.base_cardview1:
                if (container2.getVisibility() == View.GONE) {
                    container1.setVisibility(View.GONE);
                    container2.setVisibility(View.VISIBLE);
                }else container2.setVisibility(View.GONE);
                break;
            case R.id.bt_update:
                updateLeg();
                break;
            case R.id.base_cardview5:
                if (linear3.getVisibility()==View.GONE)
                {
                    linear3.setVisibility(View.VISIBLE);
                    linear4.setVisibility(View.GONE);
                    container1.setVisibility(View.GONE);
                    container2.setVisibility(View.GONE);
                    getContactData();
                }else linear3.setVisibility(View.GONE);

                break;
            case R.id.base_cardview2:
                if (linear4.getVisibility() == View.GONE) {
                    linear4.setVisibility(View.VISIBLE);
                    container1.setVisibility(View.GONE);
                    container2.setVisibility(View.GONE);
                    getEmailData();
                }else linear4.setVisibility(View.GONE);
                break;
            case R.id.bt_update_web_link:
                break;
            case R.id.base_cardview3:
                if (linear5.getVisibility() == View.GONE) {
                    linear5.setVisibility(View.VISIBLE);
                    container1.setVisibility(View.GONE);
                    container2.setVisibility(View.GONE);
                    getWebLinkData();
                    linear3.setVisibility(View.GONE);
                }else linear5.setVisibility(View.GONE);
                break;
            case R.id.bt_update_social:

                break;
            case R.id.base_cardview4:
                if (linear6.getVisibility() == View.GONE) {
                    linear6.setVisibility(View.VISIBLE);
                    container1.setVisibility(View.GONE);
                    linear5.setVisibility(View.GONE);

                    container2.setVisibility(View.GONE);
getSocialMediaData();                    linear3.setVisibility(View.GONE);
                }else linear6.setVisibility(View.GONE);
                break;
        }
    }
    private void getBusinessProfile() {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        Call<ResponseBusinessProfile> call = apiServices.getBusinessProfileList(object);
        call.enqueue(new Callback<ResponseBusinessProfile>() {
            @Override
            public void onResponse(Call<ResponseBusinessProfile> call, Response<ResponseBusinessProfile> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {

                    AdapterBusinessProfileLst adapter = new AdapterBusinessProfileLst(response.body().getLst(),context, NfcSetAction.this,NfcSetAction.this);
                    lstBusinessProfile.setAdapter(adapter);
                    //Toast.makeText(context,response+ "", Toast.LENGTH_SHORT).show();
                    showMessage(response.body().getMessage());
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseBusinessProfile> call, Throwable t) {
                hideLoading();
            }
        });
    }
    private void saveBusinessProfileData() {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("FirstName",etName.getText().toString().trim());
        object.addProperty("LastName",etSirName.getText().toString().trim());
        object.addProperty("Designation",etDegination.getText().toString().trim());
        object.addProperty("BusinessName",etBusinessName.getText().toString().trim());
        object.addProperty("Description",etDiscription.getText().toString().trim());
        Call<ResponseStatusMessage> call = apiServices.saveBusinessProfileData(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    etName.getText().clear();
                    etSirName.getText().clear();
                    etBusinessName.getText().clear();
                    etDegination.getText().clear();
                    etDiscription.getText().clear();
                    getBusinessProfile();
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
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
                    showMessage(response.body().getMessage());
                    etName.setText(response.body().getFirstName());
                    etSirName.setText(response.body().getLastName());
                    etBusinessName.setText(response.body().getBusinessName());
                    etDegination.setText(response.body().getDesignation());
                    etDiscription.setText(response.body().getDescription());

                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseGetEditBusinessProfile> call, Throwable t) {
                hideLoading();
            }
        });
    }



    private void upadetBusinessProfile(String Id) {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
       // object.addProperty("FirstName",etName.getText().toString().trim());
      //  object.addProperty("LastName",etSirName.getText().toString().trim());
        object.addProperty("PK_ProfileId",Id);
        object.addProperty("Designation",etDegination.getText().toString().trim());
        object.addProperty("BusinessName",etBusinessName.getText().toString().trim());
        object.addProperty("Description",etDiscription.getText().toString().trim());
        Call<ResponseStatusMessage> call = apiServices.updateBusinessProfile(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    etName.setKeyListener(null);
                    etSirName.setKeyListener(null);
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }
    private void updateBusinessProfileChackbox(String Id ,boolean status ) {
       // showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("IsActive", status);
        object.addProperty("PK_ProfileId",Id);

        Call<ResponseStatusMessage> call = apiServices.updateBusinessProfileStatus(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                   getBusinessProfile();
                 //  getEmailData();
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }

    @Override
    public void getBusinessprofilecheckbox(String Id, Boolean checkbox) {
       updateBusinessProfileChackbox(Id,checkbox);


    }
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

    //Start Email  Section
    private void getEmailData() {
         showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        Call<ResponseGetEmail> call = apiServices.getEmail(object);
        call.enqueue(new Callback<ResponseGetEmail>() {
            @Override
            public void onResponse(Call<ResponseGetEmail> call, Response<ResponseGetEmail> response) {
                  hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                   // AdapterEmail adapter = new AdapterEmail(response.body().getLstEmail(), context, //NfcSetAction.this,NfcSetAction.this);
                  //  recyclerviewemail.setAdapter(adapter);
                   // showMessage(response.body().getMessage());
                    recyclerviewemail.setVisibility(View.VISIBLE);

                } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    recyclerviewemail.setVisibility(View.GONE);
                }
                else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    recyclerviewemail.setVisibility(View.VISIBLE);
                }

                else {
                    showMessage(response.body().getMessage());
                    recyclerviewemail.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetEmail> call, Throwable t) {
                  hideLoading();
            }
        });
    }

    private void SaveEmailData() {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("Email", etEmail.getText().toString().trim());
        object.addProperty("IsInclude", includeEmailProfile);
        Call<ResponseNfcSaveData> call = apiServices.getNFCEmail(object);
        call.enqueue(new Callback<ResponseNfcSaveData>() {
            @Override
            public void onResponse(Call<ResponseNfcSaveData> call, Response<ResponseNfcSaveData> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    etEmail.getText().clear();
                    checkBoxemail.setChecked(false);
                    getEmailData();

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                hideLoading();
            }
        });
    }


    @Override
    public void getEmailDeleatId(String pkNfcProfileId) {
      RemoveEmailData(pkNfcProfileId);
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
                     getEmailData();

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseNfcSaveData> call, Throwable t) {
                hideLoading();
            }
        });
    }
    //End Email Section

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
    public void chackEmailProfile(String Id, Boolean checkbox) {
updateEmailIncludeProfile(Id,checkbox);
    }

    private void updateEmailIncludeProfile(String Id ,boolean status ) {
       // showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("IsIncluded", status);
        object.addProperty("Pk_NfcProfileId",Id);

        Call<ResponseStatusMessage> call = apiServices.includeEmailProfile(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
               // hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    getEmailData();
                } else showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                //hideLoading();
            }
        });
    }
    //End Email Section


    //Start Contact Section

    private void getContactData()
    {
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
                    AdapterContact adapter = new AdapterContact(response.body().getLstContact(), context,NfcSetAction.this,NfcSetAction.this);
                    recyclerviewContact.setAdapter(adapter);
                    recyclerviewContact.setVisibility(View.VISIBLE);

                } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    recyclerviewContact.setVisibility(View.GONE);
                }
                else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    recyclerviewContact.setVisibility(View.VISIBLE);
                }

                else {
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
    private void updatecontactChekbox(String Id ,boolean tfid ) {
        showLoading();
        JsonObject object = new JsonObject();

       object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_NFCProfileId", Id);
        object.addProperty("IsWhatsapp",tfid);

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
        object.addProperty("ContactNo", etContact.getText().toString().trim());
        object.addProperty("IsWhatsapp", includeMobile);
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
        updatecontactChekbox(Id,checkbox);

    }

    //Start WebLink

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
                   // AdapterWebLink adapter = new AdapterWebLink(response.body().getLstWeb(), context,//NfcSetAction.this,NfcSetAction.this);

                   // recyclerviewWebLink.setAdapter(adapter);
                  //  adapter.notifyDataSetChanged();



                } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    recyclerviewWebLink.setVisibility(View.GONE);
                }
                else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    recyclerviewWebLink.setVisibility(View.VISIBLE);
                }

                else {
                    showMessage(response.body().getMessage());
                    recyclerviewWebLink.setVisibility(View.GONE);
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

    @Override
    public void webcheckbox(String Id, Boolean checkbox) {

    }
    private void SaveWebLinkData() {
        // showLoading();
        String linkweb = weblink + etWebsite.getText().toString().trim();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("WebLink", linkweb);
        object.addProperty("IsRedirect", IsRedirectWeb);
        object.addProperty("IsInclude", webincludeprofile);
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
                    checkBoxweblink.setChecked(false);
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
    private void SaveSocialMediaData() {
        //showLoading();
        String linksocial = sociallink + etSocialMedia.getText().toString().trim();
        // Toast.makeText(context,linksocial+ "", Toast.LENGTH_SHORT).show();
        JsonObject object = new JsonObject();

        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("SocialMedia", linksocial);
        object.addProperty("IsRedirect",IsRediractSocial);
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
        Call<ResponseSocialMediaLink> call = apiServices.getSocialMeida(object);
        call.enqueue(new Callback<ResponseSocialMediaLink>() {
            @Override
            public void onResponse(Call<ResponseSocialMediaLink> call, Response<ResponseSocialMediaLink> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    getSocialMediaData();
                   // AdapterSocialMedia adapter = new AdapterSocialMedia(response.body().getLstEmail(), context, NfcSetAction.this,NfcSetAction.this);
                   // recyclerviewSocial.setAdapter(adapter);
                  //  adapter.notifyDataSetChanged();
                    //  recyclerviewSocial.setVisibility(View.VISIBLE);

                } else if (response.body().getMessage().equalsIgnoreCase("Record Not Found")) {
                    recyclerviewSocial.setVisibility(View.GONE);
                }
                else if (response.body().getMessage().equalsIgnoreCase("Record  Found")) {
                    recyclerviewSocial.setVisibility(View.VISIBLE);
                }

                else {
                    showMessage(response.body().getMessage());
                    recyclerviewSocial.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseSocialMediaLink> call, Throwable t) {
                //hideLoading();
            }
        });
    }

    @Override
    public void rediractCheckbox(String Id, Boolean checkbox) {
        updateSocialCekbox(Id,checkbox);

    }
    private void updateSocialCekbox(String Id ,boolean tfid ) {
        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PK_NFCProfileId", Id);
        object.addProperty("IsWhatsapp",tfid);

        Call<ResponseStatusMessage> call = apiServices.deleteContact(object);
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

    @Override
    public void getbusinessId(String Id, String pk_userid) {

    }
}
