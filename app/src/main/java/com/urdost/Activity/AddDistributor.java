package com.urdost.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.common.Utils;
import com.urdost.constants.BaseActivity;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.request.RequestAddDistributor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDistributor extends BaseActivity {


        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.et_name)
        EditText etName;
        @BindView(R.id.et_designation)
        EditText etDesignation;
        @BindView(R.id.et_company)
        EditText etCompany;
        @BindView(R.id.et_company_type)
        EditText etCompanyType;
        @BindView(R.id.et_company_size)
        EditText etCompanySize;
        @BindView(R.id.et_gst)
        EditText etGst;
        @BindView(R.id.et_pan)
        EditText etPan;
        @BindView(R.id.et_address)
        EditText etAddress;
        @BindView(R.id.et_Email)
        EditText etEmail;
        @BindView(R.id.et_mobile)
        EditText etMobile;
        @BindView(R.id.et_website)
        EditText etWebsite;
        @BindView(R.id.et_about)
        EditText etAbout;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_distributor);
            ButterKnife.bind(this);

            tvTitle.setText("Add Distributor");
        }

        @OnClick({R.id.img_back, R.id.btn_submit})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.img_back:
                    onBackPressed();
                    break;
                case R.id.btn_submit:
                    if (NetworkUtils.getConnectivityStatus(context) != 0) {
                        if (validate())
                            addDistributor();
                    } else showMessage(getString(R.string.alert_internet));
                    break;
            }
        }

        private void addDistributor() {
            hideKeyboard();
            showLoading();
            RequestAddDistributor distributor = new RequestAddDistributor();
            distributor.setAboutCompany(etAbout.getText().toString());
            distributor.setCompanyAddress(etAddress.getText().toString());
            distributor.setCompanyName(etCompany.getText().toString());
            distributor.setCompanySize(etCompanySize.getText().toString());
            distributor.setCompanyType(etCompanyType.getText().toString());
            distributor.setDesignation(etDesignation.getText().toString());
            distributor.setEmail(etEmail.getText().toString());
            distributor.setFkUserId(PreferencesManager.getInstance(context).getPk_userId());
            distributor.setGST(etGst.getText().toString());
            distributor.setMobile(etMobile.getText().toString());
            distributor.setPAN(etPan.getText().toString());
            distributor.setWebsite(etWebsite.getText().toString());
            distributor.setYourName(etName.getText().toString());

            LoggerUtil.logItem(distributor);
            Call<ResponseStatusMessage> call = apiServices.addDistributor(distributor);
            call.enqueue(new Callback<ResponseStatusMessage>() {
                @Override
                public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                    hideLoading();
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatusCode().equalsIgnoreCase("200"))
                        onBackPressed();
                    showMessage(response.body().getMessage());
                }

                @Override
                public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                    hideLoading();
                }
            });
        }

        private boolean validate() {
            if (etName.getText().toString().length() == 0) {
                showMessage("Enter name");
                return false;
            } else if (etDesignation.getText().toString().length() == 0) {
                showMessage("Enter designation");
                return false;
            } else if (etCompany.getText().toString().length() == 0) {
                showMessage("Enter Company name");
                return false;
            } else if (etCompanyType.getText().toString().length() == 0) {
                showMessage("Enter Company type");
                return false;
            } else if (etCompanySize.getText().toString().length() == 0) {
                showMessage("Enter Company size");
                return false;
            } else if (etGst.getText().toString().length() == 0) {
                showMessage("Enter GSTIN");
                return false;
            } else if (etPan.getText().toString().length() != 11) {
                showMessage("Enter PAN");
                return false;
            } else if (etAddress.getText().toString().length() == 0) {
                showMessage("Enter Company address");
                return false;
            } else if (!Utils.isEmailValid(etEmail.getText().toString())) {
                showMessage("Enter Email Id");
                return false;
            } else if (etMobile.getText().toString().length() != 10) {
                showMessage("Enter mobile number");
                return false;
            } else if (etWebsite.getText().toString().length() == 0) {
                showMessage("Enter Company website");
                return false;
            } else if (etAbout.getText().toString().length() == 0) {
                showMessage("Enter About Company");
                return false;
            }
            return true;
        }
}
