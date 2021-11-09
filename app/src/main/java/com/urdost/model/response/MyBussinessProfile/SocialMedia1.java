package com.urdost.model.response.MyBussinessProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SocialMedia1 {
    @SerializedName("TypeName")
    @Expose
    private String typeName;
    @SerializedName("TypeItem")
    @Expose
    private List<TypeItem> typeItem = null;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<TypeItem> getTypeItem() {
        return typeItem;
    }

    public void setTypeItem(List<TypeItem> typeItem) {
        this.typeItem = typeItem;
    }

}