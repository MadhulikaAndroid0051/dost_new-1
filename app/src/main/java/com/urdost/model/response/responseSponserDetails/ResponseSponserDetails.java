package com.urdost.model.response.responseSponserDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSponserDetails {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private SponserDetails sponserDetails;

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

    public SponserDetails getSponserDetails() {
        return sponserDetails;
    }

    public void setSponserDetails(SponserDetails sponserDetails) {
        this.sponserDetails = sponserDetails;
    }
}
