package com.urdost.model.response.MyNfc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseMyNfc {

  @SerializedName("StatusCode")
  @Expose
  private String statusCode;
  @SerializedName("Message")
  @Expose
  private String message;
  @SerializedName("lstmynfc")
  @Expose
  private List<Lstmynfc> lstmynfc = new ArrayList<Lstmynfc>();

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

  public List<Lstmynfc> getLstmynfc() {
    return lstmynfc;
  }

  public void setLstmynfc(List<Lstmynfc> lstmynfc) {
    this.lstmynfc = lstmynfc;
  }

}