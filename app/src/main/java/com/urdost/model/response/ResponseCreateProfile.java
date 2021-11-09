package com.urdost.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseCreateProfile {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("PK_ProfileId")
    @Expose
    private String pKProfileId;

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

    public String getPKProfileId() {
        return pKProfileId;
    }

    public void setPKProfileId(String pKProfileId) {
        this.pKProfileId = pKProfileId;
    }

}
