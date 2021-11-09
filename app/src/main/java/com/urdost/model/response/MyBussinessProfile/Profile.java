package com.urdost.model.response.MyBussinessProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("Pk_UserId")
    @Expose
    private String pkUserId;
    @SerializedName("Code")
    @Expose
    private Object code;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("DOB")
    @Expose
    private String dob;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("ProfilePic")
    @Expose
    private String profilePic;
    @SerializedName("Summary")
    @Expose
    private String summary;
    @SerializedName("UserCode")
    @Expose
    private String userCode;
    @SerializedName("Leg")
    @Expose
    private String leg;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPkUserId() {
        return pkUserId;
    }

    public void setPkUserId(String pkUserId) {
        this.pkUserId = pkUserId;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getLeg() {
        return leg;
    }

    public void setLeg(String leg) {
        this.leg = leg;
    }

}
