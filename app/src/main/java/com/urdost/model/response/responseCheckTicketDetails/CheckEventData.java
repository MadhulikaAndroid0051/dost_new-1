package com.urdost.model.response.responseCheckTicketDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckEventData {
    @SerializedName("ProductPrice")
    @Expose
    private String productPrice;
    @SerializedName("NoOfSeats")
    @Expose
    private String noOfSeats;
    @SerializedName("Balance")
    @Expose
    private String balance;

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(String noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}
