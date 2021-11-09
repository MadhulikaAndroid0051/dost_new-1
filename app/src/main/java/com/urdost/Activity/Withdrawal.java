package com.urdost.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseActivity;
import com.urdost.model.response.ResponseWalletBalance;
import com.urdost.model.response.ResponseWithdrawal;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Withdrawal extends BaseActivity {

    Bundle bundle;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_wallet_balance)
    TextView tvWalletBalance;
    @BindView(R.id.et_loginId)
    TextInputEditText etLoginId;
    @BindView(R.id.input_layout_loginid)
    TextInputLayout inputLayoutLoginid;
    @BindView(R.id.et_amount)
    TextInputEditText etAmount;
    @BindView(R.id.input_layout_amount)
    TextInputLayout inputLayoutAmount;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    ///
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
        ButterKnife.bind(this);
        tvTitle.setText("Withdrawal");
        getBalance();

    }
    private void getBalance() {
        showLoading();
        hideKeyboard();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        etLoginId.setText(PreferencesManager.getInstance(context).getMobileno());

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
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getMobileno());
        object.addProperty("Amount",etAmount.getText().toString().trim());

        LoggerUtil.logItem(object);
        Call<ResponseWithdrawal> call = apiServices.getWithDrwal(object);
        call.enqueue(new Callback<ResponseWithdrawal>() {
            @Override
            public void onResponse(@NonNull Call<ResponseWithdrawal> call, @NonNull Response<ResponseWithdrawal> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        showMessage(response.body().getMessage());
                        //((MainActivity) context).ReplaceFragment(new MainDashboard(), "Associate Dashboard");
                        onBackPressed();

                    } else {
                        showMessage(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseWithdrawal> call, @NonNull Throwable t) {
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
                if (NetworkUtils.getConnectivityStatus(context) != 0) {
                    if (Validation())
                        getData();
                } else

                    showMessage(R.string.alert_internet);

                break;
        }
    }
}
