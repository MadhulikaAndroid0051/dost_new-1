package com.urdost.model.response;

import com.google.gson.annotations.SerializedName;

public class ResponseTerms {
    @SerializedName("Message")
    private String message;

    @SerializedName("Data")
    private String data;

    @SerializedName("StatusCode")
    private Integer statusCode;

    public String getMessage(){
        return message;
    }

    public String getData(){
        return data;
    }

    public Integer getStatusCode(){
        return statusCode;
    }
}
