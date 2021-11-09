package com.urdost.model.response.responseSubscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstSubscription {

    @SerializedName("SubscriptionId")
    @Expose
    private String subscriptionId;
    @SerializedName("SubscriptionName")
    @Expose
    private String subscriptionName;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("EndDate")
    @Expose
    private String endDate;
    @SerializedName("NoOfSeats")
    @Expose
    private String noOfSeats;
    @SerializedName("SubscriptionImage")
    @Expose
    private String subscriptionImage;
    @SerializedName("SubscriptionDescription")
    @Expose
    private String subscriptionDescription;
    @SerializedName("LoginId")
    @Expose
    private String loginId;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("offername")
    @Expose
    private String offername;
    @SerializedName("OfferValidity")
    @Expose
    private String offerValidity;
    @SerializedName("CouponCode")
    @Expose
    private String couponCode;
    @SerializedName("IGST")
    @Expose
    private String igst;
    @SerializedName("CGST")
    @Expose
    private String cgst;
    @SerializedName("SGST")
    @Expose
    private String sgst;
    @SerializedName("ReferralPercentage")
    @Expose
    private String referralPercentage;
    @SerializedName("BinaryPercentage")
    @Expose
    private String binaryPercentage;
    @SerializedName("DeliveryCharges")
    @Expose
    private String deliveryCharges;
    @SerializedName("OfferPrice")
    @Expose
    private String offerPrice;
    @SerializedName("Discount")
    @Expose
    private String discount;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getSubscriptionImage() {
        return subscriptionImage;
    }

    public void setSubscriptionImage(String subscriptionImage) {
        this.subscriptionImage = subscriptionImage;
    }

    public String getSubscriptionDescription() {
        return subscriptionDescription;
    }

    public void setSubscriptionDescription(String subscriptionDescription) {
        this.subscriptionDescription = subscriptionDescription;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getOffername() {
        return offername;
    }

    public void setOffername(String offername) {
        this.offername = offername;
    }

    public String getOfferValidity() {
        return offerValidity;
    }

    public void setOfferValidity(String offerValidity) {
        this.offerValidity = offerValidity;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getIgst() {
        return igst;
    }

    public void setIgst(String igst) {
        this.igst = igst;
    }

    public String getCgst() {
        return cgst;
    }

    public void setCgst(String cgst) {
        this.cgst = cgst;
    }

    public String getSgst() {
        return sgst;
    }

    public void setSgst(String sgst) {
        this.sgst = sgst;
    }

    public String getReferralPercentage() {
        return referralPercentage;
    }

    public void setReferralPercentage(String referralPercentage) {
        this.referralPercentage = referralPercentage;
    }

    public String getBinaryPercentage() {
        return binaryPercentage;
    }

    public void setBinaryPercentage(String binaryPercentage) {
        this.binaryPercentage = binaryPercentage;
    }

    public String getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(String deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

}
