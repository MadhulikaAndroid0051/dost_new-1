package com.urdost.model.response.PurchaseOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponsePurchaseOrder {
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lst")
    @Expose
    private List<LstPurchaseOrder> lst = new ArrayList<LstPurchaseOrder>();

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

    public List<LstPurchaseOrder> getLst() {
        return lst;
    }

    public void setLst(List<LstPurchaseOrder> lst) {
        this.lst = lst;
    }
}
