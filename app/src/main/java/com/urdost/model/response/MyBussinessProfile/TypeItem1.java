package com.urdost.model.response.MyBussinessProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeItem1 {

    @SerializedName("Content")
    @Expose
    private String content;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("IsWhatsApp")
    @Expose
    private String isWhatsApp;
    @SerializedName("RedirectionLink")
    @Expose
    private String redirectionLink;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsWhatsApp() {
        return isWhatsApp;
    }

    public void setIsWhatsApp(String isWhatsApp) {
        this.isWhatsApp = isWhatsApp;
    }

    public String getRedirectionLink() {
        return redirectionLink;
    }

    public void setRedirectionLink(String redirectionLink) {
        this.redirectionLink = redirectionLink;
    }
}
