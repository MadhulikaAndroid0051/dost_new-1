package com.urdost.model.response.responseMainDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMainDashboard{
    @SerializedName("StatusCode")
    @Expose
    private String statusCode;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("lstBanner")
    @Expose
    private List<LstBannerItem> lstBanner ;
    @SerializedName("lstSubCategory")
    @Expose
    private List<LstSubCategoryItem> lstSubCategory;

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

    public List<LstBannerItem> getLstBanner() {
        return lstBanner;
    }

    public void setLstBanner(List<LstBannerItem> lstBanner) {
        this.lstBanner = lstBanner;
    }

    public List<LstSubCategoryItem> getLstSubCategory() {
        return lstSubCategory;
    }

    public void setLstSubCategory(List<LstSubCategoryItem> lstSubCategory) {
        this.lstSubCategory = lstSubCategory;
    }
}