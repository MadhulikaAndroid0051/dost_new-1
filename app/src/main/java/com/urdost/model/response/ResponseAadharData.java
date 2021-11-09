package com.urdost.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAadharData {
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
    @SerializedName("NameH")
    @Expose
    private String nameH;
    @SerializedName("FatherName")
    @Expose
    private String fatherName;
    @SerializedName("FatherNameH")
    @Expose
    private String fatherNameH;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("GenderH")
    @Expose
    private String genderH;
    @SerializedName("Mobile")
    @Expose
    private String mobile;
    @SerializedName("DOB")
    @Expose
    private String dob;
    @SerializedName("Photo")
    @Expose
    private String photo;
    @SerializedName("AdharNo")
    @Expose
    private String adharNo;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Address")
    @Expose
    private String address;
    @SerializedName("AddressH")
    @Expose
    private String addressH;
    @SerializedName("PinCode")
    @Expose
    private String pinCode;
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

    public String getNameH() {
        return nameH;
    }

    public void setNameH(String nameH) {
        this.nameH = nameH;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherNameH() {
        return fatherNameH;
    }

    public void setFatherNameH(String fatherNameH) {
        this.fatherNameH = fatherNameH;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGenderH() {
        return genderH;
    }

    public void setGenderH(String genderH) {
        this.genderH = genderH;
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

    public String getAdharNo() {
        return adharNo;
    }

    public void setAdharNo(String adharNo) {
        this.adharNo = adharNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressH() {
        return addressH;
    }

    public void setAddressH(String addressH) {
        this.addressH = addressH;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getQrImage() {
        return qrImage;
    }

    public void setQrImage(String qrImage) {
        this.qrImage = qrImage;
    }

}
