package com.urdost.model.response.BusinessProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseBusinessProfile {


    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lst")
    @Expose
    private List<LstBusnessProfile> lst = new ArrayList<LstBusnessProfile>();

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

    public List<LstBusnessProfile> getLst() {
        return lst;
    }

    public void setLst(List<LstBusnessProfile> lst) {
        this.lst = lst;
    }
}
