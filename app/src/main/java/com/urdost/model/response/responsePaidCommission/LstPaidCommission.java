package com.urdost.model.response.responsePaidCommission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstPaidCommission {

    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("PaymentDate")
    @Expose
    private String paymentDate;
    @SerializedName("TransactionDate")
    @Expose
    private String transactionDate;
    @SerializedName("TransactionNo")
    @Expose
    private String transactionNo;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Amount")
    @Expose
    private String amount;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
