package com.urdost.Activity;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;

import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.common.Utils;
import com.urdost.constants.BaseActivity;
import com.urdost.model.request.RequestRegister;
import com.urdost.model.response.register.ResponseRegistration;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.tv_age)
    EditText tvAge;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_repassword)
    EditText etRepassword;
    @BindView(R.id.tv_terms_conditions)
    CheckBox tvTermsConditions;
    @BindView(R.id.tv_gender)
    EditText tvGender;

    String referId;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        ButterKnife.bind(this);
        bundle = new Bundle();
        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);
        referId=bundle.getString("Id");

      //  Toast.makeText(context, referId+"", Toast.LENGTH_SHORT).show();

    }

    @OnClick({R.id.img_back, R.id.tv_gender, R.id.tv_age, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.tv_gender:
                PopupMenu popup = new PopupMenu(context, tvGender);
                popup.getMenuInflater().inflate(R.menu.menu_gender, popup.getMenu());
                popup.setOnMenuItemClickListener(item -> {
                    tvGender.setText(item.getTitle());
                    return true;
                });
                popup.show();
                break;
            case R.id.tv_age:
                datePicker(tvAge);
                break;
            case R.id.btn_submit:

                if (validate()) {
                    if (NetworkUtils.getConnectivityStatus(context) != 0)
                        register();
                    else showMessage(getResources().getString(R.string.alert_internet));
                }
                break;
        }
    }

    private void datePicker(final EditText et) {
        Calendar cal = Calendar.getInstance();
        int mYear, mMonth, mDay;

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, monthOfYear, dayOfMonth) -> {
            et.setText(Utils.changeDateFormat(dayOfMonth, monthOfYear, year));
        }, mYear, mMonth, mDay);
//        cal.add(Calendar.YEAR, -18);
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }

    private void register() {
        hideKeyboard();
        showLoading();
        RequestRegister register = new RequestRegister();
        register.setSponsorId( referId);
        register.setDOB(tvAge.getText().toString());
        register.setFirstName(etFirstName.getText().toString());
        register.setLastName(etLastName.getText().toString());
        register.setGender(tvGender.getText().toString());
        register.setPassword(etPassword.getText().toString());
        register.setMobileNo(bundle.getString("mobile"));
        register.setLeg("");
        register.setPincode("");

        LoggerUtil.logItem(register);
        Call<ResponseRegistration> call = apiServices.register(register);
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
                    PreferencesManager.getInstance(context).setPk_userId(response.body().getData().getPK_UserId());
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

    private boolean validate() {
        if (etFirstName.getText().toString().trim().length() < 2) {
            showMessage("Enter First Name!");
            return false;
        } else if (tvGender.getText().toString().trim().length() < 2) {
            showMessage("Select Gender!");
            return false;
        } else if (tvAge.getText().toString().trim().length() < 2) {
            showMessage("Select Age!");
            return false;
        } else if (etPassword.getText().toString().trim().length() < 6) {
            showMessage("Enter 6 digit Password!");
            return false;
        } else if (!(etRepassword.getText().toString().trim().equals(etPassword.getText().toString().trim()))) {
            showMessage("Password Not Matched!");
            return false;
        }
        return true;
    }
}
