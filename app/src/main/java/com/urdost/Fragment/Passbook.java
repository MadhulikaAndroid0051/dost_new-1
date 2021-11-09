package com.urdost.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.urdost.Activity.NewContainerActivity;
import com.urdost.Activity.Transfer_Ewallet;
import com.urdost.Activity.WalletRequest;
import com.urdost.Activity.Withdrawal;
import com.urdost.Adapter.AdapterCommissionHistory;
import com.urdost.Adapter.AdapterDiractCommison;
import com.urdost.Adapter.AdapterEwalletLedger;
import com.urdost.Adapter.AdapterFranchiseWalletStatement;
import com.urdost.Adapter.AdapterPaidCommission;
import com.urdost.Adapter.AdapterPayoutLedger;
import com.urdost.Adapter.AdapterPayoutReport;
import com.urdost.Adapter.AdapterStockStatement;
import com.urdost.Adapter.AdapterUnpaidIncome;
import com.urdost.R;
import com.urdost.app.PreferencesManager;
import com.urdost.common.HidingScrollListener;
import com.urdost.common.LoggerUtil;
import com.urdost.common.NetworkUtils;
import com.urdost.common.Utils;
import com.urdost.constants.BaseFragment;
import com.urdost.model.response.istributorCommissionHistory.LstDistributorCommissionHistory;
import com.urdost.model.response.istributorCommissionHistory.ResponseDistributorCommissionHistory;
import com.urdost.model.response.responsStockStatement.LstStockStatement;
import com.urdost.model.response.responsStockStatement.ResponsStockStatement;
import com.urdost.model.response.responseDiractComminsion.LstDirectCommission;
import com.urdost.model.response.responseDiractComminsion.ResponseDiractCommision;
import com.urdost.model.response.responseEwalletLedger.LstWallet;
import com.urdost.model.response.responseEwalletLedger.ResponseEwalletLedger;
import com.urdost.model.response.responseFranchiseWalletStatement.LstFranchiseWalletStatement;
import com.urdost.model.response.responseFranchiseWalletStatement.ResponseFranchiseWalletStatement;
import com.urdost.model.response.responsePaidCommission.LstPaidCommission;
import com.urdost.model.response.responsePaidCommission.ResponsePaidCommission1;
import com.urdost.model.response.responsePassbook.ResponsePassbook;
import com.urdost.model.response.responsePayoutLedger.LstPayoutLedger;
import com.urdost.model.response.responsePayoutLedger.ResponsePayoutLedger;
import com.urdost.model.response.responsePayoutReport.LstPayout;
import com.urdost.model.response.responseUnpaidIncome.LstUnpaidIncome;
import com.urdost.model.response.responseUnpaidIncome.ResponseUnpaidIncome;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Passbook extends BaseFragment {
    Bundle bundle;

    @BindView(R.id.tv_wallet_amt)
    TextView tvWalletAmt;
    @BindView(R.id.tv_coin_amt)
    TextView tvCoinAmt;
    @BindView(R.id.tv_distributor_amt)
    TextView tvDistributorAmt;

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.img_wallet_more)
    ImageView imgWalletMore;
    @BindView(R.id.img_coin_more)
    ImageView imgCoinMore;
    @BindView(R.id.img_distributor_more)
    ImageView imgDistributorMore;
    @BindView(R.id.cv_dw_wallet)
    CardView cvDwWallet;
    @BindView(R.id.cv_commission)
    CardView cvCommission;
    @BindView(R.id.cv_hold)
    CardView cvHold;
    @BindView(R.id.tv_statmentSelect)
    TextView tvStatmentSelect;
    @BindView(R.id.btn_search1)
    ImageView btnSearch1;


    private Unbinder unbinder;
    String dataitem = "0";
    private int pageNo = 1;
    private int pageSize = 30;
    private boolean once = true;
    private LinearLayoutManager layoutManager;
    BottomSheetDialog searchDialog;

    private List<LstDirectCommission> lstDirectCommission = new ArrayList<LstDirectCommission>();
    AdapterDiractCommison adapterDiractCommison;

    private List<LstPaidCommission> lstPaidCommissions = new ArrayList<>();
    AdapterPaidCommission adapterPaidCommission;

    private List<LstStockStatement> lstStockStatement = new ArrayList<>();
    AdapterStockStatement adapterStockStatement;

    private List<LstFranchiseWalletStatement> lstFranchiseWalletStatements = new ArrayList<>();
    AdapterFranchiseWalletStatement adapterFranchiseWalletStatement;

    private List<LstDistributorCommissionHistory> lstDistributorCommissionHistories = new ArrayList<>();
    AdapterCommissionHistory adapterCommissionHistory;

    private List<LstWallet> lstWallets = new ArrayList<>();
    AdapterEwalletLedger adapterEwalletLedger;

    private List<LstPayoutLedger> lstPayoutLedgers = new ArrayList<>();
    AdapterPayoutLedger adapterPayoutLedger;

    private List<LstUnpaidIncome> lstUnpaidIncomes = new ArrayList<>();
    AdapterUnpaidIncome adapterUnpaidIncome;

    private List<LstPayout> lstPayoutts = new ArrayList<>();
    AdapterPayoutReport adapterPayoutReport;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_passbook, container, false);
        unbinder = ButterKnife.bind(this, view);

        if (NetworkUtils.getConnectivityStatus(context) != 0) {
            new Handler().postDelayed(new Runnable() {


                @Override

                public void run() {
                    getPassbook();
                }
            }, 100);

        } else showMessage(R.string.alert_internet);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(layoutManager);
        rvList.setHasFixedSize(true);
        getEwalletLedger();
        btnSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchhDialog();
            }
        });

        /*tvStatmentSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupCoin1 = new PopupMenu(context, tvStatmentSelect);
                popupCoin1.getMenuInflater().inflate(R.menu.menu_statment, popupCoin1.getMenu());
                popupCoin1.setOnMenuItemClickListener(item -> {
                    if (item.getTitle().equals("Referral Income"))
                        dataitem = "1";
                    else if (item.getTitle().equals("Binary Income "))
                        dataitem = "2";
                    else if (item.getTitle().equals("Distributor Income"))
                        dataitem = "3";
                    else if (item.getTitle().equals("REferral Distributor Income"))
                        dataitem = "4";

                    tvStatmentSelect.setText(item.getTitle());

                    return true;
                });
                popupCoin1.show();
            }
        });
        return view;
    }*/
        return view;
    }

    @OnClick({R.id.cv_dw_wallet, R.id.cv_commission, R.id.cv_hold})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cv_dw_wallet:
                PopupMenu popup = new PopupMenu(context, cvDwWallet);
                popup.getMenuInflater().inflate(R.menu.menu_wallet, popup.getMenu());
                popup.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.tv_add_fund:
                            goToActivity(WalletRequest.class,bundle);
                            break;
                        case R.id.tv_transfer:
                            goToActivity(Transfer_Ewallet.class, bundle);
                            break;
                        case R.id.tv_withdrawal:
                            goToActivity(Withdrawal.class, bundle);
                            break;
                        case R.id.tv_wallet_statement:
                            tvStatmentSelect.setText(item.getTitle());
                            pageNo = 1;
                            btnSearch1.setVisibility(View.VISIBLE);
                            getEwalletLedger();
                            break;
                        case R.id.tv_earning_statement:
                            ///  tvListName.setText(item.getTitle());
                            pageNo = 1;
                            getUnpaidIncome();
                            break;
                        case R.id.tv_direct_commmission:
                            //  tvListName.setText(item.getTitle());
                            pageNo = 1;
                            getDirectCommission();
                            break;
                        case R.id.tv_commission:
                            //tvListName.setText(item.getTitle());
                            pageNo = 1;
                            //getPayoutLedger();
                            getEwalletLedger();
                            break;
                        case R.id.tv_paid_commission:
                            // tvListName.setText(item.getTitle());
                            pageNo = 1;
                            getPaidCommission();
                            break;
                    }

                    return true;
                });
                popup.show();
                break;
            case R.id.cv_commission:
                PopupMenu popupCoin = new PopupMenu(context, cvCommission);
                popupCoin.getMenuInflater().inflate(R.menu.menu_coin, popupCoin.getMenu());
                popupCoin.setOnMenuItemClickListener(item -> {
                    tvStatmentSelect.setText(item.getTitle());
                    pageNo = 1;
                    btnSearch1.setVisibility(View.GONE);
                    getStockStatement();
                    return true;
                });
                popupCoin.show();
                break;
            case R.id.cv_hold:
                PopupMenu popupDis = new PopupMenu(context, cvHold);
                popupDis.getMenuInflater().inflate(R.menu.menu_distributor, popupDis.getMenu());
                popupDis.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.tv_add_fund:
                            goToActivity(WalletRequest.class, null);
                            break;
                        case R.id.tv_commission_history:
                            //tvListName.setText(item.getTitle());
                            pageNo = 1;
                            getCommissionHistory();
                            // goToActivity(Transfer_Ewallet.class, bundle);
                            break;
                        case R.id.tv_wallet_statement:
                            // tvListName.setText(item.getTitle());
                            pageNo = 1;
                            getFranchiseWalletStatement();
                            break;
                    }
                    return true;
                });
                popupDis.show();
                break;
        }
    }

    private void updateScroll() {
        layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvList.setLayoutManager(layoutManager);
        rvList.setHasFixedSize(true);
        rvList.addOnScrollListener(new HidingScrollListener(layoutManager) {
            @Override
            public void onHide() {
            }

            @Override
            public void onLoadMore(int i) {
                if (NetworkUtils.getConnectivityStatus(context) != 0) {
                     if (tvStatmentSelect.getText().toString().equalsIgnoreCase("Stock Statement")) {
                        if (lstStockStatement.size() == pageNo * pageSize && once) {
                            once = false;
                            pageNo = i;
                            getStockStatement();
                        }
                    }/* else if (tvListName.getText().toString().equalsIgnoreCase("Distributor Wallet Statement")) {
                        if (lstFranchiseWalletStatements.size() == pageNo * pageSize && once) {
                            once = false;
                            pageNo = i;
                            getFranchiseWalletStatement();
                        }
                    } else if (tvListName.getText().toString().equalsIgnoreCase("Earning Statement")) {
                        if (lstUnpaidIncomes.size() == pageNo * pageSize && once) {
                            once = false;
                            pageNo = i;
                            getUnpaidIncome();
                        }
                    } else if (tvListName.getText().toString().equalsIgnoreCase("Direct Commission")) {
                        if (lstDirectCommission.size() == pageNo * pageSize && once) {
                            once = false;
                            pageNo = i;
                            getDirectCommission();
                        }
                    } else if (tvListName.getText().toString().equalsIgnoreCase(" Commission")) {
                        if (lstWallets.size() == pageNo * pageSize && once) {
                            once = false;
                            pageNo = i;
                            getEwalletLedger();
                        }
                    } else if (tvListName.getText().toString().equalsIgnoreCase(" Paid Commission")) {
                        if (lstPaidCommissions.size() == pageNo * pageSize && once) {
                            once = false;
                            pageNo = i;
                            getPaidCommission();
                        }
                    } else if (tvStatmentSelect.getText().toString().equalsIgnoreCase(" Commission History")) {
                        if (lstDistributorCommissionHistories.size() == pageNo * pageSize && once) {
                            once = false;
                            pageNo = i;
                            getCommissionHistory();
                        }
                    }*/

                }
            }

            @Override
            public void onShow() {
            }
        });
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    private void getPassbook() {
        // showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        Call<ResponsePassbook> call = apiServices.getPassbook(object);
        call.enqueue(new Callback<ResponsePassbook>() {
            @Override
            public void onResponse(Call<ResponsePassbook> call, Response<ResponsePassbook> response) {
                //hideLoading();
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    tvWalletAmt.setText(response.body().getData().getWalletBalance());
                    tvCoinAmt.setText(response.body().getData().getCoin());
                    tvDistributorAmt.setText(response.body().getData().getFranchiseeBalance());
                } else showMessage(response.body().getMessage());
            }

            @Override
            public void onFailure(Call<ResponsePassbook> call, Throwable t) {
                //hideLoading();
            }
        });
    }

    private void getDirectCommission() {
        // showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getMobileno());
        object.addProperty("PageNO", pageNo);
        Call<ResponseDiractCommision> call = apiServices.getDiractCommison(object);
        call.enqueue(new Callback<ResponseDiractCommision>() {
            @Override
            public void onResponse(Call<ResponseDiractCommision> call, Response<ResponseDiractCommision> response) {
                // hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    if (pageNo == 1) {
                        // updateScroll();
                        rvList.setVisibility(View.VISIBLE);
                        lstDirectCommission = response.body().getLstDirectCommission();
                        adapterDiractCommison = new AdapterDiractCommison(lstDirectCommission, context);
                        rvList.setAdapter(adapterDiractCommison);
                    } else {
                        rvList.setVisibility(View.VISIBLE);
                        lstDirectCommission.addAll(response.body().getLstDirectCommission());
                        adapterDiractCommison.updatelist(lstDirectCommission);
                    }
                } else {
                    if (pageNo == 1) {
                        rvList.setVisibility(View.GONE);
                    }
                    showMessage("No data found!");
                }
            }

            @Override
            public void onFailure(Call<ResponseDiractCommision> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void getStockStatement() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PageNO", pageNo);
        Call<ResponsStockStatement> call = apiServices.getstockStatment(object);
        call.enqueue(new Callback<ResponsStockStatement>() {
            @Override
            public void onResponse(Call<ResponsStockStatement> call, Response<ResponsStockStatement> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    if (pageNo == 1) {
                        //updateScroll();
                        rvList.setVisibility(View.VISIBLE);
                        lstStockStatement = response.body().getLstStockStatement();
                        adapterStockStatement = new AdapterStockStatement(lstStockStatement, context);
                        rvList.setAdapter(adapterStockStatement);
                    } else {
                        rvList.setVisibility(View.VISIBLE);
                        lstStockStatement.addAll(response.body().getLstStockStatement());
                        adapterStockStatement.updatelist(lstStockStatement);
                    }
                } else {
                    if (pageNo == 1) {
                        rvList.setVisibility(View.GONE);
                    }
                    showMessage("No data found!");
                }
            }

            @Override
            public void onFailure(Call<ResponsStockStatement> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void getPaidCommission() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getMobileno());
        object.addProperty("PageNO", "1");
        Call<ResponsePaidCommission1> call = apiServices.getPaidCommission(object);
        call.enqueue(new Callback<ResponsePaidCommission1>() {
            @Override
            public void onResponse(Call<ResponsePaidCommission1> call, Response<ResponsePaidCommission1> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    if (pageNo == 1) {
                        updateScroll();
                        rvList.setVisibility(View.VISIBLE);
                        lstPaidCommissions = response.body().getLstPaidCommission();
                        adapterPaidCommission = new AdapterPaidCommission(lstPaidCommissions, context);
                        rvList.setAdapter(adapterPaidCommission);
                    } else {
                        rvList.setVisibility(View.VISIBLE);
                        lstPaidCommissions.addAll(response.body().getLstPaidCommission());
                        adapterPaidCommission.updatelist(lstPaidCommissions);
                    }
                } else {
                    if (pageNo == 1) {
                        rvList.setVisibility(View.GONE);
                    }
                    showMessage("No data found!");
                }
            }

            @Override
            public void onFailure(Call<ResponsePaidCommission1> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void getFranchiseWalletStatement() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PageNO", pageNo);
        Call<ResponseFranchiseWalletStatement> call = apiServices.getFranchiseWalletStatement(object);
        call.enqueue(new Callback<ResponseFranchiseWalletStatement>() {
            @Override
            public void onResponse(Call<ResponseFranchiseWalletStatement> call, Response<ResponseFranchiseWalletStatement> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    if (pageNo == 1) {
                        //updateScroll();
                        rvList.setVisibility(View.VISIBLE);
                        lstFranchiseWalletStatements = response.body().getLstFranchiseWalletStatement();
                        adapterFranchiseWalletStatement = new AdapterFranchiseWalletStatement(lstFranchiseWalletStatements, context);
                        rvList.setAdapter(adapterFranchiseWalletStatement);
                    } else {
                        rvList.setVisibility(View.VISIBLE);
                        lstFranchiseWalletStatements.addAll(response.body().getLstFranchiseWalletStatement());
                        adapterFranchiseWalletStatement.updatelist(lstFranchiseWalletStatements);
                    }
                } else {
                    if (pageNo == 1) {
                        rvList.setVisibility(View.GONE);
                    }
                    showMessage("No data found!");
                }
            }

            @Override
            public void onFailure(Call<ResponseFranchiseWalletStatement> call, Throwable t) {
                hideLoading();
            }
        });
    }

    private void getCommissionHistory() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("Fk_UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PageNO", pageNo);
        Call<ResponseDistributorCommissionHistory> call = apiServices.getCommissionHistory(object);
        call.enqueue(new Callback<ResponseDistributorCommissionHistory>() {
            @Override
            public void onResponse(Call<ResponseDistributorCommissionHistory> call, Response<ResponseDistributorCommissionHistory> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    if (pageNo == 1) {
                        //updateScroll();
                        rvList.setVisibility(View.VISIBLE);
                        lstDistributorCommissionHistories = response.body().getLstDistributorCommissionHistory();
                        adapterCommissionHistory = new AdapterCommissionHistory(lstDistributorCommissionHistories, context);
                        rvList.setAdapter(adapterCommissionHistory);
                    } else {
                        rvList.setVisibility(View.VISIBLE);
                        lstDistributorCommissionHistories.addAll(response.body().getLstDistributorCommissionHistory());
                        adapterCommissionHistory.updatelist(lstDistributorCommissionHistories);
                    }
                } else {
                    if (pageNo == 1) {
                        rvList.setVisibility(View.GONE);
                    }
                    showMessage("No data found!");
                }
            }

            @Override
            public void onFailure(Call<ResponseDistributorCommissionHistory> call, Throwable t) {
                hideLoading();
            }
        });
    }

    public void getEwalletLedger() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PageNO", pageNo);

        LoggerUtil.logItem(object);
        Call<ResponseEwalletLedger> call = apiServices.getEwalletledger(object);
        call.enqueue(new Callback<ResponseEwalletLedger>() {
            @Override
            public void onResponse(Call<ResponseEwalletLedger> call, Response<ResponseEwalletLedger> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                   // showMessage(response.body().getMessage());
                    updateScroll();
                     rvList.setVisibility(View.VISIBLE);
                    lstWallets = response.body().getLstWallet();
                    AdapterEwalletLedger adapterEwalletLedger1 = new AdapterEwalletLedger(lstWallets, context);
                    rvList.setAdapter(adapterEwalletLedger1);
                } else
                    showMessage(response.body().getMessage());

            }

            @Override
            public void onFailure(Call<ResponseEwalletLedger> call, Throwable t) {
                hideLoading();
            }

        });
    }

    public void getPayoutLedger() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PageNo", "1");
        LoggerUtil.logItem(object);
        Call<ResponsePayoutLedger> call = apiServices.getPayoutLedger(object);
        call.enqueue(new Callback<ResponsePayoutLedger>() {
            @Override
            public void onResponse(Call<ResponsePayoutLedger> call, Response<ResponsePayoutLedger> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    if (pageNo == 1) {
                        //updateScroll();
                        rvList.setVisibility(View.VISIBLE);
                        lstPayoutLedgers = response.body().getLstPayoutLedgers();
                        adapterPayoutLedger = new AdapterPayoutLedger(lstPayoutLedgers, context);
                        rvList.setAdapter(adapterPayoutLedger);
                    } else {
                        rvList.setVisibility(View.VISIBLE);
                        lstPayoutLedgers.addAll(response.body().getLstPayoutLedgers());
                        adapterPayoutLedger.updatelist(lstPayoutLedgers);
                    }
                } else {
                    if (pageNo == 1) {
                        rvList.setVisibility(View.GONE);
                    }
                    showMessage("No data found!");
                }
            }

            @Override
            public void onFailure(Call<ResponsePayoutLedger> call, Throwable t) {
                hideLoading();
            }

        });
    }

    public void getUnpaidIncome() {
        showLoading();
        JsonObject object = new JsonObject();
        object.addProperty("LoginId", PreferencesManager.getInstance(context).getMobileno());
        object.addProperty("PageNo", "1");
        LoggerUtil.logItem(object);
        Call<ResponseUnpaidIncome> call = apiServices.getUnpaidIncome(object);
        call.enqueue(new Callback<ResponseUnpaidIncome>() {
            @Override
            public void onResponse(Call<ResponseUnpaidIncome> call, Response<ResponseUnpaidIncome> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    if (pageNo == 1) {
                        //updateScroll();
                        rvList.setVisibility(View.VISIBLE);
                        lstUnpaidIncomes = response.body().getData();
                        adapterUnpaidIncome = new AdapterUnpaidIncome(lstUnpaidIncomes, context);
                        rvList.setAdapter(adapterUnpaidIncome);
                    } else {
                        rvList.setVisibility(View.VISIBLE);
                        lstUnpaidIncomes.addAll(response.body().getData());
                        adapterUnpaidIncome.updatelist(lstUnpaidIncomes);
                    }
                } else {
                    if (pageNo == 1) {
                        rvList.setVisibility(View.GONE);
                    }
                    showMessage("No data found!");
                }
            }

            @Override
            public void onFailure(Call<ResponseUnpaidIncome> call, Throwable t) {
                hideLoading();
            }

        });
    }

    /* public void getPayoutReport() {
         showLoading();
         JsonObject object=new JsonObject();
         object.addProperty("LoginId","9919339472");
         object.addProperty("PageNo","1");
         LoggerUtil.logItem(object);
         Call<ResponsePayoutReport> call = apiServices.getPayoutReports(object);
         call.enqueue(new Callback<ResponsePayoutReport>() {
             @Override
             public void onResponse(Call<ResponsePayoutReport> call, Response<ResponsePayoutReport> response) {
                 hideLoading();
                 LoggerUtil.logItem(response.body());
                 if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                     if (pageNo == 1) {
                         updateScroll();
                         rvList.setVisibility(View.VISIBLE);
                         lstPayoutts = response.body().getLstPayout();
                         adapterPayoutReport = new AdapterPayoutReport(lstPayoutts, context);
                         rvList.setAdapter(adapterPayoutReport);
                     } else {
                         rvList.setVisibility(View.VISIBLE);
                         lstPayoutts.addAll(response.body().getLstPayout());
                         adapterPayoutReport.updatelist(lstPayoutts);
                     }
                 } else {
                     if (pageNo == 1) {
                         rvList.setVisibility(View.GONE);
                     }
                     showMessage("No data found!");
                 } }

             @Override
             public void onFailure(Call<ResponsePayoutReport> call, Throwable t) {
                 hideLoading();
             }

         });
     }*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                goToActivityWithFinish(NewContainerActivity.class, null);

                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }

    String status = "0";

    private void searchhDialog() {
        searchDialog = new BottomSheetDialog(context);
        View sheetView = context.getLayoutInflater().inflate(R.layout.dilog_wallet_stalment, null);
        searchDialog.setContentView(sheetView);
        status = "0";
        TextView tv_start_date = sheetView.findViewById(R.id.tv_start_date);
        TextView tv_end_date = sheetView.findViewById(R.id.tv_end_date);
        TextView tv_status = sheetView.findViewById(R.id.tv_statmentSelect);
        Button btn_cancel = sheetView.findViewById(R.id.btn_cancel);
        Button btn_search1 = sheetView.findViewById(R.id.btn_search);
        btn_cancel.setOnClickListener(v -> {
            searchDialog.dismiss();
        });

        tv_start_date.setOnClickListener(v -> {
            datePicker(tv_start_date);
            tv_end_date.setText("");
        });

        tv_end_date.setOnClickListener(v -> {
            if (tv_start_date.getText().toString().equalsIgnoreCase(""))
                showMessage("Select Start Date");
            else
                datePicker(tv_end_date);
        });


        tv_status.setOnClickListener(v -> {
            android.widget.PopupMenu popup_sidemenu = new android.widget.PopupMenu(context, tv_status);
            popup_sidemenu.getMenuInflater().inflate(R.menu.menu_statment, popup_sidemenu.getMenu());
            popup_sidemenu.setOnMenuItemClickListener(item -> {
                if (item.getTitle().equals("Income Type"))
                    status = "0";
                else if (item.getTitle().equals("All Income"))
                    status="0";
                else if (item.getTitle().equals("Referral Income"))
                    status = "1";
                else if (item.getTitle().equals("Binary Income"))
                    status = "2";
                else if (item.getTitle().equals("Distributor Income"))
                    status = "3";
                else if (item.getTitle().equals("Referral Distributor Income"))
                    status = "4";
                tv_status.setText(item.getTitle());
                return true;
            });
            popup_sidemenu.show();
        });

        btn_search1.setOnClickListener(v -> {
            searchDialog.dismiss();
            getDataSearch(tv_start_date.getText().toString().trim(),
                    tv_end_date.getText().toString().trim(),
                    status);
        });

        searchDialog.setCancelable(false);
        searchDialog.setCanceledOnTouchOutside(false);
        searchDialog.show();
    }

    private void datePicker(final TextView et) {
        Calendar cal = Calendar.getInstance();
        int mYear, mMonth, mDay;

        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(context, (view, year, monthOfYear, dayOfMonth) -> {
            et.setText(Utils.changeDateFormat(dayOfMonth, monthOfYear, year));
        }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(cal.getTimeInMillis());
        datePickerDialog.show();
    }



    public void getDataSearch(String startdate, String enddate, String incometype) {
        JsonObject object = new JsonObject();
        object.addProperty("UserId", PreferencesManager.getInstance(context).getPk_userId());
        object.addProperty("PageNO", pageNo);
        object.addProperty("FK_PlanId", incometype);
        object.addProperty("FromDate",startdate);
        object.addProperty("ToDate", enddate);


        LoggerUtil.logItem(object);
        Call<ResponseEwalletLedger> call = apiServices.getEwalletledger(object);
        call.enqueue(new Callback<ResponseEwalletLedger>() {
            @Override
            public void onResponse(Call<ResponseEwalletLedger> call, Response<ResponseEwalletLedger> response) {
                hideLoading();
                LoggerUtil.logItem(response.body());
                if (response.body().getStatusCode().equalsIgnoreCase("200")) {
                    //showMessage(response.body().getMessage());
                    //updateScroll();
                     rvList.setVisibility(View.VISIBLE);
                    lstWallets = response.body().getLstWallet();
                    AdapterEwalletLedger adapterEwalletLedger1 = new AdapterEwalletLedger(lstWallets, context);
                    rvList.setAdapter(adapterEwalletLedger1);
                    rvList.setVisibility(View.VISIBLE);
                } else {
                    rvList.setVisibility(View.GONE);
                    showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseEwalletLedger> call, Throwable t) {
                hideLoading();
            }

        });

    }


    @OnClick(R.id.btn_search1)
    public void onClick() {
        searchhDialog();
    }
}