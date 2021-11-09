package com.urdost.model.response.MyBussinessProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseNfcProfile {

  @SerializedName("StatusCode")
  @Expose
  private String statusCode;
  @SerializedName("Message")
  @Expose
  private String message;
  @SerializedName("Profile")
  @Expose
  private List<Profile> profile = null;
  @SerializedName("NFCList")
  @Expose
  private List<Nfc> nFCList = null;

  @SerializedName("SocialMediaList")
  @Expose
  private List<SocialMedia1> socialMedia = null;

  public List<Nfc> getnFCList() {
    return nFCList;
  }

  public void setnFCList(List<Nfc> nFCList) {
    this.nFCList = nFCList;
  }

  public List<SocialMedia1> getSocialMedia() {
    return socialMedia;
  }

  public void setSocialMedia(List<SocialMedia1> socialMedia) {
    this.socialMedia = socialMedia;
  }

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

  public List<Profile> getProfile() {
    return profile;
  }

  public void setProfile(List<Profile> profile) {
    this.profile = profile;
  }

  public List<Nfc> getNFCList() {
    return nFCList;
  }

  public void setNFCList(List<Nfc> nFCList) {
    this.nFCList = nFCList;
  }
}