package com.urdost.model.response.WebLink;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstWeb {

    @SerializedName("Pk_NfcProfileId")
    @Expose
    private String pkNfcProfileId;
    @SerializedName("Link")
    @Expose
    private String link;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Redirection")
    @Expose
    private String redirection;
    @SerializedName("IsRedirect")
    @Expose
    private Boolean isRedirect;
    @SerializedName("IsIncluded")
    @Expose
    private Boolean isIncluded;
    @SerializedName("Fk_UserId")
    @Expose
    private String fkUserId;

    public String getPkNfcProfileId() {
        return pkNfcProfileId;
    }

    public void setPkNfcProfileId(String pkNfcProfileId) {
        this.pkNfcProfileId = pkNfcProfileId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRedirection() {
        return redirection;
    }

    public void setRedirection(String redirection) {
        this.redirection = redirection;
    }

    public Boolean getIsRedirect() {
        return isRedirect;
    }

    public void setIsRedirect(Boolean isRedirect) {
        this.isRedirect = isRedirect;
    }

    public Boolean getIsIncluded() {
        return isIncluded;
    }

    public void setIsIncluded(Boolean isIncluded) {
        this.isIncluded = isIncluded;
    }

    public String getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId;
    }

}
