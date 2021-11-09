package com.urdost.model.response.responseFranchiseWalletStatement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseFranchiseWalletStatement {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstFranchiseWalletStatement")
    @Expose
    private List<LstFranchiseWalletStatement> lstFranchiseWalletStatement = new ArrayList<LstFranchiseWalletStatement>();

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

    public List<LstFranchiseWalletStatement> getLstFranchiseWalletStatement() {
        return lstFranchiseWalletStatement;
    }

    public void setLstFranchiseWalletStatement(List<LstFranchiseWalletStatement> lstFranchiseWalletStatement) {
        this.lstFranchiseWalletStatement = lstFranchiseWalletStatement;
    }

}
