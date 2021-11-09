package com.urdost.model.response.NfcActivateBuyDDCard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstnfcDdCard {

  @SerializedName("PK_EventId")
  @Expose
  private String pKEventId;
  @SerializedName("EventName")
  @Expose
  private String eventName;
  @SerializedName("BinaryBV")
  @Expose
  private String binaryBV;
  @SerializedName("ReferalBV")
  @Expose
  private String referalBV;
  @SerializedName("EventImage")
  @Expose
  private String eventImage;
  @SerializedName("ProductPrice")
  @Expose
  private String productPrice;
  @SerializedName("Discount")
  @Expose
  private String discount;

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

  public String getBinaryBV() {
    return binaryBV;
  }

  public void setBinaryBV(String binaryBV) {
    this.binaryBV = binaryBV;
  }

  public String getReferalBV() {
    return referalBV;
  }

  public void setReferalBV(String referalBV) {
    this.referalBV = referalBV;
  }

  public String getEventImage() {
    return eventImage;
  }

  public void setEventImage(String eventImage) {
    this.eventImage = eventImage;
  }

  public String getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(String productPrice) {
    this.productPrice = productPrice;
  }

  public String getDiscount() {
    return discount;
  }

  public void setDiscount(String discount) {
    this.discount = discount;
  }

}