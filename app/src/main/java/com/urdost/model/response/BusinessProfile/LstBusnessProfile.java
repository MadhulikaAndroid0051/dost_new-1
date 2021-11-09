package com.urdost.model.response.BusinessProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstBusnessProfile {

    @SerializedName("BusinessName")
    @Expose
    private String businessName;
    @SerializedName("ProfileName")
    @Expose
    private String ProfileNAme;

    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Designation")
    @Expose
    private String designation;
    @SerializedName("FK_UserId")
    @Expose
    private String fKUserId;
    @SerializedName("IsChecked")
    @Expose
    private String isChecked;
    @SerializedName("IsProfileTurnedOff")
    @Expose
    private Boolean isProfileTurnedOff;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("PK_ProfileId")
    @Expose
    private String pKProfileId;
    @SerializedName("ProfilePic")
    @Expose
    private String profilePic;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getFKUserId() {
        return fKUserId;
    }

    public void setFKUserId(String fKUserId) {
        this.fKUserId = fKUserId;
    }

    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public Boolean getIsProfileTurnedOff() {
        return isProfileTurnedOff;
    }

    public void setIsProfileTurnedOff(Boolean isProfileTurnedOff) {
        this.isProfileTurnedOff = isProfileTurnedOff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPKProfileId() {
        return pKProfileId;
    }

    public void setPKProfileId(String pKProfileId) {
        this.pKProfileId = pKProfileId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getProfileNAme() {
        return ProfileNAme;
    }

    public void setProfileNAme(String profileNAme) {
        ProfileNAme = profileNAme;
    }
}
