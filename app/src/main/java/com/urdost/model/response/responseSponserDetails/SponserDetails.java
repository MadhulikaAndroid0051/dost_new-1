package com.urdost.model.response.responseSponserDetails;

import com.google.gson.annotations.SerializedName;

public class SponserDetails {

    @SerializedName("DisplayName")
    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
