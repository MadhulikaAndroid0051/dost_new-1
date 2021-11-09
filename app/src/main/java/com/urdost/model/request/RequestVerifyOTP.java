package com.urdost.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestVerifyOTP {

    @SerializedName("MobileNo")
    private String mobileNo;

    @SerializedName("OTP")
    private String oTP;

    public void setMobileNo(String mobileNo){
        this.mobileNo = mobileNo;
    }

    public void setOTP(String oTP){
        this.oTP = oTP;
    }
}