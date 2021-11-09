package com.urdost.model.response.AvalableInvantory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstInAvaliable {

    @SerializedName("SrNo")
    @Expose
    private String SrNo;
    @SerializedName("PK_NFCId")
    @Expose
    private String pKNFCId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("ActivatedOn")
    @Expose
    private String activatedOn;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("NFCStatus")
    @Expose
    private String nFCStatus;

    public String getPKNFCId() {
        return pKNFCId;
    }

    public void setPKNFCId(String pKNFCId) {
        this.pKNFCId = pKNFCId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getActivatedOn() {
        return activatedOn;
    }

    public void setActivatedOn(String activatedOn) {
        this.activatedOn = activatedOn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNFCStatus() {
        return nFCStatus;
    }

    public void setNFCStatus(String nFCStatus) {
        this.nFCStatus = nFCStatus;
}

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getpKNFCId() {
        return pKNFCId;
    }

    public void setpKNFCId(String pKNFCId) {
        this.pKNFCId = pKNFCId;
    }

    public String getnFCStatus() {
        return nFCStatus;
    }

    public void setnFCStatus(String nFCStatus) {
        this.nFCStatus = nFCStatus;
    }
}

