package com.urdost.Activity;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.urdost.R;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseActivity;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.request.RequestCheckMobile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    String referId;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        ButterKnife.bind(this);

        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);
        etMobile.setText(bundle.getString("mobile"));
    }

    @OnClick({R.id.img_back, R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_submit:
                if (NetworkUtils.getConnectivityStatus(context) != 0) {
                    if (etMobile.getText().toString().trim().length() == 10)
                        checkMobile();
                    else showMessage("Enter 10 digit mobile number!");
                } else showMessage(R.string.alert_internet);
                break;
        }
    }

    private void checkMobile() {
        hideKeyboard();
        showLoading();
        RequestCheckMobile mobile = new RequestCheckMobile();
        mobile.setMobileNo(etMobile.getText().toString());

        LoggerUtil.logItem(mobile);
        Call<ResponseStatusMessage> call = apiServices.ForgetPassword(mobile);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    Bundle bundles = new Bundle();
                    bundles.putString("mobile", etMobile.getText().toString());
                    bundles.putString("from", "forgot");
                    bundle.putString("Id", referId);

                    goToActivityWithFinish(context, ActivityOTP.class, bundles);
                }
                showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
                Toast.makeText(context, t + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
