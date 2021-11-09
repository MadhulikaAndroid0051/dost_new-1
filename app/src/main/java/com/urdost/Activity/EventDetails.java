package com.urdost.Activity;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.urdost.Adapter.AdapterSubscription;
import com.urdost.R;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseActivity;
import com.urdost.interfaces.ActivateNfcDDCardlistner;
import com.urdost.model.response.responseSubscription.ResponseSubscription;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDetails extends BaseActivity implements ActivateNfcDDCardlistner {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    Bundle bundle;
    private String eventId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_nfc);
        ButterKnife.bind(this);
        tvTitle.setText("Event List");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerview1.setLayoutManager(layoutManager);
        recyclerview1.setHasFixedSize(true);
        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);
        eventId = bundle.getString("eventId");
        if (NetworkUtils.getConnectivityStatus(context) != 0)
            getData();
        else showMessage(getString(R.string.alert_internet));


    }

    private void getData() {

        JsonObject object = new JsonObject();
        object.addProperty("CategoryId", eventId);
        object.addProperty("UserId", "2");
        LoggerUtil.logItem(object);
        Call<ResponseSubscription> call = apiServices.getSubscription(object);
        call.enqueue(new Callback<ResponseSubscription>() {
            @Override
            public void onResponse(Call<ResponseSubscription> call, Response<ResponseSubscription> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterSubscription adapter = new AdapterSubscription(response.body().getLstSubscription(), context, EventDetails.this);
                    recyclerview1.setAdapter(adapter);
                } else showMessage(response.body().getMessage());
            }


            @Override
            public void onFailure(Call<ResponseSubscription> call, Throwable t) {
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
        goToActivity(context, SubscriptionDetails.class, bundle);
    }


    @OnClick(R.id.img_back)
    public void onClick() {
        onBackPressed();
    }
}
