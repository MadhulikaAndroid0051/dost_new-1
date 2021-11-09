package com.urdost.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGetSponseStatus {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("SponsorName")
    @Expose
    private String sponsorName;
    @SerializedName("FK_SponosrId")
    @Expose
    private String fKSponosrId;
    @SerializedName("SponsorCode")
    @Expose
    private String sponsorCode;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String sponsorName) {
        this.sponsorName = sponsorName;
    }

    public String getFKSponosrId() {
        return fKSponosrId;
    }

    public void setFKSponosrId(String fKSponosrId) {
        this.fKSponosrId = fKSponosrId;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

}
