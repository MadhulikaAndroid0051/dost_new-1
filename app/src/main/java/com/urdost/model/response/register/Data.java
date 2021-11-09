package com.urdost.model.response.register;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("MobileNo")
    private String mobileNo;

    @SerializedName("LoginId")
    private String loginId;

    @SerializedName("UserCode")
    private String userCode;

    @SerializedName("Name")
    private String name;

    @SerializedName("Password")
    private String password;

    @SerializedName("UserType")
    private String UserType;

    @SerializedName("PK_UserId")
    private String PK_UserId;
    @SerializedName("IsDistributor")
    private String Distributor;
    @SerializedName("IsAcceptanceTNC")
    private String IsAcceptanceTNC;


    public String getUserType() {
        return UserType;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPK_UserId() {
        return PK_UserId;
    }

    public String getDistributor() {
        return Distributor;
    }

    public void setDistributor(String distributor) {
        Distributor = distributor;
    }

    public String getIsAcceptanceTNC() {
        return IsAcceptanceTNC;

    }

    public void setIsAcceptanceTNC(String isAcceptanceTNC) {
        IsAcceptanceTNC = isAcceptanceTNC;
    }
}