package com.urdost.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckStatusResallerZone {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("IsAcceptanceTNC")
    @Expose
    private Boolean isAcceptanceTNC;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getIsAcceptanceTNC() {
        return isAcceptanceTNC;
    }

    public void setIsAcceptanceTNC(Boolean isAcceptanceTNC) {
        this.isAcceptanceTNC = isAcceptanceTNC;
    }

}
