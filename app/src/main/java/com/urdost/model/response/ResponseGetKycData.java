package com.urdost.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGetKycData {
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("UserID")
    @Expose
    private Object userID;
    @SerializedName("AdharNumber")
    @Expose
    private String adharNumber;
    @SerializedName("AdharImage")
    @Expose
    private String adharImage;
    @SerializedName("AdharStatus")
    @Expose
    private String adharStatus;
    @SerializedName("PanNumber")
    @Expose
    private String panNumber;
    @SerializedName("PanImage")
    @Expose
    private String panImage;
    @SerializedName("PanStatus")
    @Expose
    private String panStatus;
    @SerializedName("DocumentNumber")
    @Expose
    private String documentNumber;
    @SerializedName("DocumentImage")
    @Expose
    private String documentImage;
    @SerializedName("DocumentStatus")
    @Expose
    private String documentStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getUserID() {
        return userID;
    }

    public void setUserID(Object userID) {
        this.userID = userID;
    }

    public String getAdharNumber() {
        return adharNumber;
    }

    public void setAdharNumber(String adharNumber) {
        this.adharNumber = adharNumber;
    }

    public String getAdharImage() {
        return adharImage;
    }

    public void setAdharImage(String adharImage) {
        this.adharImage = adharImage;
    }

    public String getAdharStatus() {
        return adharStatus;
    }

    public void setAdharStatus(String adharStatus) {
        this.adharStatus = adharStatus;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getPanImage() {
        return panImage;
    }

    public void setPanImage(String panImage) {
        this.panImage = panImage;
    }

    public String getPanStatus() {
        return panStatus;
    }

    public void setPanStatus(String panStatus) {
        this.panStatus = panStatus;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public void setDocumentImage(String documentImage) {
        this.documentImage = documentImage;
    }

    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

}
