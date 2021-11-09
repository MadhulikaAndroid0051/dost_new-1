package com.urdost.model.response.responseEwalletLedger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstWallet {
    @SerializedName("SrNo")
    @Expose
    private String SrNo;
    @SerializedName("AddedOn")
    @Expose
    private String addedOn;
    @SerializedName("CrAmount")
    @Expose
    private String crAmount;
    @SerializedName("DrAmount")
    @Expose
    private String drAmount;
    @SerializedName("EwalletBalance")
    @Expose
    private String ewalletBalance;
    @SerializedName("Narration")
    @Expose
    private String narration;

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public String getCrAmount() {
        return crAmount;
    }

    public void setCrAmount(String crAmount) {
        this.crAmount = crAmount;
    }

    public String getDrAmount() {
        return drAmount;
    }

    public void setDrAmount(String drAmount) {
        this.drAmount = drAmount;
    }

    public String getEwalletBalance() {
        return ewalletBalance;
    }

    public void setEwalletBalance(String ewalletBalance) {
        this.ewalletBalance = ewalletBalance;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }
}
