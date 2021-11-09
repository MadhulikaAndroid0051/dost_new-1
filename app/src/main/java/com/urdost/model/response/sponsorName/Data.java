package com.urdost.model.response.sponsorName;

import com.google.gson.annotations.SerializedName;

public class Data {

  @SerializedName("SponsorName")
  private String sponsorName;

  @SerializedName("PK_UserId")
  private String PK_UserId;
  public String getPK_UserId() {
    return PK_UserId;
  }


  public String getSponsorName() {
    return sponsorName;
  }
}
