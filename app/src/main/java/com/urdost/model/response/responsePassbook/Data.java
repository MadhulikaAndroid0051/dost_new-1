package com.urdost.model.response.responsePassbook;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {


    @SerializedName("WalletBalance")
    @Expose
    private String walletBalance;
    @SerializedName("Coin")
    @Expose
    private String coin;
    @SerializedName("FranchiseeBalance")
    @Expose
    private String franchiseeBalance;

    public String getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(String walletBalance) {
        this.walletBalance = walletBalance;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getFranchiseeBalance() {
        return franchiseeBalance;
    }

    public void setFranchiseeBalance(String franchiseeBalance) {
        this.franchiseeBalance = franchiseeBalance;
    }


}
