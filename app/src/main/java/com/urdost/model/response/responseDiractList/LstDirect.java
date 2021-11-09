package com.urdost.model.response.responseDiractList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstDirect {

    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("JoiningDate")
    @Expose
    private String joiningDate;
    @SerializedName("Leg")
    @Expose
    private String leg;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Package")
    @Expose
    private String _package;
    @SerializedName("PermanentDate")
    @Expose
    private String permanentDate;
    @SerializedName("SponsorId")
    @Expose
    private String sponsorId;
    @SerializedName("SponsorName")
    @Expose
    private String sponsorName;
    @SerializedName("Status")
    @Expose
    private String status;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getLeg() {
        return leg;
    }

    public void setLeg(String leg) {
        this.leg = leg;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

    public String getPermanentDate() {
        return permanentDate;
    }

    public void setPermanentDate(String permanentDate) {
        this.permanentDate = permanentDate;
    }

    public String getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(String sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
