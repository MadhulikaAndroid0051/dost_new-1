package com.urdost.model.response.NfcActivateBuyDDCard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseNfcActivate {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstnfc")
    @Expose
    private List<LstnfcDdCard> lstnfcDdCards = new ArrayList<LstnfcDdCard>();

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

    public List<LstnfcDdCard> getLstnfcDdCards() {
        return lstnfcDdCards;
    }

    public void setLstnfcDdCards(List<LstnfcDdCard> lstnfcDdCards) {
        this.lstnfcDdCards = lstnfcDdCards;
    }


}