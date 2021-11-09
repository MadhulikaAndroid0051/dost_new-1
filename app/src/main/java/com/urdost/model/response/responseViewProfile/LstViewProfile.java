package com.urdost.model.response.responseViewProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstViewProfile {

    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("JoiningDate")
    @Expose
    private String joiningDate;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("AdharNo")
    @Expose
    private String adharNo;
    @SerializedName("EmailId")
    @Expose
    private String emailId;
    @SerializedName("SponsorId")
    @Expose
    private String sponsorId;
    @SerializedName("SponsorName")
    @Expose
    private String sponsorName;
    @SerializedName("AccountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("BankName")
    @Expose
    private String bankName;
    @SerializedName("BankBranch")
    @Expose
    private String bankBranch;
    @SerializedName("IFSC")
    @Expose
    private String ifsc;
    @SerializedName("ProfilePicture")
    @Expose
    private String profilePicture;
    @SerializedName("Summary")
    @Expose
    private String summary;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAdharNo() {
        return adharNo;
    }

    public void setAdharNo(String adharNo) {
        this.adharNo = adharNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}

