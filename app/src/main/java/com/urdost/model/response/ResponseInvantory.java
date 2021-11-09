package com.urdost.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseInvantory {

    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("InventoryRequest")
    @Expose
    private String inventoryRequest;
    @SerializedName("AvailableInventory")
    @Expose
    private String availableInventory;
    @SerializedName("DeliveredInventory")
    @Expose
    private String deliveredInventory;

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

    public String getInventoryRequest() {
        return inventoryRequest;
    }

    public void setInventoryRequest(String inventoryRequest) {
        this.inventoryRequest = inventoryRequest;
    }

    public String getAvailableInventory() {
        return availableInventory;
    }

    public void setAvailableInventory(String availableInventory) {
        this.availableInventory = availableInventory;
    }

    public String getDeliveredInventory() {
        return deliveredInventory;
    }

    public void setDeliveredInventory(String deliveredInventory) {
        this.deliveredInventory = deliveredInventory;
    }

}
