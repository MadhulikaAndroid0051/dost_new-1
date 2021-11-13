package com.urdost.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.urdost.Fragment.DistributorZone;
import com.urdost.Fragment.MainDashboard;
import com.urdost.Fragment.NFCEditProfile;
import com.urdost.Fragment.NfcSetAction;
import com.urdost.Fragment.NfcSetActionSection.SetAction;
import com.urdost.Fragment.NfcSetActionSection.SetActionNew;
import com.urdost.Fragment.Passbook;
import com.urdost.Fragment.Scanner;
import com.urdost.Fragment.TaskZone;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseActivity;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.response.ResponseCheckResallerStatus;
import com.urdost.model.response.ResponseGetSponseStatus;
import com.urdost.model.response.ResponseTerms;
import com.urdost.model.response.responseSponserDetails.ResponseSponserDetails;
import com.urdost.model.response.sponsorName.ResponseSponsor;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Setter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewContainerActivity extends BaseActivity {


    @BindView(R.id.img_side_menu)
    ImageView imgSideMenu;
    @BindView(R.id.et_search)
    AppCompatEditText etSearch;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.img_notification)
    ImageView imgNotification;
    @BindView(R.id.tv_notification)
    TextView tvNotification;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.cont)
    ConstraintLayout cont;
    @BindView(R.id.activity_main)
    CoordinatorLayout activityMain;

    private   Dialog joinProgramDialog, distributorDialog,NfcActivateDilog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_activity);
        ButterKnife.bind(this);



        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setItemIconTintList(null);

        //Toast.makeText(context, PreferencesManager.getInstance(context).getSponserId()+"", Toast.LENGTH_SHORT).show();

        replaceFragment(new MainDashboard(), "Hi, " + PreferencesManager.getInstance(context).getFullname());
        toolbarTitle.setVisibility(View.VISIBLE);
        etSearch.setVisibility(View.GONE);
        tvNotification.setVisibility(View.GONE);

        Glide.with(context).load(PreferencesManager.getInstance(context).getImage())
                .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.user_user)
                        .error(R.drawable.user_user))
                .into(imgSideMenu);
        imgSideMenu.setOnClickListener(v -> {
            PopupMenu popupDis = new PopupMenu(context, imgSideMenu);
            popupDis.getMenuInflater().inflate(R.menu.menu_profile, popupDis.getMenu());
            if (PreferencesManager.getInstance(context).getServicetype().equalsIgnoreCase("True")) {
                // popupDis.getMenu().getItem(1).setVisible(true);
            } else {
                //popupDis.getMenu().getItem(1).setVisible(false);
            }
            popupDis.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {

                    case R.id.tv_logout:
                        logoutDialog(context, LoginActivity.class);
                        break;
                }
                return true;
            });
            popupDis.show();
        });

    }

    public Fragment currentFragment;

    public void replaceFragment(Fragment setFragment, String title) {
        new Handler().postDelayed(() -> {
            currentFragment = setFragment;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, setFragment, title);
            toolbarTitle.setText(title);
            transaction.commitAllowingStateLoss();
        }, 200);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                if (!(currentFragment instanceof MainDashboard))
                    replaceFragment(new MainDashboard(),"Hii"+", " +PreferencesManager.getInstance(context).getFullname());

                return true;
            case R.id.navigation_business:
                bottomNavigationView.getMenu().setGroupCheckable(3, true, true);
                toolbarTitle.setVisibility(View.VISIBLE);
                JsonObject object = new JsonObject();
                object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
                LoggerUtil.logItem(object);

                Call<ResponseCheckResallerStatus> call = apiServices.getResallerStatus(object);
                call.enqueue(new Callback<ResponseCheckResallerStatus>() {
                    @Override
                    public void onResponse(Call<ResponseCheckResallerStatus> call, Response<ResponseCheckResallerStatus> response) {
                        LoggerUtil.logItem(response.body());
                        if (response.body().getIsAcceptanceTNC().equalsIgnoreCase("false")) {
                            GetTermsAndConditions();

                        }
                        else {
                            if (!(currentFragment instanceof ResallerZone))
                                replaceFragment(new ResallerZone(), "Hii "+", " +PreferencesManager.getInstance(context).getFullname());

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseCheckResallerStatus> call, Throwable t) {
                    }
                });


               /* if (PreferencesManager.getInstance(context).getServicetype().equalsIgnoreCase("True"))
                    if (!(currentFragment instanceof ResallerZone))
                        replaceFragment(new ResallerZone(), "RESALLERZONE");
                else GetTermsAndConditions();*/

                etSearch.setVisibility(View.GONE);
                return true;
            case R.id.navigation_passbook:
                bottomNavigationView.getMenu().setGroupCheckable(3, true, true);
                toolbarTitle.setVisibility(View.VISIBLE);
                if (!(currentFragment instanceof Passbook))
                    replaceFragment(new Passbook(), "Hii "+", " +PreferencesManager.getInstance(context).getFullname());
                toolbarTitle.setVisibility(View.VISIBLE);
                etSearch.setVisibility(View.GONE);

                return true;
            case R.id.navigation_order:
                bottomNavigationView.getMenu().setGroupCheckable(3, true, true);
                toolbarTitle.setVisibility(View.VISIBLE);
                //   if (PreferencesManager.getInstance(context).getDistrubuter().equalsIgnoreCase("True")) {
                if (!(currentFragment instanceof TaskZone))
                    replaceFragment(new TaskZone(), "Hii "+", " +PreferencesManager.getInstance(context).getFullname());
                // replaceFragment(new NfcSetAction(), "Hii "+", " +PreferencesManager.getInstance(context).getFullname());
                // }
                //   else {setDistributorDialog();}



                toolbarTitle.setVisibility(View.VISIBLE);
                etSearch.setVisibility(View.GONE);


                return true;
            case R.id.navigation_sai:
                bottomNavigationView.getMenu().setGroupCheckable(3, true, true);
                toolbarTitle.setVisibility(View.VISIBLE);
                toolbarTitle.setVisibility(View.VISIBLE);
                etSearch.setVisibility(View.GONE);
                /*JsonObject object1 = new JsonObject();
                object1.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
                LoggerUtil.logItem(object1);

                Call<GetNfcActicateStatus> call1 = apiServices.getNfcStatus(object1);
                call1.enqueue(new Callback<GetNfcActicateStatus>() {
                    @Override
                    public void onResponse(Call<GetNfcActicateStatus> call, Response<GetNfcActicateStatus> response) {
                        LoggerUtil.logItem(response.body());
                        if (response.body().getIsActivated().equalsIgnoreCase("false")) {
                            /*if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, 100);
                             else
                                goToActivity(context,Scanner.class, null);*/
                           /* if (!(currentFragment instanceof NFCEditProfile))
                                replaceFragment(new NFcCardActivate(), "Hii "+", " +PreferencesManager.getInstance(context).getFullname());


                        }*/
                // else {
                if (!(currentFragment instanceof SetActionNew))
                    replaceFragment(new SetActionNew(), "Hii "+", " +PreferencesManager.getInstance(context).getFullname());

                // }
                //   }

                // @Override
                //  public void onFailure(Call<GetNfcActicateStatus> call, Throwable t) {
                //  }
                //  });


                return true;
        }
        return false;
    };
    private void GetTermsAndConditions() {
        showLoading();
        Call<ResponseTerms> call = apiServices.GetTermsAndConditions();
        call.enqueue(new Callback<ResponseTerms>() {
            @Override
            public void onResponse(Call<ResponseTerms> call, Response<ResponseTerms> response) {
                hideLoading();
                joinProgramDialog(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseTerms> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private     TextInputLayout textinput_mobile;
    String leg = "L";
    private boolean isValidSponsor = false;

    void joinProgramDialog(String terms) {
        joinProgramDialog = new Dialog(context);
        joinProgramDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        joinProgramDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        joinProgramDialog.setContentView(R.layout.dialog_join_referral);

        Button btn_search = joinProgramDialog.findViewById(R.id.btn_search);
        Button btn_cancel = joinProgramDialog.findViewById(R.id.btn_cancel);

        textinput_mobile = joinProgramDialog.findViewById(R.id.textinput_mobile);
        TextView tv_terms = joinProgramDialog.findViewById(R.id.tv_terms);
        CheckBox checkBox = joinProgramDialog.findViewById(R.id.checkBox);
        CheckBox checkBox1=joinProgramDialog.findViewById(R.id.textview);
        //TextInputLayout textinput_mobile=joinProgramDialog.findViewById(R.id.textinput_mobile)
        EditText et_sponsor = joinProgramDialog.findViewById(R.id.et_sponsor);
        RadioGroup rg_leg = joinProgramDialog.findViewById(R.id.rg_leg);
        Button check_btn=joinProgramDialog.findViewById(R.id.click_btn);
        ConstraintLayout layout1=joinProgramDialog.findViewById(R.id.liner1);


        tv_terms.setText(Html.fromHtml(terms));
        btn_cancel.setOnClickListener(v -> joinProgramDialog.cancel());
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseGetSponseStatus> call = apiServices.getSponserData();
                call.enqueue(new Callback<ResponseGetSponseStatus>() {
                    @Override
                    public void onResponse(Call<ResponseGetSponseStatus> call, Response<ResponseGetSponseStatus> response) {
                        hideLoading();
                        LoggerUtil.logItem(response.body());
                        if (response.body().getStatusCode().equalsIgnoreCase("200"))

                        {
                            et_sponsor.setText(response.body().getSponsorCode());

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseGetSponseStatus> call, Throwable t) {
                        hideLoading();
                    }
                });
            }
        });



        if (PreferencesManager.getInstance(context).getSponserId().equalsIgnoreCase("")) {

            checkBox1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checkBox1.isChecked()) {
                        Call<ResponseGetSponseStatus> call = apiServices.getSponserData();
                        call.enqueue(new Callback<ResponseGetSponseStatus>() {
                            @Override
                            public void onResponse(Call<ResponseGetSponseStatus> call, Response<ResponseGetSponseStatus> response) {
                                hideLoading();
                                LoggerUtil.logItem(response.body());
                                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                                    et_sponsor.setText(response.body().getSponsorCode());

                                    et_sponsor.setVisibility(View.GONE);
                                    textinput_mobile.setVisibility(View.GONE);

                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseGetSponseStatus> call, Throwable t) {
                                hideLoading();
                            }
                        });
                    } else
                        et_sponsor.setVisibility(View.VISIBLE);
                    textinput_mobile.setVisibility(View.VISIBLE);
                    et_sponsor.getText().clear();


                }
            });
        }else {
            checkBox.setChecked(false);
            checkBox.setVisibility(View.VISIBLE);
            et_sponsor.setVisibility(View.VISIBLE);
            textinput_mobile.setVisibility(View.VISIBLE);
            et_sponsor.setText(PreferencesManager.getInstance(context).getSponserId());
        }

        rg_leg.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == R.id.rb_left)
                leg = "L";
            else leg = "R";
        });

        et_sponsor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (et_sponsor.getText().toString().trim().length() > 5) {
                    GetSponsor(et_sponsor.getText().toString().trim());
                }
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked())
                {
                    if (PreferencesManager.getInstance(context).getSponserId().equalsIgnoreCase("")) {
                        layout1.setVisibility(View.VISIBLE);
                        et_sponsor.getText().clear();

                        et_sponsor.setVisibility(View.VISIBLE);

                        textinput_mobile.setVisibility(View.GONE);

                    }
                    else {
                        layout1.setVisibility(View.VISIBLE);
                        et_sponsor.setText(PreferencesManager.getInstance(context).getSponserId());
                        et_sponsor.setEnabled(false);
                        et_sponsor.setVisibility(View.VISIBLE);
                        checkBox.setVisibility(View.GONE);




                    }
                }else layout1.setVisibility(View.GONE);


            }
        });


        btn_search.setOnClickListener(view -> {
            if (checkBox.isChecked()) {

                if (et_sponsor.getText().toString().length()>=6) {
                    JoinReferalProgram(et_sponsor.getText().toString());
                } else showMessage("Enter valid sponsor code.");
            } else showMessage("Accept terms & conditions.");
        });
        // checkBox1.setChecked(checkBox.isChecked());
        joinProgramDialog.setCancelable(false);
        joinProgramDialog.setCanceledOnTouchOutside(false);
        joinProgramDialog.show();
    }

    String pkUserId = "";

    private void GetSponsor(String id) {
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", id);
        LoggerUtil.logItem(object);

        Call<ResponseSponsor> call = apiServices.GetSponsor(object);
        call.enqueue(new Callback<ResponseSponsor>() {
            @Override
            public void onResponse(Call<ResponseSponsor> call, Response<ResponseSponsor> response) {
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode() == 200) {
                    isValidSponsor = true;
                    pkUserId = response.body().getData().getPK_UserId();
                    textinput_mobile.setHelperText(response.body().getData().getSponsorName());
                    hideKeyboard();
                } else {
                    isValidSponsor = false;
                    textinput_mobile.setHelperText("Sponsor Name");
                }
            }

            @Override
            public void onFailure(Call<ResponseSponsor> call, Throwable t) {
            }
        });
    }

    private void JoinReferalProgram(String sponsorId) {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("SponsorId", pkUserId);
        object.addProperty("Leg", leg);
        object.addProperty("UserId", PreferencesManager.getInstance(context).getPk_userId());
        LoggerUtil.logItem(object);

        Call<ResponseStatusMessage> call = apiServices.JoinReferalProgram(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    joinProgramDialog.dismiss();
                    PreferencesManager.getInstance(context).setServicetype("True");
                }
                showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }

    void setDistributorDialog() {
        distributorDialog = new Dialog(context);
        distributorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        distributorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        distributorDialog.setContentView(R.layout.dialog_distributor);

        Button btn_know = distributorDialog.findViewById(R.id.btn_know);
        Button btn_add = distributorDialog.findViewById(R.id.btn_add);
        Button btn_cancel = distributorDialog.findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(view -> distributorDialog.dismiss());
        btn_know.setOnClickListener(view -> GetKnowMore());
        btn_add.setOnClickListener(view -> {
            goToActivity(context, AddDistributor.class, null);
            distributorDialog.dismiss();
        });

        distributorDialog.setCancelable(false);
        distributorDialog.setCanceledOnTouchOutside(false);
        distributorDialog.show();
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

    void permisson()
    {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, 100);
        else
            goToActivity(context, Scanner.class, null);
    }
}
