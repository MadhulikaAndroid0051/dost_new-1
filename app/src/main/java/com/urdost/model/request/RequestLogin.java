package com.urdost.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestLogin {

    @SerializedName("MobileNo")
    private String mobileNo;

    @SerializedName("DeviceId")
    private String deviceId;

    @SerializedName("Password")
    private String password;

    public void setMobileNo(String mobileNo){
        this.mobileNo = mobileNo;
    }

    public void setDeviceId(String deviceId){
        this.deviceId = deviceId;
    }

    public void setPassword(String password){
        this.password = password;
    }
}