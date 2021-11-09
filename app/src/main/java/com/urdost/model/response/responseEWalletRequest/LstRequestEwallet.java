package com.urdost.model.response.responseEWalletRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstRequestEwallet {

    @SerializedName("LoginId")
    @Expose
    private Object loginId;
    @SerializedName("Amount")
    @Expose
    private String amount;
    @SerializedName("PaymentMode")
    @Expose
    private String paymentMode;
    @SerializedName("DDChequeNo")
    @Expose
    private String dDChequeNo;
    @SerializedName("DDChequeDate")
    @Expose
    private String dDChequeDate;
    @SerializedName("BankBranch")
    @Expose
    private String bankBranch;
    @SerializedName("DocumentImg")
    @Expose
    private String documentImg;
    @SerializedName("BankName")
    @Expose
    private String bankName;
    @SerializedName("AddedBy")
    @Expose
    private Object addedBy;
    @SerializedName("RequestCode")
    @Expose
    private String requestCode;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("AddedOn")
    @Expose
    private String addedOn;

    public Object getLoginId() {
        return loginId;
    }

    public void setLoginId(Object loginId) {
        this.loginId = loginId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getDDChequeNo() {
        return dDChequeNo;
    }

    public void setDDChequeNo(String dDChequeNo) {
        this.dDChequeNo = dDChequeNo;
    }

    public String getDDChequeDate() {
        return dDChequeDate;
    }

    public void setDDChequeDate(String dDChequeDate) {
        this.dDChequeDate = dDChequeDate;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getDocumentImg() {
        return documentImg;
    }

    public void setDocumentImg(String documentImg) {
        this.documentImg = documentImg;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Object getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(Object addedBy) {
        this.addedBy = addedBy;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

}
