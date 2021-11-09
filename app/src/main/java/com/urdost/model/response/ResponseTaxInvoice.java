package com.urdost.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseTaxInvoice {


    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("DisplayName")
    @Expose
    private String displayName;
    @SerializedName("UserCode")
    @Expose
    private String userCode;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("BusinessName")
    @Expose
    private String businessName;
    @SerializedName("InvoiceNo")
    @Expose
    private String invoiceNo;
    @SerializedName("ActivationDate")
    @Expose
    private String activationDate;
    @SerializedName("EventName")
    @Expose
    private String eventName;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Quantity")
    @Expose
    private String quantity;
    @SerializedName("ProductPrice")
    @Expose
    private String productPrice;
    @SerializedName("SGST")
    @Expose
    private String sgst;
    @SerializedName("CGST")
    @Expose
    private String cgst;
    @SerializedName("Total")
    @Expose
    private String total;
    @SerializedName("TotalFinalAmount")
    @Expose
    private String totalFinalAmount;
    @SerializedName("TotalFinalAmountWords")
    @Expose
    private String totalFinalAmountWords;
    @SerializedName("ValueBeforeTax")
    @Expose
    private String valueBeforeTax;
    @SerializedName("TaxAdded")
    @Expose
    private String taxAdded;

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getSgst() {
        return sgst;
    }

    public void setSgst(String sgst) {
        this.sgst = sgst;
    }

    public String getCgst() {
        return cgst;
    }

    public void setCgst(String cgst) {
        this.cgst = cgst;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalFinalAmount() {
        return totalFinalAmount;
    }

    public void setTotalFinalAmount(String totalFinalAmount) {
        this.totalFinalAmount = totalFinalAmount;
    }

    public String getTotalFinalAmountWords() {
        return totalFinalAmountWords;
    }

    public void setTotalFinalAmountWords(String totalFinalAmountWords) {
        this.totalFinalAmountWords = totalFinalAmountWords;
    }

    public String getValueBeforeTax() {
        return valueBeforeTax;
    }

    public void setValueBeforeTax(String valueBeforeTax) {
        this.valueBeforeTax = valueBeforeTax;
    }

    public String getTaxAdded() {
        return taxAdded;
    }

    public void setTaxAdded(String taxAdded) {
        this.taxAdded = taxAdded;
    }
}
