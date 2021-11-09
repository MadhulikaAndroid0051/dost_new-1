package com.urdost.model;

import com.google.gson.annotations.SerializedName;

public class ResponseStatusMessage {

    @SerializedName("Message")
    private String message;

    @SerializedName("StatusCode")
    private String statusCode;

    public String getMessage(){
        return message;
    }

    public String getStatusCode(){
        return statusCode;
    }
}