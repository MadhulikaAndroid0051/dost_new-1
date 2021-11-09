package com.urdost.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.urdost.Activity.ActicateCouponCode;
import com.urdost.Activity.NewContainerActivity;
import com.urdost.Adapter.AdapterAvalabileInvantory;
import com.urdost.Adapter.AdapterInDeliverd;
import com.urdost.Adapter.AdapterUnusedCoupon;
import com.urdost.Adapter.AdapterUsedCoupon;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseFragment;
import com.urdost.interfaces.UnUsedListner;
import com.urdost.model.response.AvalableInvantory.ResponseAvailable;
import com.urdost.model.response.DelaveredInvatory.ResponseDelavierd;
import com.urdost.model.response.NfcActivateBuyDDCard.ResponseNfcActivate;
import com.urdost.model.response.ResponseInvantory;
import com.urdost.model.response.responseUnusedCoupon.ResponseUnUsedCoupon;
import com.urdost.model.response.responseUsedCode.ResponseUsedCode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DistributorZone extends BaseFragment implements UnUsedListner {

    Unbinder unbinder;
    @BindView(R.id.imageView5)
    ImageView imageView5;
    @BindView(R.id.textView35)
    TextView textView35;
    @BindView(R.id.textView36)
    TextView textView36;
    @BindView(R.id.tv_wallet_amt)
    TextView tvWalletAmt;
    @BindView(R.id.cv_dw_wallet)
    CardView cvDwWallet;
    @BindView(R.id.imageView6)
    ImageView imageView6;
    @BindView(R.id.textView38)
    TextView textView38;
    @BindView(R.id.tv_coin_amt)
    TextView tvCoinAmt;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.textView37)
    TextView textView37;
    @BindView(R.id.cv_commission)
    CardView cvCommission;
    @BindView(R.id.imageView7)
    ImageView imageView7;
    @BindView(R.id.textView39)
    TextView textView39;
    @BindView(R.id.tv_distributor_amt)
    TextView tvDistributorAmt;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.textView40)
    TextView textView40;
    @BindView(R.id.cv_hold)
    CardView cvHold;
    @BindView(R.id.tv_list_name)
    TextView tvListName;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    BottomSheetDialog searchDialog;
    @BindView(R.id.btn_search)
    ImageView btnSearch;
    @BindView(R.id.rv_lst_inventory)
    RecyclerView rvLstInventory;
    @BindView(R.id.rv_lst_request)
    RecyclerView rvLstRequest;
    @BindView(R.id.tv_invantory_request)
    TextView tvInvantoryRequest;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgment_distrubuter_zone, container, false);
        unbinder = ButterKnife.bind(this, view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(layoutManager);
        rvList.setHasFixedSize(true);
        rvLstInventory.setLayoutManager(layoutManager1);
        rvLstInventory.setHasFixedSize(true);
        rvList.setVisibility(View.VISIBLE);
        btnSearch.setVisibility(View.GONE);
        rvLstInventory.setVisibility(View.GONE);
        rvLstRequest.setVisibility(View.GONE);
        InvantoryDeliverd();       // getData();
        getInvantoryData();

        return view;

    }

    @OnClick({R.id.btn_search, R.id.cv_dw_wallet, R.id.cv_commission, R.id.cv_hold})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cv_dw_wallet:
                tvListName.setText(" Available Inventory");
                //getData();

                InvantoryDeliverd();
                rvLstInventory.setVisibility(View.GONE);
                rvLstRequest.setVisibility(View.GONE);
                break;
            case R.id.cv_commission:
               // getDataUnused();
                tvListName.setText("Delivered Inventory");
                rvList.setVisibility(View.VISIBLE);
                InvantoryAvaliable();
              rvLstRequest.setVisibility(View.GONE);

                break;
            case R.id.cv_hold:
                tvListName.setText("Inventory Request");
                rvList.setVisibility(View.VISIBLE);

               // rvLstInventory.setVisibility(View.GONE);
               // rvLstRequest.setVisibility(View.VISIBLE);
                goToActivity(InvantoryRequestActicity.class,null);
                break;
            case R.id.btn_search:

                break;
        }
    }

    public void getData() {
        //showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("CouponCode", "");
        object.addProperty("CategoryCode", "");
        LoggerUtil.logItem(object);
        Call<ResponseUsedCode> call = apiServices.getUsedCouponCode(object);
        call.enqueue(new Callback<ResponseUsedCode>() {
            @Override
            public void onResponse(Call<ResponseUsedCode> call, Response<ResponseUsedCode> response) {
              //  hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterUsedCoupon adapter = new AdapterUsedCoupon(response.body().getLstUsedCodes(), getContext());
                    rvList.setAdapter(adapter);
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseUsedCode> call, Throwable t) {
                hideLoading();
            }

        });
    }

    public void getDataSearch(String startDate, String endDate, String payoutno) {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("CouponCode", "");
        object.addProperty("CategoryCode", "");
        LoggerUtil.logItem(object);
        Call<ResponseUsedCode> call = apiServices.getUsedCouponCode(object);
        call.enqueue(new Callback<ResponseUsedCode>() {
            @Override
            public void onResponse(Call<ResponseUsedCode> call, Response<ResponseUsedCode> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterUsedCoupon adapter = new AdapterUsedCoupon(response.body().getLstUsedCodes(), getContext());
                    rvList.setAdapter(adapter);
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseUsedCode> call, Throwable t) {
                hideLoading();
            }

        });
    }

    String category = "";


    private void searchhDialog() {
        searchDialog = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dilog_used_coupon, null);
        searchDialog.setContentView(sheetView);
        EditText et_coupon_code = sheetView.findViewById(R.id.et_coupon_code);
        EditText et_coupon_used = sheetView.findViewById(R.id.et_coupon_used);
        EditText tv_catogory = sheetView.findViewById(R.id.tv_catogory);

        Button btn_cancel = sheetView.findViewById(R.id.btn_cancel);
        Button btn_search = sheetView.findViewById(R.id.btn_search);
        btn_cancel.setOnClickListener(v -> {
            searchDialog.dismiss();
        });

        tv_catogory.setOnClickListener(v -> {
            PopupMenu popup_sidemenu = new PopupMenu(context, tv_catogory);
            popup_sidemenu.getMenuInflater().inflate(R.menu.menu_coupon_catogory, popup_sidemenu.getMenu());
            popup_sidemenu.setOnMenuItemClickListener(item -> {
                if (item.getTitle().equals("All"))
                    category = "null";
                else if (item.getTitle().equals("Active"))
                    category = "P";
                else if (item.getTitle().equals("InActive"))
                    category = "T";
                else if (item.getTitle().equals("Blocked"))
                    category = "B";
                tv_catogory.setText(item.getTitle());
                return true;
            });
            popup_sidemenu.show();
        });


        btn_search.setOnClickListener(v -> {
            searchDialog.dismiss();
            getDataSearch(et_coupon_code.getText().toString().trim(),
                    et_coupon_used.getText().toString().trim(), category);
        });

        searchDialog.setCancelable(false);
        searchDialog.setCanceledOnTouchOutside(false);
        searchDialog.show();
    }

    public void getDataUnused() {
       // showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        // object.addProperty("CouponCode", "");
        object.addProperty("CategoryCode", "0");
        LoggerUtil.logItem(object);
        Call<ResponseUnUsedCoupon> call = apiServices.getUnUsedCouponCode(object);
        call.enqueue(new Callback<ResponseUnUsedCoupon>() {
            @Override
            public void onResponse(Call<ResponseUnUsedCoupon> call, Response<ResponseUnUsedCoupon> response) {
               // hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterUnusedCoupon adapter = new AdapterUnusedCoupon(DistributorZone.this, response.body().getLstunusedcoupons(), getContext());
                    rvLstInventory.setAdapter(adapter);

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseUnUsedCoupon> call, Throwable t) {
               // hideLoading();
            }

        });
    }


    @Override
    public void ActivateId(String couponCode, String couponPice) {
        Bundle bundle = new Bundle();
        bundle.putString("couponcode", couponCode);
        bundle.putString("couponprice", couponPice);
        goToActivity(ActicateCouponCode.class, bundle);
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

    public void getInvantoryData() {
        //showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());

        LoggerUtil.logItem(object);
        Call<ResponseInvantory> call = apiServices.getInvantoryData(object);
        call.enqueue(new Callback<ResponseInvantory>() {
            @Override
            public void onResponse(Call<ResponseInvantory> call, Response<ResponseInvantory> response) {
                // hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    tvWalletAmt.setText(response.body().getAvailableInventory());
                    tvCoinAmt.setText(response.body().getDeliveredInventory());
                    tvDistributorAmt.setText(response.body().getInventoryRequest());
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseInvantory> call, Throwable t) {
                // hideLoading();
            }

        });
    }



    @OnClick(R.id.tv_invantory_request)
    public void onClick() {
        goToActivity(InvantoryRequestActicity.class,null);
    }

    public void InvantoryDeliverd()
    {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId",PreferencesManager.getInstance(context).getPk_userId());
        // object.addProperty("CouponCode", "");
        object.addProperty("Code", "");
        LoggerUtil.logItem(object);
        Call<ResponseDelavierd> call = apiServices.getDelaviredInvantory(object);
        call.enqueue(new Callback<ResponseDelavierd>() {
            @Override
            public void onResponse(Call<ResponseDelavierd> call, Response<ResponseDelavierd> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterInDeliverd adapter = new AdapterInDeliverd(response.body().getLstUnusedNFC(),context);
                    rvList.setAdapter(adapter);
                  //  rvList.setVisibility(View.GONE);

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseDelavierd> call, Throwable t) {
                hideLoading();
            }

        });
    }
    public void InvantoryAvaliable()
    {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        // object.addProperty("CouponCode", "");
        object.addProperty("Code", "");
        LoggerUtil.logItem(object);
        Call<ResponseAvailable> call = apiServices.getAvalibaleInvantory(object);
        call.enqueue(new Callback<ResponseAvailable>() {
            @Override
            public void onResponse(Call<ResponseAvailable> call, Response<ResponseAvailable> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    AdapterAvalabileInvantory adapter = new AdapterAvalabileInvantory(response.body().getLstUsedNFC(),context);
                    rvList.setAdapter(adapter);
                    //  rvList.setVisibility(View.GONE);

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseAvailable> call, Throwable t) {
                hideLoading();
            }

        });
    }
}
