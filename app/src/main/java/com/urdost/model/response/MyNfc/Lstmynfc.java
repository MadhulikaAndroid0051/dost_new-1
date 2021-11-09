package com.urdost.model.response.MyNfc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lstmynfc {
    @SerializedName("PK_EventId")
    @Expose
    private String pKEventId;
    @SerializedName("EventName")
    @Expose
    private String eventName;
    @SerializedName("Offername")
    @Expose
    private String offername;
    @SerializedName("EventPrice")
    @Expose
    private String eventPrice;
    @SerializedName("EventImage")
    @Expose
    private String eventImage;
    @SerializedName("OfferValidity")
    @Expose
    private String offerValidity;
    @SerializedName("CouponCode")
    @Expose
    private String couponCode;
    @SerializedName("NoOfSeats")
    @Expose
    private String noOfSeats;
    @SerializedName("Description")
    @Expose
    private String description;

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

    public String getOffername() {
        return offername;
    }

    public void setOffername(String offername) {
        this.offername = offername;
    }

    public String getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(String eventPrice) {
        this.eventPrice = eventPrice;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
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

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}