package com.urdost.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestAddDistributor {

    @SerializedName("Designation")
    private String designation;

    @SerializedName("Email")
    private String email;

    @SerializedName("CompanySize")
    private String companySize;

    @SerializedName("GST")
    private String gST;

    @SerializedName("Website")
    private String website;

    @SerializedName("CompanyType")
    private String companyType;

    @SerializedName("Mobile")
    private String mobile;

    @SerializedName("YourName")
    private String yourName;

    @SerializedName("CompanyAddress")
    private String companyAddress;

    @SerializedName("CompanyName")
    private String companyName;

    @SerializedName("Fk_UserId")
    private String fkUserId;

    @SerializedName("PAN")
    private String pAN;

    @SerializedName("AboutCompany")
    private String aboutCompany;

    public void setDesignation(String designation){
        this.designation = designation;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setCompanySize(String companySize){
        this.companySize = companySize;
    }

    public void setGST(String gST){
        this.gST = gST;
    }

    public void setWebsite(String website){
        this.website = website;
    }

    public void setCompanyType(String companyType){
        this.companyType = companyType;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public void setYourName(String yourName){
        this.yourName = yourName;
    }

    public void setCompanyAddress(String companyAddress){
        this.companyAddress = companyAddress;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    public void setFkUserId(String fkUserId){
        this.fkUserId = fkUserId;
    }

    public void setPAN(String pAN){
        this.pAN = pAN;
    }

    public void setAboutCompany(String aboutCompany){
        this.aboutCompany = aboutCompany;
    }
}