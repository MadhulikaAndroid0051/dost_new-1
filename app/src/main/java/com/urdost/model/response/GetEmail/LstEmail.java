package com.urdost.model.response.GetEmail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstEmail {

    @SerializedName("Pk_NfcProfileId")
    @Expose
    private String pkNfcProfileId;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("IsIncluded")
    @Expose
    private Boolean isIncluded;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Fk_UserId")
    @Expose
    private String fkUserId;

    public String getPkNfcProfileId() {
        return pkNfcProfileId;
    }

    public void setPkNfcProfileId(String pkNfcProfileId) {
        this.pkNfcProfileId = pkNfcProfileId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsIncluded() {
        return isIncluded;
    }

    public void setIsIncluded(Boolean isIncluded) {
        this.isIncluded = isIncluded;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId;
    }

}
