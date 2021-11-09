package com.urdost.model.response.sponsorName;

import com.google.gson.annotations.SerializedName;

public class ResponseSponsor{

    @SerializedName("Message")
    private String message;
    @SerializedName("Data")
    private Data data;

    @SerializedName("StatusCode")
    private Integer statusCode;

    public String getMessage(){
        return message;
    }

    public Data getData(){
        return data;
    }

    public Integer getStatusCode(){
        return statusCode;
    }
}
