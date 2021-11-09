package com.urdost.model.response.kycStatus;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetKycData {
    @SerializedName("AdharNumber")
    @Expose
    private String adharNumber;
    @SerializedName("AdharImage")
    @Expose
    private String adharImage;
    @SerializedName("AdharStatus")
    @Expose
    private String adharStatus;
    @SerializedName("PanNumber")
    @Expose
    private String panNumber;
    @SerializedName("PanImage")
    @Expose
    private String panImage;
    @SerializedName("PanStatus")
    @Expose
    private String panStatus;
    @SerializedName("DocumentNumber")
    @Expose
    private String documentNumber;
    @SerializedName("DocumentImage")
    @Expose
    private String documentImage;
    @SerializedName("DocumentStatus")
    @Expose
    private String documentStatus;
    @SerializedName("MemberAccNo")
    @Expose
    private String memberAccNo;
    @SerializedName("MemberBankName")
    @Expose
    private String memberBankName;
    @SerializedName("IFSCCode")
    @Expose
    private String iFSCCode;
    @SerializedName("MemberBranch")
    @Expose
    private String memberBranch;

    public String getAdharNumber() {
        return adharNumber;
    }

    public void setAdharNumber(String adharNumber) {
        this.adharNumber = adharNumber;
    }

    public String getAdharImage() {
        return adharImage;
    }

    public void setAdharImage(String adharImage) {
        this.adharImage = adharImage;
    }

    public String getAdharStatus() {
        return adharStatus;
    }

    public void setAdharStatus(String adharStatus) {
        this.adharStatus = adharStatus;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getPanImage() {
        return panImage;
    }

    public void setPanImage(String panImage) {
        this.panImage = panImage;
    }

    public String getPanStatus() {
        return panStatus;
    }

    public void setPanStatus(String panStatus) {
        this.panStatus = panStatus;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public void setDocumentImage(String documentImage) {
        this.documentImage = documentImage;
    }

    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getMemberAccNo() {
        return memberAccNo;
    }

    public void setMemberAccNo(String memberAccNo) {
        this.memberAccNo = memberAccNo;
    }

    public String getMemberBankName() {
        return memberBankName;
    }

    public void setMemberBankName(String memberBankName) {
        this.memberBankName = memberBankName;
    }

    public String getIFSCCode() {
        return iFSCCode;
    }

    public void setIFSCCode(String iFSCCode) {
        this.iFSCCode = iFSCCode;
    }

    public String getMemberBranch() {
        return memberBranch;
    }

    public void setMemberBranch(String memberBranch) {
        this.memberBranch = memberBranch;
    }
}
