package com.urdost.model.response.istributorCommissionHistory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstDistributorCommissionHistory {

    @SerializedName("Narration")
    @Expose
    private String narration;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("date")
    @Expose
    private String date;

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
