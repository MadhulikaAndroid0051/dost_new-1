package com.urdost.model.response.DelaveredInvatory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseDelavierd {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstUnusedNFC")
    @Expose
    private List<LstDelaverd> lstUnusedNFC = new ArrayList<LstDelaverd>();

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

    public List<LstDelaverd> getLstUnusedNFC() {
        return lstUnusedNFC;
    }

    public void setLstUnusedNFC(List<LstDelaverd> lstUnusedNFC) {
        this.lstUnusedNFC = lstUnusedNFC;
    }
}
