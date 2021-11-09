package com.urdost.model.response.GeSocialMediaLink;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstEmail {
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
    @SerializedName("Fk_UserId")
    @Expose
    private String fkUserId;
    @SerializedName("IsIncluded")
    @Expose
    private Boolean isIncluded;
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

    public String getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId;
    }

    public Boolean getRedirect() {
        return isRedirect;
    }

    public void setRedirect(Boolean redirect) {
        isRedirect = redirect;
    }

    public Boolean getIncluded() {
        return isIncluded;
    }

    public void setIncluded(Boolean included) {
        isIncluded = included;
    }
}
