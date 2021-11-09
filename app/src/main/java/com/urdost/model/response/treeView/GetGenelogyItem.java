package com.urdost.model.response.treeView;

import com.google.gson.annotations.SerializedName;

public class GetGenelogyItem{

    @SerializedName("LoginId")
    private String loginId;

    @SerializedName("BusinessLeft")
    private String businessLeft;

    @SerializedName("ImageURL")
    private String imageURL;

    @SerializedName("Fk_SponsorId")
    private String fkSponsorId;

    @SerializedName("BusinessRight")
    private String businessRight;

    @SerializedName("Leg")
    private String leg;

    @SerializedName("ActiveLeft")
    private String activeLeft;

    @SerializedName("InactiveLeft")
    private String inactiveLeft;

    @SerializedName("SponsorId")
    private String sponsorId;

    @SerializedName("Fk_UserId")
    private String fkUserId;

    @SerializedName("ActiveRight")
    private String activeRight;

    @SerializedName("Fk_ParentId")
    private String fkParentId;

    @SerializedName("TeamPermanent")
    private String teamPermanent;

    @SerializedName("InactiveRight")
    private String inactiveRight;

    @SerializedName("MemberName")
    private String memberName;

    @SerializedName("Id")
    private String id;

    @SerializedName("ActivationDate")
    private String activationDate;

    @SerializedName("MemberLevel")
    private String memberLevel;

    public String getLoginId(){
        return loginId;
    }

    public String getBusinessLeft(){
        return businessLeft;
    }

    public String getImageURL(){
        return imageURL;
    }

    public String getFkSponsorId(){
        return fkSponsorId;
    }

    public String getBusinessRight(){
        return businessRight;
    }

    public String getLeg(){
        return leg;
    }

    public String getActiveLeft(){
        return activeLeft;
    }

    public String getInactiveLeft(){
        return inactiveLeft;
    }

    public String getSponsorId(){
        return sponsorId;
    }

    public String getFkUserId(){
        return fkUserId;
    }

    public String getActiveRight(){
        return activeRight;
    }

    public String getFkParentId(){
        return fkParentId;
    }

    public String getTeamPermanent(){
        return teamPermanent;
    }

    public String getInactiveRight(){
        return inactiveRight;
    }

    public String getMemberName(){
        return memberName;
    }

    public String getId(){
        return id;
    }

    public String getActivationDate(){
        return activationDate;
    }

    public String getMemberLevel(){
        return memberLevel;
    }
}