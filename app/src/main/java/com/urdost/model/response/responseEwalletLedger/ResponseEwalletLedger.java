package com.urdost.model.response.responseEwalletLedger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseEwalletLedger {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstWallet")
    @Expose
    private List<LstWallet> lstWallet = new ArrayList<LstWallet>();

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<LstWallet> getLstWallet() {
        return lstWallet;
    }

    public void setLstWallet(List<LstWallet> lstWallet) {
        this.lstWallet = lstWallet;
    }


}
