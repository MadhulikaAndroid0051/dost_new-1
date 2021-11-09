package com.urdost.Activity;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

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
import com.urdost.common.Utils;
import com.urdost.constants.BaseActivity;
import com.urdost.model.request.RequestLogin;
import com.urdost.model.response.register.ResponseRegistration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Password extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_forgot_password)
    TextView tvForgotPassword;

    Bundle bundle;
    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);
        ButterKnife.bind(this);

        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);
    }


    @OnClick({R.id.img_back, R.id.btn_submit, R.id.tv_forgot_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_submit:
                if (NetworkUtils.getConnectivityStatus(context) != 0) {
                    if (etPassword.getText().toString().trim().length() >= 6)
                        sendLogin();
                    else showMessage("Enter valid password!");
                } else showMessage(R.string.alert_internet);
                break;
            case R.id.tv_forgot_password:
                Bundle bundle1 = new Bundle();
                bundle1.putString("mobile", bundle.getString("mobile"));
                goToActivity(context, ForgotPassword.class, bundle1);
                break;
        }
    }

    private void sendLogin() {
        hideKeyboard();
        showLoading();
        RequestLogin mobile = new RequestLogin();
        mobile.setMobileNo(bundle.getString("mobile"));
        mobile.setDeviceId(Utils.getAndroidDeviceId(context));
        mobile.setPassword(etPassword.getText().toString());

        LoggerUtil.logItem(mobile);
        Call<ResponseRegistration> call = apiServices.login(mobile);
        call.enqueue(new Callback<ResponseRegistration>() {
            @Override
            public void onResponse(Call<ResponseRegistration> call, Response<ResponseRegistration> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    PreferencesManager.getInstance(context).setUserid(response.body().getData().getLoginId());
                    PreferencesManager.getInstance(context).setFullname(response.body().getData().getName());
                    PreferencesManager.getInstance(context).setMobileno(response.body().getData().getMobileNo());
                    PreferencesManager.getInstance(context).setServicetype(response.body().getData().getUserType());
                    PreferencesManager.getInstance(context).setPKUserId(response.body().getData().getPK_UserId());
                    PreferencesManager.getInstance(context).setUserCode(response.body().getData().getUserCode());
                    PreferencesManager.getInstance(context).setDistrubuter(response.body().getData().getDistributor());
                    PreferencesManager.getInstance(context).setIsAcceptanceTNC(response.body().getData().getIsAcceptanceTNC());
                    goToActivityWithFinish(context, NewContainerActivity.class, null);
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseRegistration> call, Throwable t) {
                hideLoading();
            }
        });
    }
}
