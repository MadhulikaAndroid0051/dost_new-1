package com.urdost.model.response.BuyNfc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstBuyNfc {
    @SerializedName("PK_EventId")
    @Expose
    private String pKEventId;
    @SerializedName("EventName")
    @Expose
    private String eventName;
    @SerializedName("EventLocation")
    @Expose
    private String eventLocation;
    @SerializedName("EventDescription")
    @Expose
    private String eventDescription;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("EndDate")
    @Expose
    private String endDate;
    @SerializedName("EventImage")
    @Expose
    private String eventImage;
    @SerializedName("NoOfSeats")
    @Expose
    private String noOfSeats;
    @SerializedName("ProductPrice")
    @Expose
    private String productPrice;
    @SerializedName("OfferPrice")
    @Expose
    private String offerPrice;
    @SerializedName("Discount")
    @Expose
    private String discount;
    @SerializedName("DeliverCharge")
    @Expose
    private String deliverCharge;
    @SerializedName("ReferralPercent")
    @Expose
    private String referralPercent;
    @SerializedName("GST")
    @Expose
    private String gst;

    public String getPKEventId() {
        return pKEventId;
    }

    public void setPKEventId(String pKEventId) {
        this.pKEventId = pKEventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
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

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
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

    public String getDeliverCharge() {
        return deliverCharge;
    }

    public void setDeliverCharge(String deliverCharge) {
        this.deliverCharge = deliverCharge;
    }

    public String getReferralPercent() {
        return referralPercent;
    }

    public void setReferralPercent(String referralPercent) {
        this.referralPercent = referralPercent;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }
}