package com.urdost.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseActivity;
import com.urdost.model.response.ResponseResponseTransferEWallet;
import com.urdost.model.response.ResponseWalletBalance;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Transfer_Ewallet extends BaseActivity {

    @BindView(R.id.tv_wallet_balance)
    TextView tvWalletBalance;
    @BindView(R.id.et_loginId)
    EditText etLoginId;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_back)
    ImageView imgBack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_ewallet);
        ButterKnife.bind(this);
        getBalance();
        tvTitle.setText("Transfer Wallet");
    }


    private void getBalance() {
        showLoading();
        hideKeyboard();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());


        LoggerUtil.logItem(object);
        Call<ResponseWalletBalance> call = apiServices.getWalletBalance(object);
        call.enqueue(new Callback<ResponseWalletBalance>() {
            @Override
            public void onResponse(@NonNull Call<ResponseWalletBalance> call, @NonNull Response<ResponseWalletBalance> response) {
                hideLoading();

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    tvWalletBalance.setText(response.body().getWalletBalance());
                   // ((MainActivity) context).ReplaceFragment(new Home(), "Associate Dashboard");


                } else {
                    showMessage(response.body().getMessage());
                }
            }


            @Override
            public void onFailure(@NonNull Call<ResponseWalletBalance> call, @NonNull Throwable t) {
                hideLoading();
                showMessage("Something went wrong");
            }
        });

    }
    private void getData() {
        showLoading();
        hideKeyboard();
        JsonObject object = new JsonObject();
        object.addProperty("AddedBy", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("LoginId", etLoginId.getText().toString().trim());
        object.addProperty("Amount", etAmount.getText().toString().trim());

        LoggerUtil.logItem(object);
        Call<ResponseResponseTransferEWallet> call = apiServices.getTransferEWallet(object);
        call.enqueue(new Callback<ResponseResponseTransferEWallet>() {
            @Override
            public void onResponse(@NonNull Call<ResponseResponseTransferEWallet> call, @NonNull Response<ResponseResponseTransferEWallet> response) {
                hideLoading();

                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        showMessage(response.body().getMessage());
                       // ((MainActivity) context).ReplaceFragment(new Home(), "Associate Dashboard");
                                onBackPressed();

                    } else {
                        showMessage(response.body().getMessage());
                    }
                }


            @Override
            public void onFailure(@NonNull Call<ResponseResponseTransferEWallet> call, @NonNull Throwable t) {
                hideLoading();
                showMessage("Something went wrong");
            }
        });
    }

    private boolean Validation() {
        if (etLoginId.getText().toString().length() == 0) {
            etLoginId.setError("Please enter loginId");
            return false;
        } else if (etAmount.getText().toString().length() == 0) {
            etAmount.setError("Please enter amount");
            return false;
        }
        return true;
    }

    @OnClick({R.id.img_back, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_submit:
                if(NetworkUtils.getConnectivityStatus(context)!=0)

                {
                    if (Validation())
                        getData();
                } else

                    showMessage(R.string.alert_internet);

                break;
        }
    }
}
