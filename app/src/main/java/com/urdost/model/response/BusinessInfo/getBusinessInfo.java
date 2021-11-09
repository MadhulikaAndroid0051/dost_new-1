package com.urdost.model.response.BusinessInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class getBusinessInfo {
    @SerializedName("BusinessName")
    @Expose
    private String businessName;
    @SerializedName("BusinessContact")
    @Expose
    private String businessContact;
    @SerializedName("BusinessMail")
    @Expose
    private String businessMail;
    @SerializedName("About")
    @Expose
    private String about;
    @SerializedName("Description")
    @Expose
    private String description;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessContact() {
        return businessContact;
    }

    public void setBusinessContact(String businessContact) {
        this.businessContact = businessContact;
    }

    public String getBusinessMail() {
        return businessMail;
    }

    public void setBusinessMail(String businessMail) {
        this.businessMail = businessMail;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}