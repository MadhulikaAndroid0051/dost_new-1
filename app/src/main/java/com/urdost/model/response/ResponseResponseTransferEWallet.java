package com.urdost.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseResponseTransferEWallet {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;

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

}
