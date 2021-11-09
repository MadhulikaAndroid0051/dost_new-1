package com.urdost.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseCheckResallerStatus {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("IsAcceptanceTNC")
    @Expose
    private String isAcceptanceTNC;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getIsAcceptanceTNC() {
        return isAcceptanceTNC;
    }

    public void setIsAcceptanceTNC(String isAcceptanceTNC) {
        this.isAcceptanceTNC = isAcceptanceTNC;
    }

}
