package com.urdost.model.response.responseMainDashboard;

import com.google.gson.annotations.SerializedName;

public class LstBannerItem{

    @SerializedName("BannerImage")
    private String bannerImage;

    public String getBannerImage(){
        return bannerImage;
    }
}