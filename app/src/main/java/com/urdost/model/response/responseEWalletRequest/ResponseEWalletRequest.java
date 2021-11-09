package com.urdost.model.response.responseEWalletRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseEWalletRequest {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstEwallet")
    @Expose
    private List<LstRequestEwallet> lstEwallet = new ArrayList<LstRequestEwallet>();

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

    public List<LstRequestEwallet> getLstEwallet() {
        return lstEwallet;
    }

    public void setLstEwallet(List<LstRequestEwallet> lstEwallet) {
        this.lstEwallet = lstEwallet;
    }

}
