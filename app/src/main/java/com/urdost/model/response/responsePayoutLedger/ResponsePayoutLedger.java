package com.urdost.model.response.responsePayoutLedger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponsePayoutLedger {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstPayout")
    @Expose
    private List<LstPayoutLedger> lstPayoutLedgers = new ArrayList<LstPayoutLedger>();

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

    public List<LstPayoutLedger> getLstPayoutLedgers() {
        return lstPayoutLedgers;
    }

    public void setLstPayoutLedgers(List<LstPayoutLedger> lstPayout) {
        this.lstPayoutLedgers = lstPayoutLedgers;
    }
}
