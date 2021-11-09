package com.urdost.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.urdost.NFCActivity.ActivateNfcDDCardDetails;
import com.urdost.R;
import com.urdost.constants.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.urdost.app.AppConfig.PAYLOAD_BUNDLE;

public class NfcFullImageDetails extends BaseActivity {

    Bundle bundle;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.textViewProductName)
    TextView textViewProductName;
    @BindView(R.id.tv_refer_bv)
    TextView tvReferBv;
    @BindView(R.id.tv_team_bv)
    TextView tvTeamBv;
    @BindView(R.id.textViewProductPrice)
    TextView textViewProductPrice;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    @BindView(R.id.add_to_card)
    Button addToCard;
    @BindView(R.id.add_text1)
    Button addText1;
    @BindView(R.id.add_text)
    Button addText;
    @BindView(R.id.btn_buy)
    Button btnBuy;
    private String img;
    public int count=1;
    String tvRf,TvBv,tvpr,tvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc_details);
        ButterKnife.bind(this);
        bundle = getIntent().getBundleExtra(PAYLOAD_BUNDLE);
        img = bundle.getString("img");
        tvRf=bundle.getString("ReferBv");
        TvBv=bundle.getString("TeamBv");
        tvId=bundle.getString("itemId");
        tvpr= bundle.getString("Price");
        tvTitle.setText(bundle.getString("EventName"));

        Glide.with(context).load("https://dost.click/" + img).into(imageview);
        textViewProductName.setText(bundle.getString("EventName"));
        tvReferBv.setText("Bv :" + bundle.getString("ReferBv"));
        tvTeamBv.setText("Bv: " + bundle.getString("TeamBv"));
        tvPrice.setText("MRP: " + bundle.getString("Price"));
        textViewProductPrice.setText("Discount: " + bundle.getString("Discount"));

        // Toast.makeText(context,img+ "", Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.img_back, R.id.add_to_card,R.id.add_text1, R.id.add_text, R.id.btn_buy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.add_to_card:

                // goToActivityWithFinish(context,ActivateNfcDDCards.class,null);
                break;
            case R.id.add_text1:
                if (addToCard.getText().toString().equalsIgnoreCase("1")) {
                    showMessage("");
                }else
                count--;

                addToCard.setText(String.valueOf(count));
                break;
            case R.id.add_text:


                count++;
                addToCard.setText(String.valueOf(count));

                break;
            case R.id.btn_buy:
                Bundle bundle = new Bundle();
                bundle.putString("id", tvId);
                bundle.putString("mrp",tvpr);
                bundle.putString("ReferBv",tvRf);
                bundle.putString("TeamBv",TvBv);
                bundle.putString("NoItem",addToCard.getText().toString().trim());

                bundle.putString("eventName",textViewProductName.getText().toString().trim());

                goToActivity(context, ActivateNfcDDCardDetails.class,bundle);

                break;
        }
    }

}
