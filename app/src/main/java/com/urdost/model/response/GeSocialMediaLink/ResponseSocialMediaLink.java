package com.urdost.model.response.GeSocialMediaLink;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseSocialMediaLink {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstEmail")
    @Expose
    private List<LstEmail> lstEmail = new ArrayList<LstEmail>();

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

    public List<LstEmail> getLstEmail() {
        return lstEmail;
    }

    public void setLstEmail(List<LstEmail> lstEmail) {
        this.lstEmail = lstEmail;
    }

}