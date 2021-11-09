package com.urdost.Activity;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseActivity;
import com.urdost.model.response.ResponseActivateCouponId;
import com.urdost.model.response.responseSponserDetails.ResponseSponserDetails;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActicateCouponCode extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.et_loginId)
    EditText etLoginId;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_date)
    EditText tvDate;
    @BindView(R.id.btn_search)
    Button btnSearch;
    Bundle bundle;
    String couponcode, coupon_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dilog_active_coupon);
        ButterKnife.bind(this);
        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);
        couponcode = bundle.getString("couponcode");
        coupon_price = bundle.getString("couponprice");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());
        tvDate.setText(currentDateandTime);


        etLoginId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etLoginId.getText().toString().length() >= 10) {
                    GetSponsorName(etLoginId.getText().toString().trim());
                } else {
                    tvName.setText("");
                }
            }
        });
    }

    public void GetSponsorName(String sponsorId) {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", etLoginId.getText().toString().trim());
        LoggerUtil.logItem(object);
        Call<ResponseSponserDetails> call = apiServices.getSponserId(object);
        call.enqueue(new Callback<ResponseSponserDetails>() {
            @Override
            public void onResponse(Call<ResponseSponserDetails> call, Response<ResponseSponserDetails> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    tvName.setText(response.body().getSponserDetails().getDisplayName());
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseSponserDetails> call, Throwable t) {
                hideLoading();
            }

        });
    }

    private void getData() {
        showLoading();
        hideKeyboard();
        JsonObject object = new JsonObject();
        object.addProperty("AddedBy", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("LoginId", etLoginId.getText().toString().trim());
        object.addProperty("CouponCode", couponcode);
        object.addProperty("CouponPrice", coupon_price);
        object.addProperty("TopupDate", tvDate.getText().toString().trim());

        LoggerUtil.logItem(object);
        Call<ResponseActivateCouponId> call = apiServices.getActivateUserId(object);
        call.enqueue(new Callback<ResponseActivateCouponId>() {
            @Override
            public void onResponse(@NonNull Call<ResponseActivateCouponId> call, @NonNull Response<ResponseActivateCouponId> response) {
                hideLoading();
                if (response.isSuccessful()) {
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        showMessage(response.body().getMessage());
                        onBackPressed();
                    } else {
                        showMessage(response.body().getMessage());

                        //
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseActivateCouponId> call, @NonNull Throwable t) {
                hideLoading();
                showMessage("Something went wrong");
            }
        });
    }

    private boolean Validation() {
        if (etLoginId.getText().toString().length() == 0) {
            etLoginId.setError("Please enter LoginId");
            return false;
        }
        return true;
    }

    @OnClick({R.id.img_back, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                break;
            case R.id.btn_search:
                if (NetworkUtils.getConnectivityStatus(context) != 0) {
                    if (Validation())
                        getData();
                } else showMessage(R.string.alert_internet);

                break;
        }
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
