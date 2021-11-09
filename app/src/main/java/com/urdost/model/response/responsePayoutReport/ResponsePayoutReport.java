package com.urdost.model.response.responsePayoutReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponsePayoutReport {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstPayout")
    @Expose
    private List<LstPayout> lstPayout = new ArrayList<LstPayout>();

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

    public List<LstPayout> getLstPayout() {
        return lstPayout;
    }

    public void setLstPayout(List<LstPayout> lstPayout) {
        this.lstPayout = lstPayout;
    }


}

