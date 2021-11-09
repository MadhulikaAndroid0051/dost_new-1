package com.urdost.model.response.WebLink;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseWebLink {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstWeb")
    @Expose
    private List<LstWeb> lstWeb = new ArrayList<LstWeb>();

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

    public List<LstWeb> getLstWeb() {
        return lstWeb;
    }

    public void setLstWeb(List<LstWeb> lstWeb) {
        this.lstWeb = lstWeb;
    }

}
