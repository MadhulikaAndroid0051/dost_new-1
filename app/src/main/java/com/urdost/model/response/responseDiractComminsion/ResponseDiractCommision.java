package com.urdost.model.response.responseDiractComminsion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseDiractCommision {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstDirectCommission")
    @Expose
    private List<LstDirectCommission> lstDirectCommission = new ArrayList<LstDirectCommission>();

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

    public List<LstDirectCommission> getLstDirectCommission() {
        return lstDirectCommission;
    }

    public void setLstDirectCommission(List<LstDirectCommission> lstDirectCommission) {
        this.lstDirectCommission = lstDirectCommission;
    }

}
