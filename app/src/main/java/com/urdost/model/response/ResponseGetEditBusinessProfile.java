package com.urdost.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseGetEditBusinessProfile {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("ProfileName")
    @Expose
    private String ProfileName;
    @SerializedName("PK_ProfileId")
    @Expose
    private String pKProfileId;
    @SerializedName("FK_UserId")
    @Expose
    private String fKUserId;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("Designation")
    @Expose
    private String designation;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("ProfilePic")
    @Expose
    private String profilePic;
    @SerializedName("BusinessName")
    @Expose
    private String businessName;
    @SerializedName("Description")
    @Expose
    private String description;

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

    public String getPKProfileId() {
        return pKProfileId;
    }

    public void setPKProfileId(String pKProfileId) {
        this.pKProfileId = pKProfileId;
    }

    public String getFKUserId() {
        return fKUserId;
    }

    public void setFKUserId(String fKUserId) {
        this.fKUserId = fKUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileName() {
        return ProfileName;
    }

    public void setProfileName(String profileName) {
        ProfileName = profileName;
    }
}
