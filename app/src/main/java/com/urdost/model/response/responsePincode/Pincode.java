package com.urdost.model.response.responsePincode;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pincode {
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("City")
    @Expose
    private String city;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
