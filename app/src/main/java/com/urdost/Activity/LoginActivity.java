package com.urdost.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.urdost.R;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.common.Utils;
import com.urdost.constants.BaseActivity;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.request.RequestCheckMobile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.frame_root)
    ConstraintLayout frameRoot;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.tv_country_code)
    EditText tvCountryCode;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.layout)
    LinearLayout layout;
    String referId;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);

//        if (bundle.getString("Id") != null)
            referId = bundle.getString("Id");
        // Toast.makeText(this, referId+"", Toast.LENGTH_SHORT).show();

        // bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);

//123456

    }

    @OnClick({R.id.img_back, R.id.btn_submit, R.id.tv_gotregister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finishAffinity();
                break;
            case R.id.btn_submit:
                if (NetworkUtils.getConnectivityStatus(context) != 0) {
                    if (etMobile.getText().toString().trim().length() == 10)
                        checkMobile();
                    else showMessage("Enter 10 digit mobile number!");
                } else showMessage(R.string.alert_internet);
                break;
            case R.id.tv_gotregister:
                goToActivity(context, Signup.class, null);
                break;
        }
    }

    private void checkMobile() {
        hideKeyboard();
        showLoading();
        RequestCheckMobile mobile = new RequestCheckMobile();
        mobile.setDeviceId(Utils.getAndroidDeviceId(context));
        mobile.setMobileNo(etMobile.getText().toString());

        LoggerUtil.logItem(mobile);
        Call<ResponseStatusMessage> call = apiServices.checkMobile(mobile);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    Bundle bundle = new Bundle();
                    bundle.putString("mobile", etMobile.getText().toString());
                    bundle.putString("from", "login");
                    bundle.putString("Id", referId);
                    if (response.body().getMessage().contains("OTP")) {
                        showMessage(response.body().getMessage());
                        goToActivityWithFinish(context, ActivityOTP.class, bundle);
                    } else {
                        goToActivityWithFinish(context, Password.class, bundle);
                    }
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
                Toast.makeText(context, t + "", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
