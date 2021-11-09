package com.urdost.model.response.responseUnpaidIncome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseUnpaidIncome {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private List<LstUnpaidIncome> data = new ArrayList<LstUnpaidIncome>();

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

    public List<LstUnpaidIncome> getData() {
        return data;
    }

    public void setData(List<LstUnpaidIncome> data) {
        this.data = data;
    }

}
