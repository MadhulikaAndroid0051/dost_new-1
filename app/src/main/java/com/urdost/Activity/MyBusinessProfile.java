package com.urdost.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.urdost.Adapter.AdapterBussinessProfile;
import com.urdost.Adapter.AdapterSocialContect;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseActivity;
import com.urdost.model.response.MyBussinessProfile.ResponseNfcProfile;
import com.urdost.model.response.ResponseBusinessInformation;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBusinessProfile extends BaseActivity {


    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_about_profile)
    TextView tvAboutProfile;
    @BindView(R.id.rv_orders)
    RecyclerView rvOrders;

    AdapterBussinessProfile adapter;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_orders1)
    RecyclerView rvOrders1;
    RecyclerView.LayoutManager layoutManager1, layoutManager;
    @BindView(R.id.tv_on)
    TextView tvOn;
    @BindView(R.id.simpleSwitch)
    Switch simpleSwitch;
    @BindView(R.id.tv_off)
    TextView tvOff;
    String isclick = "";
    @BindView(R.id.ic_profile)
    ImageView icProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc_business_profile);
        ButterKnife.bind(this);

        tvTitle.setText("My Business Profile");
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager1 = new LinearLayoutManager(getApplicationContext());

        rvOrders.setLayoutManager(layoutManager);
        rvOrders.setHasFixedSize(true);
        rvOrders1.setLayoutManager(layoutManager1);
        rvOrders1.setHasFixedSize(true);
        getSubscriptions();
        getSubscriptions1();


        simpleSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (simpleSwitch.isChecked()) {
                    isclick = "true";
                    profileStatus();

                } else {
                    isclick = "false";
                    profileStatus();


                }

            }
        });
    }

    public void profileStatus() {
        //  showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("IsProfileTurnedOff", isclick);
        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getMobileno());
        LoggerUtil.logItem(object);
        Call<ResponseBusinessInformation> call = apiServices.updateNfcProfileSwitch(object);
        call.enqueue(new Callback<ResponseBusinessInformation>() {
            @Override
            public void onResponse(Call<ResponseBusinessInformation> call, Response<ResponseBusinessInformation> response) {
                //  hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseBusinessInformation> call, Throwable t) {
                Toast.makeText(MyBusinessProfile.this, t + "", Toast.LENGTH_SHORT).show();
                Log.d("error", "" + t);
                // hideLoading();
            }
        });
    }

    public void getSubscriptions() {
        //  showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId",PreferencesManager.getInstance(context).getMobileno());
        //object.addProperty("Fk_UserId", "");
        LoggerUtil.logItem(object);
        Call<ResponseNfcProfile> call = apiServices.getMyBussinessProfile(object);
        call.enqueue(new Callback<ResponseNfcProfile>() {
            @Override
            public void onResponse(Call<ResponseNfcProfile> call, Response<ResponseNfcProfile> response) {
                //  hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    tvName.setText(response.body().getProfile().get(0).getName());
                  // Glide.with(context).load("https://dost.click//AssociateFolder/images/logo3.png").into(icProfile);
                   Glide.with(context).load(response.body().getProfile().get(0).getProfilePic()).placeholder(R.drawable.digi_logo).dontAnimate().into(icProfile);

                    tvAboutProfile.setText(response.body().getProfile().get(0).getSummary());
                    AdapterBussinessProfile adapterBussinessProfile = new AdapterBussinessProfile(context, response.body().getNFCList());
                    rvOrders.setAdapter(adapterBussinessProfile);

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseNfcProfile> call, Throwable t) {
                Toast.makeText(MyBusinessProfile.this, t + "", Toast.LENGTH_SHORT).show();
                Log.d("error", "" + t);
                // hideLoading();
            }
        });
    }

    public void getSubscriptions1() {
        // showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getMobileno());
        //object.addProperty("Fk_UserId", "");
        LoggerUtil.logItem(object);
        Call<ResponseNfcProfile> call = apiServices.getMyBussinessProfile(object);
        call.enqueue(new Callback<ResponseNfcProfile>() {
            @Override
            public void onResponse(Call<ResponseNfcProfile> call, Response<ResponseNfcProfile> response) {
                //  hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {

                    //  tvName.setText(response.body().getProfile().get(0).getName());

                    //tvAboutProfile.setText(response.body().getProfile().get(0).getSummary());
                    AdapterSocialContect adapterBussinessProfile1 = new AdapterSocialContect(context, response.body().getSocialMedia());
                    rvOrders1.setAdapter(adapterBussinessProfile1);
                    // rvOrders.setAdapter(adapterBussinessProfile);
                    // showMessage(response.body().getMessage());
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseNfcProfile> call, Throwable t) {
                Toast.makeText(MyBusinessProfile.this, t + "", Toast.LENGTH_SHORT).show();
                Log.d("error", "" + t);
                // hideLoading();
            }
        });
    }


    @OnClick({R.id.img_back, R.id.tv_title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.tv_title:
                break;
        }
    }
}
