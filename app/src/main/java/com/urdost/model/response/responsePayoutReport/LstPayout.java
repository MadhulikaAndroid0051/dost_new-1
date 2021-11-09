package com.urdost.model.response.responsePayoutReport;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstPayout {

  @SerializedName("BinaryIncome")
  @Expose
  private String binaryIncome;
  @SerializedName("ClosingDate")
  @Expose
  private String closingDate;
  @SerializedName("DirectIncome")
  @Expose
  private String directIncome;
  @SerializedName("DisplayName")
  @Expose
  private String displayName;
  @SerializedName("EncryptLoginID")
  @Expose
  private String encryptLoginID;
  @SerializedName("EncryptPayoutNo")
  @Expose
  private String encryptPayoutNo;
  @SerializedName("GrossAmount")
  @Expose
  private String grossAmount;
  @SerializedName("LeadershipBonus")
  @Expose
  private String leadershipBonus;
  @SerializedName("LoginId")
  @Expose
  private String loginId;
  @SerializedName("NetAmount")
  @Expose
  private String netAmount;
  @SerializedName("PayoutNo")
  @Expose
  private String payoutNo;
  @SerializedName("ProcessingFee")
  @Expose
  private String processingFee;
  @SerializedName("ProductWallet")
  @Expose
  private String productWallet;
  @SerializedName("TDSAmount")
  @Expose
  private String tDSAmount;

  public String getBinaryIncome() {
    return binaryIncome;
  }

  public void setBinaryIncome(String binaryIncome) {
    this.binaryIncome = binaryIncome;
  }

  public String getClosingDate() {
    return closingDate;
  }

  public void setClosingDate(String closingDate) {
    this.closingDate = closingDate;
  }

  public String getDirectIncome() {
    return directIncome;
  }

  public void setDirectIncome(String directIncome) {
    this.directIncome = directIncome;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getEncryptLoginID() {
    return encryptLoginID;
  }

  public void setEncryptLoginID(String encryptLoginID) {
    this.encryptLoginID = encryptLoginID;
  }

  public String getEncryptPayoutNo() {
    return encryptPayoutNo;
  }

  public void setEncryptPayoutNo(String encryptPayoutNo) {
    this.encryptPayoutNo = encryptPayoutNo;
  }

  public String getGrossAmount() {
    return grossAmount;
  }

  public void setGrossAmount(String grossAmount) {
    this.grossAmount = grossAmount;
  }

  public String getLeadershipBonus() {
    return leadershipBonus;
  }

  public void setLeadershipBonus(String leadershipBonus) {
    this.leadershipBonus = leadershipBonus;
  }

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getNetAmount() {
    return netAmount;
  }

  public void setNetAmount(String netAmount) {
    this.netAmount = netAmount;
  }

  public String getPayoutNo() {
    return payoutNo;
  }

  public void setPayoutNo(String payoutNo) {
    this.payoutNo = payoutNo;
  }

  public String getProcessingFee() {
    return processingFee;
  }

  public void setProcessingFee(String processingFee) {
    this.processingFee = processingFee;
  }

  public String getProductWallet() {
    return productWallet;
  }

  public void setProductWallet(String productWallet) {
    this.productWallet = productWallet;
  }

  public String getTDSAmount() {
    return tDSAmount;
  }

  public void setTDSAmount(String tDSAmount) {
    this.tDSAmount = tDSAmount;
  }

}