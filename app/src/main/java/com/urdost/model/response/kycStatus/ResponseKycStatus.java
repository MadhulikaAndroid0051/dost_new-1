package com.urdost.model.response.kycStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseKycStatus {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private GetKycData getKycData;

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

    public GetKycData getGetKycData() {
        return getKycData;
    }

    public void setGetKycData(GetKycData kycData) {
        this.getKycData = kycData;
    }
}
