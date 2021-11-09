package com.urdost.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGetHealthData {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("PK_CardId")
    @Expose
    private String pKCardId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("DOB")
    @Expose
    private String dob;
    @SerializedName("Photo")
    @Expose
    private String photo;
    @SerializedName("HealthCardNo")
    @Expose
    private String healthCardNo;
    @SerializedName("QrImage")
    @Expose
    private String qrImage;

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

    public String getPKCardId() {
        return pKCardId;
    }

    public void setPKCardId(String pKCardId) {
        this.pKCardId = pKCardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHealthCardNo() {
        return healthCardNo;
    }

    public void setHealthCardNo(String healthCardNo) {
        this.healthCardNo = healthCardNo;
    }

    public String getQrImage() {
        return qrImage;
    }

    public void setQrImage(String qrImage) {
        this.qrImage = qrImage;
    }

}
