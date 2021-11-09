package com.urdost.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.urdost.Adapter.AdapterPuarchaseOrder;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseActivity;
import com.urdost.interfaces.NfcDeletId;
import com.urdost.model.response.PurchaseOrder.ResponsePurchaseOrder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseOrderActivity extends BaseActivity implements NfcDeletId {

    @BindView(R.id.recyclerview1)
    RecyclerView recyclerview1;
    @BindView(R.id.btn_search)
    ImageView btnSearch;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_direct);
        ButterKnife.bind(this);
        tvTitle.setText("Purchase & Order");
        btnSearch.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview1.setLayoutManager(layoutManager);
        recyclerview1.setHasFixedSize(true);


        if (NetworkUtils.getConnectivityStatus(context) != 0) {
            getData();
        } else showMessage(R.string.alert_internet);


    }


    public void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("PK_UserId", PreferencesManager.getInstance(context).getPk_userId());

        LoggerUtil.logItem(object);
        Call<ResponsePurchaseOrder> call = apiServices.getPurachaseOrder(object);
        call.enqueue(new Callback<ResponsePurchaseOrder>() {
            @Override
            public void onResponse(Call<ResponsePurchaseOrder> call, Response<ResponsePurchaseOrder> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterPuarchaseOrder adapter = new AdapterPuarchaseOrder(response.body().getLst(), PurchaseOrderActivity.this, PurchaseOrderActivity.this);
                    recyclerview1.setAdapter(adapter);
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponsePurchaseOrder> call, Throwable t) {
                hideLoading();
            }

        });
    }


    @Override
    public void getDeleatId(String pkNfcProfileId) {
        Bundle bundle = new Bundle();
        bundle.putString("id", pkNfcProfileId);
        goToActivity(context, TaxInvoiceActivity.class, bundle);
    }


    @OnClick(R.id.img_back)
    public void onClick() {
        onBackPressed();
    }
}
