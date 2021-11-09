package com.urdost.model.response.istributorCommissionHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseDistributorCommissionHistory {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstDistributorCommissionHistory")
    @Expose
    private List<LstDistributorCommissionHistory> lstDistributorCommissionHistory = new ArrayList<LstDistributorCommissionHistory>();

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

    public List<LstDistributorCommissionHistory> getLstDistributorCommissionHistory() {
        return lstDistributorCommissionHistory;
    }

    public void setLstDistributorCommissionHistory(List<LstDistributorCommissionHistory> lstDistributorCommissionHistory) {
        this.lstDistributorCommissionHistory = lstDistributorCommissionHistory;
    }

}
