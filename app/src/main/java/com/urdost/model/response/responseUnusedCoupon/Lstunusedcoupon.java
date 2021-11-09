package com.urdost.model.response.responseUnusedCoupon;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lstunusedcoupon {

    @SerializedName("CouponCode")
    @Expose
    private String couponCode;
    @SerializedName("CategoryName")
    @Expose
    private String categoryName;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("CuopnStatus")
    @Expose
    private String cuopnStatus;
    @SerializedName("CouponPrice")
    @Expose
    private String couponPrice;
    @SerializedName("EventName")
    @Expose
    private String eventName;
    @SerializedName("lstunusedpins")
    @Expose
    private Object lstunusedpins;

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

    public Object getLstunusedpins() {
        return lstunusedpins;
    }

    public void setLstunusedpins(Object lstunusedpins) {
        this.lstunusedpins = lstunusedpins;
    }
}
