package com.urdost.model.response.responseUsedCode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseUsedCode {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstTopUp")
    @Expose
    private List<LstUsedCode> lstUsedCodes = new ArrayList<LstUsedCode>();

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

    public List<LstUsedCode> getLstUsedCodes() {
        return lstUsedCodes;
    }

    public void setLstUsedCodes(List<LstUsedCode> lstUsedCodes) {
        this.lstUsedCodes = lstUsedCodes;
    }

}
