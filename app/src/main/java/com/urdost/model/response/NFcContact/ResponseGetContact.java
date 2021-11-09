package com.urdost.model.response.NFcContact;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseGetContact {


    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstContact")
    @Expose
    private List<LstContactNo> lstContact = new ArrayList<LstContactNo>();

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

    public List<LstContactNo> getLstContact() {
        return lstContact;
    }

    public void setLstContact(List<LstContactNo> lstContact) {
        this.lstContact = lstContact;
    }

}