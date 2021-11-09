package com.urdost.model.response.responsePaidCommission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePaidCommission1 {


    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstPaidCommission")
    @Expose
    private List<LstPaidCommission> lstPaidCommission;

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

    public List<LstPaidCommission> getLstPaidCommission() {
        return lstPaidCommission;
    }

    public void setLstPaidCommission(List<LstPaidCommission> lstPaidCommission) {
        this.lstPaidCommission = lstPaidCommission;
    }

}
