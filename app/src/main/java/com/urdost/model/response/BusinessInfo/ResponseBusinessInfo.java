package com.urdost.model.response.BusinessInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseBusinessInfo {
  @SerializedName("StatusCode")
  @Expose
  private String statusCode;
  @SerializedName("Message")
  @Expose
  private String message;
  @SerializedName("Data")
  @Expose
  private getBusinessInfo data;

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

  public getBusinessInfo getData() {
    return data;
  }

  public void setData(getBusinessInfo data) {
    this.data = data;
  }
}