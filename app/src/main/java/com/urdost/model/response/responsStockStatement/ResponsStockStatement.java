package com.urdost.model.response.responsStockStatement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponsStockStatement {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstStockStatement")
    @Expose
    private List<LstStockStatement> lstStockStatement = new ArrayList<LstStockStatement>();

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

    public List<LstStockStatement> getLstStockStatement() {
        return lstStockStatement;
    }

    public void setLstStockStatement(List<LstStockStatement> lstStockStatement) {
        this.lstStockStatement = lstStockStatement;
    }


}
