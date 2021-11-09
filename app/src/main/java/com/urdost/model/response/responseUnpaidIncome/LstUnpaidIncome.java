package com.urdost.model.response.responseUnpaidIncome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstUnpaidIncome {

    @SerializedName("FromLoginId")
    @Expose
    private String fromLoginId;
    @SerializedName("FromUserName")
    @Expose
    private String fromUserName;
    @SerializedName("IncomeType")
    @Expose
    private String incomeType;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Amount")
    @Expose
    private String amount;

    public String getFromLoginId() {
        return fromLoginId;
    }

    public void setFromLoginId(String fromLoginId) {
        this.fromLoginId = fromLoginId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}