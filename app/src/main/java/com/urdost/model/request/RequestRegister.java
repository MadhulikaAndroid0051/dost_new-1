package com.urdost.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestRegister {

    @SerializedName("SponsorId")
    private String sponsorId;

    @SerializedName("MobileNo")
    private String mobileNo;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("DOB")
    private String dOB;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("Gender")
    private String gender;

    @SerializedName("Pincode")
    private String pincode;

    @SerializedName("Leg")
    private String leg;

    @SerializedName("Password")
    private String password;

    public void setSponsorId(String sponsorId){
        this.sponsorId = sponsorId;
    }

    public void setMobileNo(String mobileNo){
        this.mobileNo = mobileNo;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setDOB(String dOB){
        this.dOB = dOB;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setPincode(String pincode){
        this.pincode = pincode;
    }

    public void setLeg(String leg){
        this.leg = leg;
    }

    public void setPassword(String password){
        this.password = password;
    }
}