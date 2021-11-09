package com.urdost.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.google.gson.JsonObject;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseActivity;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.response.BuyNfc.ResponseBuyNfc;
import com.urdost.model.response.ResponseCheckResallerStatus;
import com.urdost.model.response.ResponseNFCPayNow;
import com.urdost.model.response.ResponseWalletBalance;
import com.urdost.model.response.responsePincode.ResponsePincode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

public class RequestedInvantory extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tvPlan)
    TextView tvPlan;
    @BindView(R.id.tvQuantity)
    TextView tvQuantity;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.et_pincode)
    EditText etPincode;
    @BindView(R.id.et_state)
    EditText etState;
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.btn_search)
    Button btnSearch;
    Bundle bundle;
    @BindView(R.id.cv_plan)
    CardView cvPlan;
    @BindView(R.id.cv_transfer_ewallet)
    CardView cvTransferEwallet;
    @BindView(R.id.et_Wallet_balance)
    EditText etWalletBalance;
    @BindView(R.id.et_discount_coupon_code)
    EditText etDiscountCouponCode;
    @BindView(R.id.tv_payable_amount)
    TextView tvPayableAmount;
    @BindView(R.id.tv_gst)
    TextView tvGst;
    @BindView(R.id.tv_handling_charge)
    TextView tvHandlingCharge;
    @BindView(R.id.btn_pay_now)
    Button btnPayNow;
    @BindView(R.id.cv_quantity)
    CardView cvQuantity;

    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_3)
    TextView tv3;
    @BindView(R.id.tv_4)
    TextView tv4;
    @BindView(R.id.tv_5)
    TextView tv5;
    private String tvTotalamt, discount,tvEventid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requested_ivantory);
        ButterKnife.bind(this);
        tvTitle.setText("REQUESTED INVENTORY");
       // cvTransferEwallet.setVisibility(View.VISIBLE);
      //  cvQuantity.setVisibility(View.GONE);
        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);

        tvPlan.setText(bundle.getString("mrp"));
        tvQuantity.setText(bundle.getString("NoOfSeet"));
        tvPrice.setText(bundle.getString("price"));
        tvEventid = bundle.getString("eventid");
        etWalletBalance.setText(bundle.getString("price"));
        btnPayNow.setText("Submit");
        //  Toast.makeText(context, bundle.getString("eventid")+"", Toast.LENGTH_SHORT).show();


        BuyNfc();
        getBalance();

       /* etPincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etPincode.getText().toString().length() >= 6) {
                    getData(etPincode.getText().toString().trim());
                } else {
                    etState.setText("");
                }
            }
        });
        etCity.setText(etPincode.getText().toString().trim());


    }*/
    }
    private void getBalance() {
        // showLoading();
        hideKeyboard();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());


        LoggerUtil.logItem(object);
        Call<ResponseWalletBalance> call = apiServices.getWalletBalance(object);
        call.enqueue(new Callback<ResponseWalletBalance>() {
            @Override
            public void onResponse(@NonNull Call<ResponseWalletBalance> call, @NonNull Response<ResponseWalletBalance> response) {
                //hideLoading();

                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    tv1.setText(response.body().getWalletBalance());
                    // ((MainActivity) context).ReplaceFragment(new Home(), "Associate Dashboard");


                } else {
                    showMessage(response.body().getMessage());
                }
            }


            @Override
            public void onFailure(@NonNull Call<ResponseWalletBalance> call, @NonNull Throwable t) {
                // hideLoading();
                showMessage("Something went wrong");
            }
        });

    }

    private void getData(String pincode) {

        //showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("Pincode", pincode);
        Call<ResponsePincode> call = apiServices.getCity(object);
        call.enqueue(new Callback<ResponsePincode>() {
            @Override
            public void onResponse(Call<ResponsePincode> call, Response<ResponsePincode> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    etState.setText(response.body().getData().getState());
                    etCity.setText(response.body().getData().getCity());
                    //Toast.makeText(ActivateNfcAddress.this, response + "", Toast.LENGTH_SHORT).show();

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponsePincode> call, Throwable t) {
                hideLoading();
            }
        });
    }
    private boolean validate() {
        if (etState.getText().toString().trim().length()==0) {
            showMessage("Enter Pin Code!");
            return false;
        } else if (etAddress.getText().toString().trim().length() < 2) {
            showMessage("Enter Address!");
            return false;

        }
        return true;
    }

    @OnClick({R.id.img_back, R.id.btn_search, R.id.btn_pay_now})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_search:

                cvQuantity.setVisibility(View.GONE);
                // getBalance();
               // if (validate()) {
                  //  cvTransferEwallet.setVisibility(View.GONE);
                //}
                break;
            case R.id.btn_pay_now:


                PayNow();

                break;
        }
    }

    private void BuyNfc() {

        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("EventId",tvEventid);
        // object.addProperty("Fk_EventId", tvEventid);
        Call<ResponseBuyNfc> call = apiServices.getBuyNfc(object);
        call.enqueue(new Callback<ResponseBuyNfc>() {
            @Override
            public void onResponse(Call<ResponseBuyNfc> call, Response<ResponseBuyNfc> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {

                    etDiscountCouponCode.setText(response.body().getLstBuyNfc().getDiscount());
                    etWalletBalance.setText(bundle.getString("price"));
                    tvPayableAmount.setText(bundle.getString("price"));
                    tvHandlingCharge.setText(response.body().getLstBuyNfc().getDeliverCharge());
                    discount = response.body().getLstBuyNfc().getDiscount();
                    String tvA;
                    tvA = String.valueOf(Float.valueOf(response.body().getLstBuyNfc().getGst()) + 100);
                    tvTotalamt = String.valueOf(Float.valueOf(bundle.getString("price")) * Float.valueOf(100) / Float.valueOf(tvA));
                    tvGst.setText(String.valueOf(Float.valueOf(bundle.getString("price")) - Float.valueOf(tvTotalamt)));

                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseBuyNfc> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void PayNow() {

        showLoading();
        JsonObject object = new JsonObject();

        object.addProperty("Quantity", bundle.getString("NoOfSeet"));
        object.addProperty("FK_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("CategoryId",tvEventid);


        Call<ResponseStatusMessage> call = apiServices.requestedInvantory(object);
        call.enqueue(new Callback<ResponseStatusMessage>() {
            @Override
            public void onResponse(Call<ResponseStatusMessage> call, Response<ResponseStatusMessage> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {

                    showMessage(response.body().getMessage());
                    if (response.body().getMessage().equalsIgnoreCase("Insufficiant Dost Wallet Balance."))
                    {
                        showMessage("Insufficiant Dost Wallet Balance");
                    }else {
                        onBackPressed();
                        showMessage(response.body().getMessage());
                    }

                    //  etDiscountCouponCode.setText(response.body().getLstBuyNfc().getDiscount());
                    // etWalletBalance.setText(bundle.getString("price"));
                    // tvPayableAmount.setText(bundle.getString("price"));
                    // tvGst.setText(response.body().getLstBuyNfc().getGst()*);
                    // tvGst.setText(response.body().getLstBuyNfc().getGst()* Float.parseFloat(tvQuantity.getText().toString())));
                    // tvGst.setText(String.valueOf(Float.parseFloat(response.body().getLstBuyNfc().getGst())));
                    // tvGst.setText(String.valueOf(Float.parseFloat(bundle.getString("price")) * Float.parseFloat(response.body().getLstBuyNfc().getGst())/100));



                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponseStatusMessage> call, Throwable t) {
                hideLoading();
            }
        });
    }
}
