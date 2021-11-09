package com.urdost.model.response.PurchaseOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstPurchaseOrder {

    @SerializedName("ActivationDate")
    @Expose
    private String activationDate;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("EventName")
    @Expose
    private String eventName;
    @SerializedName("Fk_EventId")
    @Expose
    private String fkEventId;
    @SerializedName("Fk_UserId")
    @Expose
    private String fkUserId;
    @SerializedName("Image")
    @Expose
    private Object image;
    @SerializedName("InvoiceNo")
    @Expose
    private String invoiceNo;
    @SerializedName("PK_BookingId")
    @Expose
    private String pKBookingId;
    @SerializedName("ProductPrice")
    @Expose
    private String productPrice;

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getFkEventId() {
        return fkEventId;
    }

    public void setFkEventId(String fkEventId) {
        this.fkEventId = fkEventId;
    }

    public String getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(String fkUserId) {
        this.fkUserId = fkUserId;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getPKBookingId() {
        return pKBookingId;
    }

    public void setPKBookingId(String pKBookingId) {
        this.pKBookingId = pKBookingId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }
}
