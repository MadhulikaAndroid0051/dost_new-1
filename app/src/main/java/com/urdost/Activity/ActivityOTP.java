package com.urdost.Activity;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.urdost.R;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseActivity;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.request.RequestVerifyOTP;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityOTP extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tv_verify)
    TextView tvVerify;
    @BindView(R.id.et_pin_entry)
    PinEntryEditText etPinEntry;
     String referId;

    Bundle bundle;

    ////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_passwprd_otp);
        ButterKnife.bind(this);
        bundle = new Bundle();
        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);
        referId=bundle.getString("Id");

      // Toast.makeText(this,referId+"", Toast.LENGTH_SHORT).show();

        tvVerify.setText("Verify your mobile number +91 " + bundle.getString("mobile"));
    }

    @OnClick({R.id.img_back, R.id.btn_submit, R.id.tv_resend_otp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_submit:
                if (NetworkUtils.getConnectivityStatus(context) != 0) {
                    if (etPinEntry.getText().toString().trim().length() == 6) {
                        if (bundle.getString("from").equalsIgnoreCase("login")) {

                            verifyOTP();
                        } else {
                            Bundle bundles = new Bundle();
                            bundles.putString("mobile", bundle.getString("mobile"));
                            bundles.putString("otp", etPinEntry.getText().toString());
                            bundles.putString("Id",referId);

                            goToActivityWithFinish(context, NewPassword.class, bundles);
                        }
                    } else showMessage("Enter 6 digit OTP");
                } else showMessage(R.string.alert_internet);
                break;
            case R.id.tv_resend_otp:
                resendOTP();
                break;
        }
    }

    private void verifyOTP() {
        hideKeyboard();
        showLoading();
        RequestVerifyOTP mobile = new RequestVerifyOTP();
        mobile.setMobileNo(bundle.getString("mobile"));
        mobile.setOTP(etPinEntry.getText().toString().trim());

        LoggerUtil.logItem(mobile);
        Call<ResponseStatusMessage> call = apiServices.VerifyOTP(mobile);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    Bundle bundles = new Bundle();
                    bundles.putString("mobile", bundle.getString("mobile"));
                    bundles.putString("Id",referId);

                    goToActivityWithFinish(context, Signup.class, bundles);
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void resendOTP() {
        hideKeyboard();
        showLoading();
        JsonObject mobile = new JsonObject();
        mobile.addProperty("MobileNo", bundle.getString("mobile"));

        LoggerUtil.logItem(mobile);
        Call<ResponseStatusMessage> call = apiServices.resendOTP(mobile);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }

}
