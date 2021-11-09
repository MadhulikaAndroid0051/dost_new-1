package com.urdost.model.response.AvalableInvantory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseAvailable {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstUsedNFC")
    @Expose
    private List<LstInAvaliable> lstUsedNFC = new ArrayList<LstInAvaliable>();

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

    public List<LstInAvaliable> getLstUsedNFC() {
        return lstUsedNFC;
    }

    public void setLstUsedNFC(List<LstInAvaliable> lstUsedNFC) {
        this.lstUsedNFC = lstUsedNFC;
    }

}
