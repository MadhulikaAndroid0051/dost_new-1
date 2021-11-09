package com.urdost.model.response.responseUnusedCoupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseUnUsedCoupon {


    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstunusedcoupons")
    @Expose
    private List<Lstunusedcoupon> lstunusedcoupons = null;

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

    public List<Lstunusedcoupon> getLstunusedcoupons() {
        return lstunusedcoupons;
    }

    public void setLstunusedcoupons(List<Lstunusedcoupon> lstunusedcoupons) {
        this.lstunusedcoupons = lstunusedcoupons;
    }


}
