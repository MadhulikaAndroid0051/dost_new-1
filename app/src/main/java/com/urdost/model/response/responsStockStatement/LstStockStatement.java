package com.urdost.model.response.responsStockStatement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstStockStatement {

    @SerializedName("Narration")
    @Expose
    private String narration;
    @SerializedName("DrAmount")
    @Expose
    private String drAmount;
    @SerializedName("CrAmount")
    @Expose
    private String crAmount;
    @SerializedName("AddedOn")
    @Expose
    private String addedOn;
    @SerializedName("EwalletBalance")
    @Expose
    private String ewalletBalance;

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getDrAmount() {
        return drAmount;
    }

    public void setDrAmount(String drAmount) {
        this.drAmount = drAmount;
    }

    public String getCrAmount() {
        return crAmount;
    }

    public void setCrAmount(String crAmount) {
        this.crAmount = crAmount;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getEwalletBalance() {
        return ewalletBalance;
    }

    public void setEwalletBalance(String ewalletBalance) {
        this.ewalletBalance = ewalletBalance;
    }

}
