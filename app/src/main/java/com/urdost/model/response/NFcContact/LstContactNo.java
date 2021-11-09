package com.urdost.model.response.NFcContact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstContactNo {
    @SerializedName("Pk_NfcProfileId")
    @Expose
    private String pkNfcProfileId;
    @SerializedName("Contact")
    @Expose
    private String contact;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("IsIncluded")
    @Expose
    private Boolean isIncluded;
    @SerializedName("IsWhatsup")
    @Expose
    private Boolean isWhatsup;
    @SerializedName("Fk_UserId")
    @Expose
    private String fkUserId;

    public String getPkNfcProfileId() {
        return pkNfcProfileId;
    }

    public void setPkNfcProfileId(String pkNfcProfileId) {
        this.pkNfcProfileId = pkNfcProfileId;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsIncluded() {
        return isIncluded;
    }

    public void setIsIncluded(Boolean isIncluded) {
        this.isIncluded = isIncluded;
    }

    public Boolean getIsWhatsup() {
        return isWhatsup;
    }

    public void setIsWhatsup(Boolean isWhatsup) {
        this.isWhatsup = isWhatsup;
    }

    public String getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId;
    }

}