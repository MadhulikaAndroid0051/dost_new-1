package com.urdost.model.response.register;

import com.google.gson.annotations.SerializedName;

public class ResponseRegistration{

    @SerializedName("Message")
    private String message;

    @SerializedName("Data")
    private Data data;

    @SerializedName("StatusCode")
    private String statusCode;

    public String getMessage(){
        return message;
    }

    public Data getData(){
        return data;
    }

    public String getStatusCode(){
        return statusCode;
    }
}