package com.urdost.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestCheckMobile {

    @SerializedName("MobileNo")
    private String mobileNo;

    @SerializedName("DeviceId")
    private String deviceId;

    public void setMobileNo(String mobileNo){
        this.mobileNo = mobileNo;
    }

    public void setDeviceId(String deviceId){
        this.deviceId = deviceId;
    }
}