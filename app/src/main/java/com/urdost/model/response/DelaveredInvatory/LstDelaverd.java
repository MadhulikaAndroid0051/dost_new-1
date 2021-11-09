package com.urdost.model.response.DelaveredInvatory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstDelaverd {
    @SerializedName("SrNo")
    @Expose
    private String SrNo;
    @SerializedName("PK_NFCId")
    @Expose
    private String pKNFCId;
    @SerializedName("AllotedOn")
    @Expose
    private String allotedOn;
    @SerializedName("AssignedBy")
    @Expose
    private String assignedBy;
    @SerializedName("Code")
    @Expose
    private String code;
    @SerializedName("NFCStatus")
    @Expose
    private String nFCStatus;

    public String getPKNFCId() {
        return pKNFCId;
    }

    public void setPKNFCId(String pKNFCId) {
        this.pKNFCId = pKNFCId;
    }

    public String getAllotedOn() {
        return allotedOn;
    }

    public void setAllotedOn(String allotedOn) {
        this.allotedOn = allotedOn;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNFCStatus() {
        return nFCStatus;
    }

    public void setNFCStatus(String nFCStatus) {
        this.nFCStatus = nFCStatus;
    }

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getpKNFCId() {
        return pKNFCId;
    }

    public void setpKNFCId(String pKNFCId) {
        this.pKNFCId = pKNFCId;
    }

    public String getnFCStatus() {
        return nFCStatus;
    }

    public void setnFCStatus(String nFCStatus) {
        this.nFCStatus = nFCStatus;
    }
}
