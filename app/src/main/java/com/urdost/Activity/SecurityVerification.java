package com.urdost.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseActivity;
import com.urdost.model.ResponseStatusMessage;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecurityVerification extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_search)
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.security_details);
        ButterKnife.bind(this);
        tvTitle.setText("SECURITY DETAILS");
        btnCancel.setVisibility(View.GONE);
    }

    @OnClick({R.id.img_back, R.id.btn_cancel, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_cancel:
                break;
            case R.id.btn_search:
                if (validate()) {
                    if (NetworkUtils.getConnectivityStatus(context) != 0)
                        SecurityVerification();
                    else showMessage(getResources().getString(R.string.alert_internet));
                }
                break;
        }
    }
    private boolean validate() {
        if (etEmail.getText().toString().trim().length() < 6) {
            showMessage("Enter Email");
            return false;
        }
        return true;
    }
    private void SecurityVerification() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
       object.addProperty("Email",etEmail.getText().toString().trim());

        LoggerUtil.logItem(object);
        Call<ResponseStatusMessage> call = apiServices.getEmailVerification(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    showMessage(response.body().getMessage());
                    onBackPressed();
                    etEmail.getText().clear();
                }
                showMessage(response.body().getMessage());
                etEmail.getText().clear();
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }
}
