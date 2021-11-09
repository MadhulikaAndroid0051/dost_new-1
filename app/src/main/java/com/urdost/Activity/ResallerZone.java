package com.urdost.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.JsonObject;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.constants.BaseFragment;
import com.urdost.model.response.ResponseBusinessD;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResallerZone extends BaseFragment {


    @BindView(R.id.img_user)
    ImageView imgUser;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.tv_last_login)
    TextView tvLastLogin;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.tv_doj)
    TextView tvDoj;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.tv_doa)
    TextView tvDoa;
    @BindView(R.id.img_card)
    ImageView imgCard;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.tv_user_mobile)
    TextView tvUserMobile;
    @BindView(R.id.textView10)
    TextView textView10;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.tv_unclear_bal)
    TextView tvUnclearBal;
    @BindView(R.id.textview11)
    TextView textview11;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.tv_team_size)
    TextView tvTeamSize;
    @BindView(R.id.textview12)
    TextView textview12;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.tv_commission)
    TextView tvCommission;
    @BindView(R.id.textview13)
    TextView textview13;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.tv_today_app)
    TextView tvTodayApp;
    @BindView(R.id.textview14)
    TextView textview14;
    @BindView(R.id.view7)
    View view7;
    @BindView(R.id.tv_today_prime)
    TextView tvTodayPrime;
    @BindView(R.id.textview15)
    TextView textview15;
    @BindView(R.id.view17)
    View view17;
    @BindView(R.id.tv_total_team)
    TextView tvTotalTeam;
    @BindView(R.id.container1)
    LinearLayout container1;
    @BindView(R.id.tv_diract)
    TextView tvDiract;
    @BindView(R.id.container12)
    LinearLayout container12;
    @BindView(R.id.tv_activate_ids)
    TextView tvActivateIds;
    @BindView(R.id.tv_total_payout)
    TextView tvTotalPayout;
    @BindView(R.id.container)
    LinearLayout container;
    @BindView(R.id.textView101)
    TextView textView101;
    @BindView(R.id.tvlegType)
    TextView tvlegType;
    @BindView(R.id.tvlegActive)
    TextView tvlegActive;
    @BindView(R.id.tvLegInactive)
    TextView tvLegInactive;
    @BindView(R.id.totalmembers)
    TextView totalmembers;
    @BindView(R.id.totalbusiness)
    TextView totalbusiness;
    @BindView(R.id.constrOne)
    ConstraintLayout constrOne;
    @BindView(R.id.tvLeft)
    TextView tvLeft;
    @BindView(R.id.total_left)
    TextView totalLeft;
    @BindView(R.id.paid_left)
    TextView paidLeft;
    @BindView(R.id.cf_left)
    TextView cfLeft;
    @BindView(R.id.totalbuis_left)
    TextView totalbuisLeft;
    @BindView(R.id.constrTwo)
    ConstraintLayout constrTwo;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.total_right)
    TextView totalRight;
    @BindView(R.id.paid_right)
    TextView paidRight;
    @BindView(R.id.cf_right)
    TextView cfRight;
    @BindView(R.id.totalbuis_right)
    TextView totalbuisRight;
    @BindView(R.id.constrThree)
    ConstraintLayout constrThree;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.active_total)
    TextView activeTotal;
    @BindView(R.id.inactive_total)
    TextView inactiveTotal;
    @BindView(R.id.totalmembers_total)
    TextView totalmembersTotal;
    @BindView(R.id.totalbuis_total)
    TextView totalbuisTotal;
    @BindView(R.id.constrFour)
    ConstraintLayout constrFour;
    @BindView(R.id.cv_business)
    CardView cvBusiness;
    @BindView(R.id.textView1011)
    TextView textView1011;
    @BindView(R.id.tvlegType1)
    TextView tvlegType1;
    @BindView(R.id.tvlegActive1)
    TextView tvlegActive1;
    @BindView(R.id.tvLegInactive1)
    TextView tvLegInactive1;
    @BindView(R.id.totalmembers1)
    TextView totalmembers1;
    @BindView(R.id.totalbusiness1)
    TextView totalbusiness1;
    @BindView(R.id.constrOne1)
    ConstraintLayout constrOne1;
    @BindView(R.id.tvLeft1)
    TextView tvLeft1;
    @BindView(R.id.total_left1)
    TextView totalLeft1;
    @BindView(R.id.paid_left1)
    TextView paidLeft1;
    @BindView(R.id.cf_left1)
    TextView cfLeft1;
    @BindView(R.id.totalbuis_left1)
    TextView totalbuisLeft1;
    @BindView(R.id.constrTwo1)
    ConstraintLayout constrTwo1;
    @BindView(R.id.tvRight1)
    TextView tvRight1;
    @BindView(R.id.total_right1)
    TextView totalRight1;
    @BindView(R.id.paid_right1)
    TextView paidRight1;
    @BindView(R.id.cf_right1)
    TextView cfRight1;
    @BindView(R.id.totalbuis_right1)
    TextView totalbuisRight1;
    @BindView(R.id.constrThree1)
    ConstraintLayout constrThree1;
    @BindView(R.id.total1)
    TextView total1;
    @BindView(R.id.active_total1)
    TextView activeTotal1;
    @BindView(R.id.inactive_total1)
    TextView inactiveTotal1;
    @BindView(R.id.totalmembers_total1)
    TextView totalmembersTotal1;
    @BindView(R.id.totalbuis_total1)
    TextView totalbuisTotal1;
    @BindView(R.id.constrFour1)
    ConstraintLayout constrFour1;
    @BindView(R.id.cv_business1)
    CardView cvBusiness1;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.tv_copy_refercode)
    TextView tvCopyRefercode;
    @BindView(R.id.cl_kyc)
    ConstraintLayout clKyc;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.tv_my_team)
    TextView tvMyTeam;
    @BindView(R.id.tv_ref_code)
    TextView tvRefCode;
    @BindView(R.id.cl_refer)
    ConstraintLayout clRefer;
    @BindView(R.id.img_prime)
    ImageView imgPrime;
    @BindView(R.id.tv_prime)
    TextView tvPrime;
    @BindView(R.id.tv_products)
    TextView tvProducts;
    @BindView(R.id.img_invite_people)
    ImageView imgInvitePeople;
    @BindView(R.id.cl_sai)
    ConstraintLayout clSai;
    @BindView(R.id.img_club_dashboard)
    ImageView imgClubDashboard;
    @BindView(R.id.tv_club_dashboard)
    TextView tvClubDashboard;
    @BindView(R.id.tv_earn_crypto)
    TextView tvEarnCrypto;
    @BindView(R.id.cl_club_dashboard)
    ConstraintLayout clClubDashboard;

    Unbinder unbinder;
    @BindView(R.id.tv_products1)
    TextView tvProducts1;
    @BindView(R.id.cl_Purchase_Order)
    ConstraintLayout clPurchaseOrder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_mlm_dashboard, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (NetworkUtils.getConnectivityStatus(context) != 0) {
            getData();
        } else showMessage(R.string.alert_internet);
        return view;
    }

    private void getData() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        //object.addProperty("LoginId",PreferencesManager.getInstance(context).getMobileno());
        LoggerUtil.logItem(object);

        Call<ResponseBusinessD> call = apiServices.getBussines(object);
        call.enqueue(new Callback<ResponseBusinessD>() {
            @Override
            public void onResponse(Call<ResponseBusinessD> call, Response<ResponseBusinessD> response) {
                hideLoading();
                try {
                    LoggerUtil.logItem(response.body());
                    if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                        tvUserId.setText(response.body().getUserId());
                        tvDoj.setText(response.body().getDoj());
                        tvDoa.setText(response.body().getDoa());
                        tvUserMobile.setText(response.body().getMobileNo());
                        tvLastLogin.setText(response.body().getLastLogin());
                        tvUnclearBal.setText(response.body().getTotalPayout());
                        tvTeamSize.setText(response.body().getTotalTeam());
                        tvCommission.setText(response.body().getTotalDirect());
                        tvTodayApp.setText(response.body().getTodaysEarning());
                        tvTodayPrime.setText(response.body().getTodaysTeam());
                        tvUserName.setText(response.body().getName());

                        //
                        tvTotalTeam.setText(response.body().getTotalReseller());
                        tvDiract.setText(response.body().getTotalDistributor());
                        tvActivateIds.setText(response.body().getTotalAirDrop());
                        tvTotalPayout.setText(response.body().getTotalIncome());
                        //
                        totalLeft.setText(response.body().getTotalBusinessLeft());
                        totalRight.setText(response.body().getTotalBusinessRight());
                        paidLeft.setText(response.body().getPaidBusinessLeft());
                        paidRight.setText(response.body().getPaidBusinessRight());
                        cfLeft.setText(response.body().getCarryLeft());
                        cfRight.setText(response.body().getCarryRight());
                        //
                        totalLeft1.setText(response.body().getCurrentTotalBusinessLeft());
                        totalRight1.setText(response.body().getCurrentTotalBusinessRight());
                        paidLeft1.setText(response.body().getCurrentPaidBusinessLeft());
                        paidRight1.setText(response.body().getCurrentPaidBusinessRight());
                        cfLeft1.setText(response.body().getCurrentCarryLeft());
                        cfRight1.setText(response.body().getCurrentCarryRight());

                    } else showMessage(response.body().getMessage());
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<ResponseBusinessD> call, Throwable t) {
                hideLoading();
            }
        });
    }

    @OnClick({R.id.cl_kyc, R.id.cl_refer, R.id.cl_sai, R.id.cl_club_dashboard,R.id.cl_Purchase_Order})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.cl_kyc:
                goToActivity(DiractList.class, null);
                break;
            case R.id.cl_refer:
                goToActivity(ActivityTree.class, null);

                break;
            case R.id.cl_sai:
                goToActivity(ReferEarn.class, null);
                break;
            case R.id.cl_club_dashboard:
                break;
            case R.id.cl_Purchase_Order:
                goToActivity(PurchaseOrderActivity.class,null);
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                goToActivityWithFinish(NewContainerActivity.class, null);

                // H]andle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    @OnClick(R.id.cl_Purchase_Order)
    public void onClick() {
    }
}
