package com.urdost.model.response.exchangeAnalytics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LstAnalytics {

    @SerializedName("Browser")
    @Expose
    private String browser;
    @SerializedName("Device")
    @Expose
    private String device;
    @SerializedName("IP")
    @Expose
    private String ip;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("ViewDateTime")
    @Expose
    private String viewDateTime;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getViewDateTime() {
        return viewDateTime;
    }

    public void setViewDateTime(String viewDateTime) {
        this.viewDateTime = viewDateTime;
    }
}
