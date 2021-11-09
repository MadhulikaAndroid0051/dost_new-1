package com.urdost.model.response.responseMainDashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LstSubCategoryItem{

    @SerializedName("SubCategoryId")
    @Expose
    private String subCategoryId;
    @SerializedName("SubCategoryName")
    @Expose
    private String subCategoryName;
    @SerializedName("SubCategoryIcon")
    @Expose
    private String subCategoryIcon;
    @SerializedName("Products")
    @Expose
    private List<ProductsItem> products;

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getSubCategoryIcon() {
        return subCategoryIcon;
    }

    public void setSubCategoryIcon(String subCategoryIcon) {
        this.subCategoryIcon = subCategoryIcon;
    }

    public List<ProductsItem> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsItem> products) {
        this.products = products;
    }

}
