package com.urdost.model.response.BuyNfc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBuyNfc {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Data")
    @Expose
    private LstBuyNfc lstBuyNfc;

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

    public LstBuyNfc getLstBuyNfc() {
        return lstBuyNfc;
    }

    public void setLstBuyNfc(LstBuyNfc lstBuyNfc) {
        this.lstBuyNfc = lstBuyNfc;
    }
}