package com.urdost.Fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.urdost.Activity.InvantoryRequestDetails;
import com.urdost.Adapter.AdapterInvantoryRequest;
import com.urdost.R;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseActivity;
import com.urdost.interfaces.ActivateNfcDDCardlistner;
import com.urdost.model.response.NfcActivateBuyDDCard.ResponseNfcActivate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvantoryRequestActicity extends BaseActivity implements ActivateNfcDDCardlistner {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_nfc);
        ButterKnife.bind(this);
        tvTitle.setText("INVENTORY REQUEST");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerview1.setLayoutManager(layoutManager);
        recyclerview1.setHasFixedSize(true);

        if (NetworkUtils.getConnectivityStatus(context) != 0)
            getData();

        else showMessage(getString(R.string.alert_internet));

    }

    private void getData() {

        Call<ResponseNfcActivate> call = apiServices.getActivateNfcDd();
        call.enqueue(new Callback<ResponseNfcActivate>() {
            @Override
            public void onResponse(Call<ResponseNfcActivate> call, Response<ResponseNfcActivate> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterInvantoryRequest adapter = new AdapterInvantoryRequest(response.body().getLstnfcDdCards(), context, InvantoryRequestActicity.this);
                    recyclerview1.setAdapter(adapter);
                } else showMessage(response.body().getMessage());
            }


            @Override
            public void onFailure(Call<ResponseNfcActivate> call, Throwable t) {
                hideLoading();
            }
        });
    }


    @Override
    public void activateddCard(String id, String mrp, String eventName, String ReferBv, String TeamBv) {

        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("mrp", mrp);
        bundle.putString("eventName", eventName);
        bundle.putString("ReferBv", ReferBv);
        bundle.putString("TeamBv", TeamBv);
        goToActivity(context, InvantoryRequestDetails.class, bundle);
    }

    @OnClick(R.id.img_back)
    public void onClick() {
        onBackPressed();
    }
}
