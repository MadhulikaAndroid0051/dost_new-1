package com.urdost.model.response.responseSubscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseSubscription {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstSubscription")
    @Expose
    private List<LstSubscription> lstSubscription = new ArrayList<LstSubscription>();

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

    public List<LstSubscription> getLstSubscription() {
        return lstSubscription;
    }

    public void setLstSubscription(List<LstSubscription> lstSubscription) {
        this.lstSubscription = lstSubscription;
    }

}