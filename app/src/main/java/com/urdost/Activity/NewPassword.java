package com.urdost.Activity;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.urdost.R;
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

public class NewPassword extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    Bundle bundle;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_repassword)
    EditText etRepassword;
    String referId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_new_password);
        ButterKnife.bind(this);
        bundle = new Bundle();
        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);
        referId=bundle.getString("Id");

    }

    @OnClick({R.id.img_back, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_submit:
                if (validate()) {
                    if (NetworkUtils.getConnectivityStatus(context) != 0)
                        newPassword();
                    else showMessage(getResources().getString(R.string.alert_internet));
                }
                break;
        }
    }

    private boolean validate() {
        if (etNewPassword.getText().toString().trim().length() < 6) {
            showMessage("Enter 6 digit Password!");
            return false;
        } else if (!(etRepassword.getText().toString().trim().equals(etNewPassword.getText().toString().trim()))) {
            showMessage("Password Not Matched!");
            return false;
        }
        return true;
    }

    private void newPassword() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("TempPassword", bundle.getString("otp"));
        object.addProperty("MobileNo", bundle.getString("mobile"));
        object.addProperty("Password", etNewPassword.getText().toString());

        LoggerUtil.logItem(object);
        Call<ResponseStatusMessage> call = apiServices.ResetPassword(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    Bundle bundle1 = new Bundle();

                    bundle1.putString("Id", referId);

                    goToActivityWithFinish(context, LoginActivity.class, bundle1);
                }
                showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }
}
