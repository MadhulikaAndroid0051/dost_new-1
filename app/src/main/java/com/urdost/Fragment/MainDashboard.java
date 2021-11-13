package com.urdost.Fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.urdost.Activity.CardActivityAdd;
import com.urdost.Activity.EditProfile;
import com.urdost.Activity.EventDetails;
import com.urdost.Activity.NfcFullImageDetails;
import com.urdost.Activity.PurchaseOrderActivity;
import com.urdost.Activity.ReferEarn;
import com.urdost.Activity.WalletRequest;
import com.urdost.Adapter.AdapterDashboardMenu;
import com.urdost.Adapter.AdapterListData;
import com.urdost.Adapter.AdapterNewContainerBanner;
import com.urdost.Adapter.DashboardGridAdapter;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseFragment;
import com.urdost.interfaces.EventDetailslistner;
import com.urdost.interfaces.NFcFullDetails;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.response.NfcActivateBuyDDCard.ResponseNfcActivate;
import com.urdost.model.response.ResponseTerms;
import com.urdost.model.response.responseMainDashboard.ResponseMainDashboard;
import com.urdost.model.response.sponsorName.ResponseSponsor;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;
import com.smarteist.autoimageslider.SliderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDashboard extends BaseFragment implements EventDetailslistner, NFcFullDetails {
    Unbinder unbinder;

    @BindView(R.id.horizontal_scrollview)
    LinearLayout horizontalScrollview;
    @BindView(R.id.consLayout1)
    ConstraintLayout consLayout1;
    @BindView(R.id.cv_0)
    CardView cv0;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.rechg)
    TextView rechg;
    //BindView(R.id.recharge_recycler)
    // RecyclerView rechargeRecycler;
    @BindView(R.id.cv_1)
    CardView cv1;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.shopping_text)
    TextView shoppingText;
    @BindView(R.id.imageSlider)
    SliderView imageSlider;

    @BindView(R.id.imageSlider1)
    SliderView imageSlider1;

    @BindView(R.id.imageSlider23)
    SliderView imageSlider2;

    @BindView(R.id.tv_prime_text)
    TextView tvPrimeText;
    @BindView(R.id.cv_shop)
    CardView cvShop;
    @BindView(R.id.cv_passbook)
    CardView cvPassbook;
    @BindView(R.id.rv_dashboard_menu)
    RecyclerView rvDashboardMenu;
    @BindView(R.id.rv_dashboard_menu1)
    RecyclerView rvDashboardMenu1;
    @BindView(R.id.mainScroll)
    NestedScrollView mainScroll;
    @BindView(R.id.liner1)
    LinearLayout liner1;
    @BindView(R.id.liner2)
    LinearLayout liner2;
    @BindView(R.id.liner3)
    LinearLayout liner3;
    @BindView(R.id.liner4)
    LinearLayout liner4;
    private static final int STORAGE_PERMISSION_CODE = 123;
    Dialog joinProgramDialog, distributorDialog, dilogNfcActivation;
    @BindView(R.id.tv_liner4)
    TextView tvLiner4;
    private static final int CAMERA_REQUEST = 1888;

    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    @BindView(R.id.qr_img)
    ImageView qrImg;


    private String[] nameArr = {"Mobile\nRecharge",
            "Electricity\nBill Pay",
            "Water\nBill Pay",
            "Gas\nBill Pay",
            "DTH\nRecharge",
            "Broadband\nRecharge"/*,
            "Insurance\nRenewal",
            "Fastag\nPayment",
            "EMI\nPayment"*/};

    private int[] imgArr = {R.drawable.ic_mobile,
            R.drawable.ic_mobile,
            R.drawable.ic_mobile,
            R.drawable.ic_mobile,
            R.drawable.ic_mobile,
            R.drawable.ic_mobile/*,
            R.drawable.ic_insurance,
            R.drawable.ic_fasttag,
            R.drawable.ic_emi*/
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_main_dashboard, container, false);
        unbinder = ButterKnife.bind(this, view);
        String welcome = getColoredSpanned("Welcome ", "#000313");
        String signup = getColoredSpanned(PreferencesManager.getInstance(context).getFullname(), "#0063e8");
        tvUserName.setText(Html.fromHtml(welcome + " " + signup));
        //Toast.makeText(context,PreferencesManager.getInstance(context).getDistrubuter()+ "", Toast.LENGTH_SHORT).show();
        //Glide.with(context).load("load-from-whatever-source").transform(new BlurMaskFilter.Blur(context, 20)).into(qrImg);

        tvLiner4.setText("QR \n SCANNER");


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext());
        // RecyclerView.LayoutManager layoutManager3=new LinearLayoutManager(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);

        rvDashboardMenu1.setLayoutManager(gridLayoutManager);
        rvDashboardMenu.setLayoutManager(layoutManager1);
        rvDashboardMenu.setHasFixedSize(true);


        // rechargeRecycler.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DashboardGridAdapter adapterViewAndroid = new DashboardGridAdapter(getActivity(), nameArr, imgArr, this);
        //rechargeRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        //rechargeRecycler.setAdapter(adapterViewAndroid);
        // rechargeRecycler.setHasFixedSize(true);

        if (NetworkUtils.getConnectivityStatus(context) != 0) {
            getData();
            getData1();
        } else showMessage(R.string.alert_internet);

    }

    public void getData() {
        showLoading();
        Call<ResponseMainDashboard> call = apiServices.getNewContainer();
        call.enqueue(new Callback<ResponseMainDashboard>() {
            @Override
            public void onResponse(Call<ResponseMainDashboard> call, Response<ResponseMainDashboard> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equals("200")) {
                    AdapterDashboardMenu adapter = new AdapterDashboardMenu(context, response.body().getLstSubCategory(), MainDashboard.this);
                    rvDashboardMenu.setAdapter(adapter);
                    AdapterNewContainerBanner adapterNewContainerBanner = new AdapterNewContainerBanner(context, response.body().getLstBanner());
                    imageSlider.setSliderAdapter(adapterNewContainerBanner);
                    imageSlider.setAutoCycle(true);
                    imageSlider.startAutoCycle();

                    imageSlider1.setSliderAdapter(adapterNewContainerBanner);
                    imageSlider1.setAutoCycle(true);
                    imageSlider1.startAutoCycle();

                    imageSlider2.setSliderAdapter(adapterNewContainerBanner);
                    imageSlider2.setAutoCycle(true);
                    imageSlider2.startAutoCycle();
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseMainDashboard> call, Throwable t) {
                hideLoading();
            }
        });
    }


    @Override
    public void openeventDetails(String eventId, String type) {
        Bundle bundle = new Bundle();
        bundle.putString("eventId", eventId);
        bundle.putString("type", type);
        if (eventId.equalsIgnoreCase("2")) {
            goToActivity(EventDetails.class, bundle);
        } else if (eventId.equalsIgnoreCase("3")) {
            goToActivity(EventDetails.class, bundle);

        }
    }

    @OnClick({R.id.liner1, R.id.liner2, R.id.liner3, R.id.liner4, R.id.cv_shop, R.id.cv_passbook})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.liner1:
                goToActivity(ReferEarn.class, null);
                break;
            case R.id.liner2:
                goToActivity(EditProfile.class, null);
                break;
            case R.id.liner3:
                goToActivity(WalletRequest.class,null);
                // goToActivity(UploadeKyc.class, null);
                break;
            case R.id.liner4:
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, 100);
                } else {
                    goToActivity(Scanner.class, null);

                }
                //  if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // takePictureButton.setEnabled(false);
                //    ActivityCompat.requestPermissions(context, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
                // }else {
                //     goToActivity(Scanner.class, null);

                //  }
                break;

            case R.id.cv_shop:
                goToActivity(PurchaseOrderActivity.class, null);
                break;
            case R.id.cv_passbook:
                //  showMessage("coming soon");
                goToActivity(CardActivityAdd.class, null);
                break;
        }
    }

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

    TextInputLayout textinput_mobile;
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
        EditText et_sponsor = joinProgramDialog.findViewById(R.id.et_sponsor);
        RadioGroup rg_leg = joinProgramDialog.findViewById(R.id.rg_leg);

        tv_terms.setText(Html.fromHtml(terms));

        btn_cancel.setOnClickListener(v -> joinProgramDialog.cancel());

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

        btn_search.setOnClickListener(view -> {
            if (checkBox.isChecked()) {
                if (isValidSponsor) {
                    JoinReferalProgram(et_sponsor.getText().toString());
                } else showMessage("Enter valid sponsor code.");
            } else showMessage("Accept terms & conditions.");
        });

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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Showing the toast message
                Toast.makeText(context, "Camera Permission Granted", Toast.LENGTH_SHORT).show();
                goToActivity(Scanner.class, null);
            } else {
                Toast.makeText(context, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }
    private void getData1() {

        Call<ResponseNfcActivate> call = apiServices.getActivateNfcDd();
        call.enqueue(new Callback<ResponseNfcActivate>() {
            @Override
            public void onResponse(Call<ResponseNfcActivate> call, Response<ResponseNfcActivate> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterListData adapter = new AdapterListData(MainDashboard.this,response.body().getLstnfcDdCards(),context);
                    rvDashboardMenu1.setAdapter(adapter);
                } else showMessage(response.body().getMessage());
            }


            @Override
            public void onFailure(Call<ResponseNfcActivate> call, Throwable t) {
                hideLoading();
            }
        });
    }


    @Override
    public void getNFcFullDetails(String Itemid, String img, String ReferBv, String TeamBv, String Discount,String EventName,String Price) {
        Bundle bundle = new Bundle();
        bundle.putString("itemId", Itemid);
        bundle.putString("img",img);
        bundle.putString("ReferBv",ReferBv);
        bundle.putString("TeamBv",TeamBv);
        bundle.putString("Discount",Discount);
        bundle.putString("EventName",EventName);
        bundle.putString("Price",Price);


        goToActivity(NfcFullImageDetails.class,bundle);
    }
}



