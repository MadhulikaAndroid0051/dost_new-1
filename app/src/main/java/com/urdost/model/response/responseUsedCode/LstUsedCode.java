package com.urdost.model.response.responseUsedCode;

import com.google.gson.annotations.SerializedName;

public class LstUsedCode {

    @SerializedName("CouponCode")
    private String couponCode;

    @SerializedName("CategoryName")
    private String categoryName;

    @SerializedName("CreatedDate")
    private String createdDate;

    @SerializedName("CuopnStatus")
    private String cuopnStatus;

    @SerializedName("CouponPrice")
    private String couponPrice;

    @SerializedName("EventName")
    private String eventName;

    @SerializedName("RegisteredTo")
    private String RegisteredTo;

    public String getRegisteredTo() {
        return RegisteredTo;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCuopnStatus() {
        return cuopnStatus;
    }

    public void setCuopnStatus(String cuopnStatus) {
        this.cuopnStatus = cuopnStatus;
    }

    public String getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(String couponPrice) {
        this.couponPrice = couponPrice;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

}
