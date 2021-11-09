package com.urdost.model.response.responseMainDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductsItem{

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("PK_SubCategoryId")
    @Expose
    private String pKSubCategoryId;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("DeliveryCharges")
    @Expose
    private String deliveryCharges;
    @SerializedName("IGST")
    @Expose
    private String igst;
    @SerializedName("OfferPrice")
    @Expose
    private String offerPrice;
    @SerializedName("CGST")
    @Expose
    private String cgst;
    @SerializedName("SGST")
    @Expose
    private String sgst;
    @SerializedName("Discount")
    @Expose
    private Object discount;
    @SerializedName("ShortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("Price")
    @Expose
    private String price;
    @SerializedName("Pk_EventId")
    @Expose
    private String pkEventId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPKSubCategoryId() {
        return pKSubCategoryId;
    }

    public void setPKSubCategoryId(String pKSubCategoryId) {
        this.pKSubCategoryId = pKSubCategoryId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(String deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public String getIgst() {
        return igst;
    }

    public void setIgst(String igst) {
        this.igst = igst;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
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

    public Object getDiscount() {
        return discount;
    }

    public void setDiscount(Object discount) {
        this.discount = discount;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPkEventId() {
        return pkEventId;
    }

    public void setPkEventId(String pkEventId) {
        this.pkEventId = pkEventId;
    }
}