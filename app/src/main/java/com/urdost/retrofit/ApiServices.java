package com.urdost.retrofit;


import com.google.gson.annotations.SerializedName;
import com.urdost.model.ResponseStatusMessage;
import com.urdost.model.request.RequestAddDistributor;
import com.urdost.model.request.RequestCheckMobile;
import com.urdost.model.request.RequestDirectSearch;
import com.urdost.model.request.RequestLogin;
import com.urdost.model.request.RequestRegister;
import com.urdost.model.request.RequestVerifyOTP;
import com.urdost.model.response.AvalableInvantory.ResponseAvailable;
import com.urdost.model.response.BusinessInfo.ResponseBusinessInfo;
import com.urdost.model.response.BusinessProfile.ResponseBusinessProfile;
import com.urdost.model.response.BuyNfc.ResponseBuyNfc;
import com.urdost.model.response.CreateProfileResponse;
import com.urdost.model.response.NFcContact.ResponseGetContact;
import com.urdost.model.response.DelaveredInvatory.ResponseDelavierd;
import com.urdost.model.response.GeSocialMediaLink.ResponseSocialMediaLink;
import com.urdost.model.response.GetEmail.ResponseGetEmail;
import com.urdost.model.response.GetNfcActicateStatus;
import com.urdost.model.response.MyBussinessProfile.ResponseNfcProfile;
import com.urdost.model.response.MyNfc.ResponseMyNfc;
import com.urdost.model.response.NfcActivateBuyDDCard.ResponseNfcActivate;
import com.urdost.model.response.PurchaseOrder.ResponsePurchaseOrder;
import com.urdost.model.response.ReponsePanCard;
import com.urdost.model.response.ResponseAadharData;
import com.urdost.model.response.ResponseActivateCouponId;
import com.urdost.model.response.ResponseBusinessD;
import com.urdost.model.response.ResponseBusinessInformation;
import com.urdost.model.response.ResponseCheckResallerStatus;
import com.urdost.model.response.ResponseCreateProfile;
import com.urdost.model.response.ResponseGetEditBusinessProfile;
import com.urdost.model.response.ResponseGetHealthData;
import com.urdost.model.response.ResponseGetKycData;
import com.urdost.model.response.ResponseGetSponseStatus;
import com.urdost.model.response.ResponseInvantory;
import com.urdost.model.response.ResponseNFCPayNow;
import com.urdost.model.response.ResponseNfcSaveData;
import com.urdost.model.response.ResponsePanCard;
import com.urdost.model.response.ResponseProfilePicUpload;
import com.urdost.model.response.ResponseResponseTransferEWallet;
import com.urdost.model.response.ResponseSaveHealth;
import com.urdost.model.response.ResponseSaveProfile;
import com.urdost.model.response.ResponseTaxInvoice;
import com.urdost.model.response.ResponseTerms;
import com.urdost.model.response.ResponseUpdateProfile;
import com.urdost.model.response.ResponseWalletBalance;
import com.urdost.model.response.ResponseWithdrawal;
import com.urdost.model.response.WebLink.ResponseWebLink;
import com.urdost.model.response.exchangeAnalytics.ResponseExchangeAnalytics;
import com.urdost.model.response.istributorCommissionHistory.ResponseDistributorCommissionHistory;
import com.urdost.model.response.kycStatus.ResponseKycStatus;
import com.urdost.model.response.register.ResponseRegistration;
import com.urdost.model.response.responsStockStatement.ResponsStockStatement;
import com.urdost.model.response.responseDiractComminsion.ResponseDiractCommision;
import com.urdost.model.response.responseDiractList.ResponseDiractList;
import com.urdost.model.response.responseEwalletLedger.ResponseEwalletLedger;
import com.urdost.model.response.responseFranchiseWalletStatement.ResponseFranchiseWalletStatement;
import com.urdost.model.response.responseMainDashboard.ResponseMainDashboard;
import com.urdost.model.response.responsePaidCommission.ResponsePaidCommission1;
import com.urdost.model.response.responsePassbook.ResponsePassbook;
import com.urdost.model.response.responsePayoutLedger.ResponsePayoutLedger;
import com.urdost.model.response.responsePayoutReport.ResponsePayoutReport;
import com.urdost.model.response.responsePersonalInformation.ResponseGetPersonalInfor;
import com.urdost.model.response.responsePincode.ResponsePincode;
import com.urdost.model.response.responseSponserDetails.ResponseSponserDetails;
import com.urdost.model.response.responseSubscription.ResponseSubscription;
import com.urdost.model.response.responseUnpaidIncome.ResponseUnpaidIncome;
import com.urdost.model.response.responseUnusedCoupon.ResponseUnUsedCoupon;
import com.urdost.model.response.responseUsedCode.ResponseUsedCode;
import com.urdost.model.response.responseViewProfile.ResponseViewProfile;
import com.urdost.model.response.sponsorName.ResponseSponsor;
import com.urdost.model.response.treeView.ResponseTreeView;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServices {
    @POST("CheckMobile")
    Call<ResponseStatusMessage> checkMobile(@Body RequestCheckMobile mobile);

    @POST("VerifyOTP")
    Call<ResponseStatusMessage> VerifyOTP(@Body RequestVerifyOTP mobile);

    @POST("GenerateOTP")
    Call<ResponseStatusMessage> resendOTP(@Body JsonObject mobile);

    @POST("Register")
    Call<ResponseRegistration> register(@Body RequestRegister mobile);
    @POST("ResetPassword")
    Call<ResponseStatusMessage> ResetPassword(@Body JsonObject mobile);

    @POST("ForgetPassword")
    Call<ResponseStatusMessage> ForgetPassword(@Body RequestCheckMobile mobile);

    @POST("Login")
    Call<ResponseRegistration> login(@Body RequestLogin mobile);
    @GET("GetDigiDashBoardData")
    Call<ResponseMainDashboard> getNewContainer();

    @POST("AddDistributor")
    Call<ResponseStatusMessage> addDistributor(@Body RequestAddDistributor distributor);

    @GET("GetKnowMore")
    Call<ResponseTerms> GetKnowMore();

    @Multipart
    @POST("/UploadProfilePic")
    Call<ResponseProfilePicUpload> uploadProfilePic1(@Part("fk_UserId") RequestBody fk_UserId,
                                                     @Part() MultipartBody.Part file);

    @Multipart
    @POST("/UploadPassbookImage")
    Call<ResponseProfilePicUpload> uplodedocumrnt(@Part("PK_UserId") RequestBody fk_UserId,
                                                  @Part() MultipartBody.Part file);
    @Multipart
    @POST("SaveEwalletRequest")
    Call<ResponseStatusMessage> uploadOfflineRequest(@Part("LoginId") RequestBody userId,
                                                     @Part("PaymentMode") RequestBody PaymentMode,
                                                     @Part("DDChequeNo") RequestBody TransactionNo,
                                                     @Part("DDChequeDate") RequestBody TransactionDate,
                                                     @Part("Amount") RequestBody Amount,
                                                     @Part("BankName") RequestBody BankName,
                                                     @Part("BankBranch") RequestBody BranchName,
                                                     @Part() MultipartBody.Part file);



    @POST("NFCPersonalInformation")
    Call<ResponseGetPersonalInfor> getPersonalInformation(@Body JsonObject mobile);

    @POST("GetNFCContactDetails")
    Call<ResponseGetContact> getContact(@Body JsonObject mobile);

    @POST("GetNFCEmailDetails")
    Call<ResponseGetEmail> getEmail(@Body JsonObject mobile);

    @POST("GetNFCWebLinkDetails")
    Call<ResponseWebLink> getWebLink(@Body JsonObject mobile);

    @POST("GetNFCSocialMediaDetails")
    Call<ResponseSocialMediaLink> getSocialMeida(@Body JsonObject mobile);

    @POST("AddNFCContactDetails")
    Call<ResponseNfcSaveData> getNfcData(@Body JsonObject mobile);

    @POST("AddNFCEmailDetails")
    Call<ResponseNfcSaveData> getNFCEmail(@Body JsonObject mobile);

    @POST("AddNFCSocialMediaDetails")
    Call<ResponseNfcSaveData> getSocialMedialink(@Body JsonObject mobile);

    @POST("AddNFCWebLinkDetails")
    Call<ResponseNfcSaveData> getNfcWebLink(@Body JsonObject mobile);

    @POST("UpdateNFCProfileDetails")
    Call<ResponseNfcSaveData> updatenfcPersonalinfo(@Body JsonObject mobile);

    @POST("DeletNFCDetails")
    Call<ResponseNfcSaveData> DeletNfcData(@Body JsonObject mobile);

    @POST("GetMyNFC")
    Call<ResponseMyNfc> getMyNfc(@Body JsonObject mobile);
    @POST("UpdateBusinessInformation")
    Call<ResponseBusinessInformation> updateBusinessInfo(@Body JsonObject mobile);

    @POST("GetNFCBusinessInformation")
    Call<ResponseBusinessInfo> getBusinessInfo(@Body JsonObject mobile);


    @POST("DirectList")
    Call<ResponseDiractList> getDiractList(@Body JsonObject mobile);


    @POST("DirectList2")
    Call<ResponseDiractList> getDiractListSearch(@Body RequestDirectSearch mobile);

    @POST("SubscriptionList")
    Call<ResponseSubscription> getSubscription(@Body JsonObject mobile);
    @POST("WalletBalance")
    Call<ResponseWalletBalance> getWalletBalance(@Body JsonObject mobile);

    @POST("GetStateCity")
    Call<ResponsePincode>getCity(@Body JsonObject mobile);

    @POST("GetEventDetails")
    Call<ResponseBuyNfc>getBuyNfc(@Body JsonObject mobile);
    @POST("SaveNFCDetails")
    Call<ResponseNFCPayNow>getNfcPayNow(@Body JsonObject mobile);

    @GET("GetNFC")
    Call<ResponseNfcActivate> getActivateNfcDd();

    @POST("GetTermsAndConditions")
    Call<ResponseTerms> GetTermsAndConditions();

    @POST("GetSponsor")
    Call<ResponseSponsor> GetSponsor(@Body JsonObject mobile);

    @POST("JoinReferalProgram")
    Call<ResponseStatusMessage> JoinReferalProgram(@Body JsonObject mobile);

    @POST("GetPassbook")
    Call<ResponsePassbook> getPassbook(@Body JsonObject mobile);

    @POST("WithDrawal")
    Call<ResponseWithdrawal> getWithDrwal(@Body JsonObject mobile);

    @POST("EwalletLedger")
    Call<ResponseEwalletLedger> getEwalletledger(@Body JsonObject mobile);

    @POST("GetStockStatement")
    Call<ResponsStockStatement> getstockStatment(@Body JsonObject mobile);

    @POST("GetDirectCommision")
    Call<ResponseDiractCommision> getDiractCommison(@Body JsonObject mobile);

    @POST("GetPaidCommission")
    Call<ResponsePaidCommission1> getPaidCommission(@Body JsonObject mobile);

    @POST("FranchiseWalletStatement")
    Call<ResponseFranchiseWalletStatement> getFranchiseWalletStatement(@Body JsonObject mobile);

    @POST("DistributorCommissionHistory")
    Call<ResponseDistributorCommissionHistory> getCommissionHistory(@Body JsonObject mobile);

    @POST("UnPaidIncomes")
    Call<ResponseUnpaidIncome> getUnpaidIncome(@Body JsonObject mobile);

    @POST("PayoutReport")
    Call<ResponsePayoutReport> getPayoutReports(@Body JsonObject mobile);
    @POST("TransferEwallet")
    Call<ResponseResponseTransferEWallet> getTransferEWallet(@Body JsonObject mobile);

    @POST("PayoutLedger")
    Call<ResponsePayoutLedger> getPayoutLedger(@Body JsonObject mobile);

    @POST("Tree")
    Call<ResponseTreeView> getTreeView(@Body JsonObject login);

    @POST("BusinessDashboard")
    Call<ResponseBusinessD> getBussines(@Body JsonObject mobile);


    @POST("ViewProfile")
    Call<ResponseViewProfile> getViewProfile(@Body JsonObject mobile);

    @POST("UpdateProfile")
    Call<ResponseUpdateProfile> getUpdateProfile(@Body JsonObject mobile);

    @POST("UnusedCoupon")
    Call<ResponseUnUsedCoupon> getUnUsedCouponCode(@Body JsonObject mobile);

    ////
    @POST("UsedCoupon")
    Call<ResponseUsedCode> getUsedCouponCode(@Body JsonObject mobile);

    @POST("GetSponserDetails")
    Call<ResponseSponserDetails> getSponserId(@Body JsonObject mobile);
    @POST("ActivateUserId")
    Call<ResponseActivateCouponId> getActivateUserId(@Body JsonObject mobile);

    @POST("GetInventoryCount")
    Call<ResponseInvantory> getInvantoryData(@Body JsonObject mobile);

    @POST("EmailVerification")
    Call<ResponseStatusMessage> getEmailVerification(@Body JsonObject mobile);

    @POST("UpdateNFCProfileOnOffStatus")
    Call<ResponseBusinessInformation> updateNfcProfileSwitch(@Body JsonObject mobile);
    @POST("getNFCProfileList")
    Call<ResponseNfcProfile> getMyBussinessProfile(@Body JsonObject mobile);

    /* @Multipart
     @POST("api/ImageUpload/user/PostUserImage")
     Call<JsonObject> uploadProfilePic(@Part("fk_UserId") RequestBody fk_UserId,
                                       @Part() MultipartBody.Part file);*/
    @POST("KYCDocuments")
    Call<ResponseGetKycData> getKYCData(@Body JsonObject login);
    @Multipart
    @POST("UploadAadhar")
    Call<ResponseStatusMessage> uploadAdhar(@Part("PK_UserId") RequestBody UserId,
                                            @Part("AdharNo") RequestBody adharNumber,
                                            @Part() MultipartBody.Part file);
    @Multipart
    @POST("UploadPan")
    Call<ResponseStatusMessage> uploadPan(@Part("PK_UserId") RequestBody UserId,
                                          @Part("PanNo") RequestBody panNumber,
                                          @Part() MultipartBody.Part file);
    @Multipart
    @POST("UploadDocument")
    Call<ResponseStatusMessage> uoloaddocument(@Part("PK_UserId") RequestBody UserId,
                                               @Part("DocumentNo") RequestBody panNumber,
                                               @Part() MultipartBody.Part file);
    @Multipart
    @POST("UploadPassbookImage")
    Call<ResponseStatusMessage> uploadepassbook(@Part("PK_UserId") RequestBody fk_UserId,
                                                @Part() MultipartBody.Part file);
    @Multipart
    @POST("UploadProfilePic")
    Call<ResponseStatusMessage> uploadProfilePic(@Part("PK_UserId") RequestBody fk_UserId,
                                                 @Part() MultipartBody.Part file);

    @POST("UpdateRedirectionStatus")
    Call<ResponseStatusMessage>updatecheckbox(@Body JsonObject mobile);
    @POST("KYCDocuments")
    Call<ResponseKycStatus> getKycStatus(@Body JsonObject mobile);

    @POST("GetNFCActivationStatus")
    Call<GetNfcActicateStatus> getNfcStatus(@Body JsonObject mobile);
    @POST("CheckResellerStatus")
    Call<ResponseCheckResallerStatus> getResallerStatus(@Body JsonObject mobile);

    @POST("RecentAnalytics")
    Call<ResponseExchangeAnalytics> getAnalytics(@Body JsonObject mobile);

    @POST("GetSponsorForReseller")
    Call<ResponseGetSponseStatus> getSponserData();
    @POST("InventoryRequest")
    Call<ResponseStatusMessage>requestedInvantory(@Body JsonObject mobile);
    @POST("UnusedNFC")
    Call<ResponseDelavierd>getDelaviredInvantory(@Body JsonObject mobile);
    @POST("UsedNFC")
    Call<ResponseAvailable>getAvalibaleInvantory(@Body JsonObject mobile);

    @POST("InvoiceList")
    Call<ResponsePurchaseOrder>getPurachaseOrder(@Body JsonObject mobile);

    @POST("PrintInvoice")
    Call<ResponseTaxInvoice>getInvoiceData(@Body JsonObject mobile);

    @POST("GetNFCBusinessInformation")
    Call<ResponseBusinessProfile>getBusinessProfileList(@Body JsonObject mobile);

    @POST("UpdateBusinessInformation")
    Call<ResponseStatusMessage>saveBusinessProfileData(@Body JsonObject mobile);

    @POST("GetNFCBusinessInfoById")
    Call<ResponseGetEditBusinessProfile>getBusinessProfileEdit(@Body JsonObject mobile);

    @POST("EditBusinessInformation")
    Call<ResponseStatusMessage>updateBusinessProfile(@Body JsonObject mobile);

    @POST("UpdateProfileStatus")
    Call<ResponseStatusMessage>updateBusinessProfileStatus(@Body JsonObject mobile);

    @POST("Updateleg")
    Call<ResponseStatusMessage>updateLeg(@Body JsonObject mobile);

    @POST("UpdateIncludedStatus")
    Call<ResponseStatusMessage>includeEmailProfile(@Body JsonObject mobile);

    @POST("UpdateContactAsWhatsapp")
    Call<ResponseStatusMessage>deleteContact(@Body JsonObject mobile);
    @POST("SaveProfileName")
    Call<ResponseSaveProfile>saveprofilename(@Body JsonObject mobile);

    @POST("SaveHealthCardDetails")
    Call<ResponseSaveHealth>SaveHealthCardDetails(@Body JsonObject mobile);

    @POST("GetHealthCardDetails")
    Call<ResponseGetHealthData>getHealthCard(@Body JsonObject mobile);

    @POST("UpdateHealthCardDetails")
    Call<ResponseStatusMessage>updateHealthCard(@Body JsonObject mobile);


    @Multipart
    @POST("UploadPicForCard")
    Call<ResponseStatusMessage> uploadHealthImage(@Part("PK_UserId") RequestBody fk_UserId,
                                                  @Part("PK_CardId") RequestBody pk_cardId,
                                                  @Part() MultipartBody.Part file);

    //adhar CARD add
    @POST("SaveAdharCardDetails")
    Call<ResponseStatusMessage>saveadhardata(@Body JsonObject mobile);

    //Pan Card
    @POST("SavePanCardDetails")
    Call<ResponseStatusMessage>SavePanCard(@Body JsonObject mobile);

    @POST("GetPanCardDetails")
    Call<ReponsePanCard>getPancardData(@Body JsonObject mobile);

    @POST("UpdatePanCardDetails")
    Call<ResponseStatusMessage>updatePanCard(@Body JsonObject mobile);

    @POST("GetAdharCardDetails")
    Call<ResponseAadharData>getAadharData(@Body JsonObject mobile);

    @POST("SaveAdharCardDetails")
    Call<ResponseStatusMessage>SaveAdharCardDetails(@Body JsonObject mobile);

    @POST("UpdateAdharCardDetails")
    Call<ResponseStatusMessage>UpdateAdharCardDetails(@Body JsonObject mobile);

    @POST("SaveProfileName")
    Call<CreateProfileResponse>CreateNewProfile(@Body JsonObject mobile);


}

