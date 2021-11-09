package com.urdost.NFCActivity;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;
import static java.lang.Integer.parseInt;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.urdost.NFCActivity.ActivateNfcAddress;
import com.urdost.R;
import com.urdost.constants.BaseActivity;
import com.urdost.model.response.responseCheckTicketDetails.ResponseCheckTicket;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivateNfcDDCardDetails extends BaseActivity {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.textPlan)
    TextView textPlan;
    @BindView(R.id.tvPlan)
    TextView tvPlan;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.tv_quantity)
    EditText tvQuantity;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.tvReferral)
    TextView tvReferral;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.tv_Team_BV)
    TextView tvTeamBV;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    Bundle bundle;
    String tveventid;
    ResponseCheckTicket ticket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activate_buy_card);
        ButterKnife.bind(this);
        tvTitle.setText("NFC Detail");
        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);

        tvPlan.setText(bundle.getString("mrp"));
        tvPrice.setText(bundle.getString("mrp"));
        tvQuantity.setText(bundle.getString("NoItem"));
       tveventid=bundle.getString("id");
       // Toast.makeText(context,bundle.getString("id")+ "", Toast.LENGTH_SHORT).show();

        if (tvQuantity.getText().toString().trim().length() >0 && parseInt(tvQuantity.getText().toString()) >0) {
            tvTeamBV.setText(String.valueOf(Float.parseFloat(bundle.getString("TeamBv")) * Float.parseFloat(tvQuantity.getText().toString())));
            tvReferral.setText(String.valueOf(Float.parseFloat(bundle.getString("ReferBv")) * Float.parseFloat(tvQuantity.getText().toString())));
            tvPrice.setText(String.valueOf(Float.parseFloat(bundle.getString("mrp")) * Float.parseFloat(tvQuantity.getText().toString())));

        } else {
            showMessage("");
        }
        //tvQuantity.setText(bundle.getString("itemNo"));
        // tvReferral.setText(bundle.getString("TeamBv"));

        tvQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (tvQuantity.getText().toString().trim().length() >0 && parseInt(tvQuantity.getText().toString()) >0) {
                    tvTeamBV.setText(String.valueOf(Float.parseFloat(bundle.getString("TeamBv")) * Float.parseFloat(tvQuantity.getText().toString())));
                    tvReferral.setText(String.valueOf(Float.parseFloat(bundle.getString("ReferBv")) * Float.parseFloat(tvQuantity.getText().toString())));
                    tvPrice.setText(String.valueOf(Float.parseFloat(bundle.getString("mrp")) * Float.parseFloat(tvQuantity.getText().toString())));

                } else {
                    showMessage("");
                }
            }
        });






    }

    String tvquentity;
    @OnClick({R.id.img_back, R.id.btn_buy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.btn_buy:


                        Bundle bundle = new Bundle();
                        bundle.putString("NoOfSeet", tvQuantity.getText().toString().trim());
                        bundle.putString("mrp",tvPlan.getText().toString().trim());
                        bundle.putString("price",tvPrice.getText().toString().trim());
                        bundle.putString("ReferBv",tvReferral.getText().toString().trim());
                        bundle.putString("TeamBv",tvTeamBV.getText().toString().trim());
                        bundle.putString("eventid",tveventid);
                         if (tvQuantity.getText().toString().length()==0)
                         {
                             showMessage("Enter Number");
                         }else {
                             goToActivity(context, ActivateNfcAddress.class, bundle);

                         }

                break;
        }
    }
}
