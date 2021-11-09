package com.urdost.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.JsonObject;
import com.urdost.R;
import com.urdost.common.LoggerUtil;
import com.urdost.constants.BaseActivity;
import com.urdost.model.response.ResponseTaxInvoice;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

public class TaxInvoiceActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_log)
    ImageView imgLog;
    @BindView(R.id.textview1)
    TextView textview1;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_Email)
    TextView tvEmail;
    @BindView(R.id.tv_invoice_no)
    TextView tvInvoiceNo;
    @BindView(R.id.tv_invoice_no_date)
    TextView tvInvoiceNoDate;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.textview2)
    TextView textview2;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.textview3)
    TextView textview3;
    @BindView(R.id.tv_sgst)
    TextView tvSgst;
    @BindView(R.id.textview4)
    TextView textview4;
    @BindView(R.id.tv_cgst)
    TextView tvCgst;
    @BindView(R.id.textview5)
    TextView textview5;
    @BindView(R.id.tv_igst)
    TextView tvIgst;
    @BindView(R.id.textview6)
    TextView textview6;
    @BindView(R.id.tv_quantity)
    TextView tvQuantity;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_text_summary)
    TextView tvTextSummary;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tv_less_discount)
    TextView tvLessDiscount;
    @BindView(R.id.tv_total_sale_value)
    TextView tvTotalSaleValue;
    @BindView(R.id.tv_add_text)
    TextView tvAddText;
    @BindView(R.id.tv_total_value_after_text)
    TextView tvTotalValueAfterText;
    @BindView(R.id.tv_grand_total)
    TextView tvGrandTotal;
    @BindView(R.id.tv_total_value)
    TextView tvTotalValue;
    Bundle bundle;
    @BindView(R.id.view1)
    View view1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tax_invoice);
        ButterKnife.bind(this);
        tvTitle.setText("Invoice");
        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);
        // Toast.makeText(context, bundle.getString("id")+"", Toast.LENGTH_SHORT).show();
        getData();


    }

    @OnClick(R.id.img_back)
    public void onClick() {
        onBackPressed();
    }

    public void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("InvoiceNo", bundle.getString("id"));


        LoggerUtil.logItem(object);

        Call<ResponseTaxInvoice> call = apiServices.getInvoiceData(object);
        call.enqueue(new Callback<ResponseTaxInvoice>() {
            @Override
            public void onResponse(Call<ResponseTaxInvoice> call, Response<ResponseTaxInvoice> response) {
                hideLoading();
                try {
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        //  showMessage(response.body().getMessage());
                        tvName.setText(response.body().getDisplayName() + "(" + response.body().getUserCode());
                        tvEmail.setText(response.body().getEmail());
                        tvMobile.setText(response.body().getMobile());
                        tvInvoiceNo.setText("Invoice No:-" + response.body().getInvoiceNo());
                        tvInvoiceNoDate.setText("Invoice Date:-" + response.body().getActivationDate());
                        tvProductName.setText(response.body().getEventName());
                        tvSgst.setText(response.body().getSgst());
                        tvCgst.setText(response.body().getCgst());
                        tvIgst.setText("0");

                        tvTotal.setText("Total :- " + response.body().getTotal());
                        tvTotalSaleValue.setText("Total Sale Value :-" + response.body().getValueBeforeTax() + ")");
                        tvAddText.setText("Add Taxt:-" + response.body().getTaxAdded());
                        tvTotalValueAfterText.setText("Add Value Before Taxt:-" + response.body().getValueBeforeTax());
                        tvGrandTotal.setText("Grand Total:- " + response.body().getTotalFinalAmount());
                        tvTotalValue.setText("Total value in Word:- " + response.body().getTotalFinalAmountWords());
                        tvQuantity.setText("Qty" + " (" + response.body().getQuantity() + ")");

                        //onBackPressed();
                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseTaxInvoice> call, Throwable t) {
                hideLoading();
            }
        });

    }


}
