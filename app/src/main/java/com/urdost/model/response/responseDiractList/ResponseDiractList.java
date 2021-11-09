package com.urdost.model.response.responseDiractList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDiractList {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstDirect")
    @Expose
    private List<LstDirect> lstDirect = null;

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

    public List<LstDirect> getLstDirect() {
        return lstDirect;
    }

    public void setLstDirect(List<LstDirect> lstDirect) {
        this.lstDirect = lstDirect;
    }

}
