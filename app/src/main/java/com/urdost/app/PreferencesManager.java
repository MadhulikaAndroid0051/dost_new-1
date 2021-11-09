package com.urdost.app;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

/**
 * Created by Abhishek on 1/3/18.
 */

public class PreferencesManager {

    //app login variables
    private static final String PREF_NAME = "com.digidirect.PREF";
    private static final String userid = "userid";
    private static final String pk_userId = "PK_UserId";
    private static final String PK_profileId = "PK_ProfileId";

    private static final String designation = "designation";
    private static final String servicetype = "servicetype";
    private static final String CiN = "CiN";
    private static final String fullname = "fullname";
    private static final String email = "email";
    private static final String mobileno = "mobileno";
    private static final String alternatemobile = "alternatemobile";
    private static final String aadhaar = "aadhaar";
    private static final String operationcity = "operationcity";
    private static final String completeprofile = "completeprofile";
    private static final String loginstatus = "loginstatus";
    private static final String dutystatus = "dutystatus";
    private static final String image = "image";
    private static final String address = "address";
    private static final String zipcode = "zipcode";
    private static final String vehicleno = "vehicleno";
    private static final String vehiclename = "vehiclename";
    private static final String vehicleimage = "vehicleimage";
    private static final String operationcityid = "operationcityid";
    private static final String countdocs = "countdocs";
    private static final String bookingtableid = "bookingtableid";
    private static final String language = "language";
    private static final String verificationRequested = "verificationRequested";
    private static final String SponserId = "SponsorId";
    private static final String UiMode = "UiMode";
    private static final String UserCode = "UserCode";
    public static PreferencesManager sInstance;
    private final SharedPreferences mPref;
    private static final String Distrubuter = "Distrubuter";
    private static final String IsAcceptanceTNC = "IsAcceptanceTNC";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    //for fragment
    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
    }


    //for getting instance
    public static synchronized PreferencesManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferencesManager(context);
        }
        return sInstance;
    }

    public boolean clear() {
        return mPref.edit().clear().commit();
    }

    //UIMode
    public void setUiMode(int value) {
        mPref.edit().putInt(UiMode, value).apply();
    }

    public int getUiMode() {
        return mPref.getInt(UiMode, AppCompatDelegate.MODE_NIGHT_NO);
    }

    //firstTime
    public void setIsFirstTimeLaunch(Boolean value) {
        mPref.edit().putBoolean(IS_FIRST_TIME_LAUNCH, value).apply();
    }

    public boolean getIsFirstTimeLaunch() {
        return mPref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    //verificationRequested
    public void setVerificationRequested(String value) {
        mPref.edit().putString(verificationRequested, value).apply();
    }

    public String getVerificationRequested() {
        return mPref.getString(verificationRequested, "");
    }

    //id
    public void setUserid(String value) {
        mPref.edit().putString(userid, value).apply();
    }

    public String getUserid() {
        return mPref.getString(userid, "");
    }
    //IsAcceptanceTNC
    public void setIsAcceptanceTNC(String IsAcceptanceTNC) {
        mPref.edit().putString(IsAcceptanceTNC, IsAcceptanceTNC).apply();
    }
    public String getIsAcceptanceTNC() {
        return mPref.getString(IsAcceptanceTNC,"");
    }

    // PK_id
    public void setPKUserId(String value) {
        mPref.edit().putString(pk_userId, value).apply();
    }

    public String getPk_userId() {
        return mPref.getString(pk_userId, "");
    }

    // User Code
    public void setUserCode(String value) {
        mPref.edit().putString(UserCode, value).apply();
    }

    public String getUserCode() {
        return mPref.getString(UserCode, "");
    }


    // Sponser ID
    public void setSponserId(String value) {
        mPref.edit().putString(SponserId, value).apply();
    }

    public String getSponserId() {
        return mPref.getString(SponserId, "");
    }

    //Distrubuter
    // User Code
    public void setDistrubuter(String value) {
        mPref.edit().putString(Distrubuter, value).apply();
    }

    public String getDistrubuter() {
        return mPref.getString(Distrubuter, "");
    }

    //taxiowneruid
    public void setDesignation(String value) {
        mPref.edit().putString(designation, value).apply();
    }

    public String getDesignation() {
        return mPref.getString(designation, "");
    }

    //servicetype
    public void setServicetype(String value) {
        mPref.edit().putString(servicetype, value).apply();
    }

    public String getServicetype() {
        return mPref.getString(servicetype, "");
    }

    //CiN
    public void setCiN(String value) {
        mPref.edit().putString(CiN, value).apply();
    }

    public String getCiN() {
        return mPref.getString(CiN, "");
    }

    //fullname
    public void setFullname(String value) {
        mPref.edit().putString(fullname, value).apply();
    }

    public String getFullname() {
        return mPref.getString(fullname, "");
    }

    //email
    public void setEmail(String value) {
        mPref.edit().putString(email, value).apply();
    }

    public String getEmail() {
        return mPref.getString(email, "");
    }

    //mobileno
    public void setMobileno(String value) {
        mPref.edit().putString(mobileno, value).apply();
    }

    public String getMobileno() {
        return mPref.getString(mobileno, "");
    }

    //alternatemobile
    public void setAlternatemobile(String value) {
        mPref.edit().putString(alternatemobile, value).apply();
    }

    public String getAlternatemobile() {
        return mPref.getString(alternatemobile, "");
    }

    //aadhaar
    public void setAadhaar(String value) {
        mPref.edit().putString(aadhaar, value).apply();
    }

    public String getAadhaar() {
        return mPref.getString(aadhaar, "");
    }

    //operationcity
    public void setOperationcity(String value) {
        mPref.edit().putString(operationcity, value).apply();
    }

    public String getOperationcity() {
        return mPref.getString(operationcity, "");
    }

    //completeprofile
    public void setCompleteprofile(String value) {
        mPref.edit().putString(completeprofile, value).apply();
    }

    public String getCompleteprofile() {
        return mPref.getString(completeprofile, "");
    }

    //loginstatus
    public void setLoginstatus(String value) {
        mPref.edit().putString(loginstatus, value).apply();
    }

    public String getLoginstatus() {
        return mPref.getString(loginstatus, "");
    }

    //dutystatus
    public void setDutystatus(String value) {
        mPref.edit().putString(dutystatus, value).apply();
    }

    public String getDutystatus() {
        return mPref.getString(dutystatus, "");
    }

    //image
    public void setImage(String value) {
        mPref.edit().putString(image, value).apply();
    }
    public void setPk_userId(String value) {
        mPref.edit().putString(pk_userId, value).apply();
    }


    public String getImage() {
        return mPref.getString(image, "");
    }

    //address
    public void setAddress(String value) {
        mPref.edit().putString(address, value).apply();
    }

    public String getAddress() {
        return mPref.getString(address, "");
    }

    //zipcode
    public void setZipcode(String value) {
        mPref.edit().putString(zipcode, value).apply();
    }

    public String getZipcode() {
        return mPref.getString(zipcode, "");
    }

    //vehicleno
    public void setVehicleno(String value) {
        mPref.edit().putString(vehicleno, value).apply();
    }

    public String getVehicleno() {
        return mPref.getString(vehicleno, "");
    }

    //vehiclename
    public void setVehiclename(String value) {
        mPref.edit().putString(vehiclename, value).apply();
    }

    public String getVehiclename() {
        return mPref.getString(vehiclename, "");
    }

    //operationcityid
    public void setOperationcityid(String value) {
        mPref.edit().putString(operationcityid, value).apply();
    }

    public String getOperationcityid() {
        return mPref.getString(operationcityid, "");
    }

    //countdocs
    public void setCountdocs(String value) {
        mPref.edit().putString(countdocs, value).apply();
    }

    public String getCountdocs() {
        return mPref.getString(countdocs, "");
    }


    //vehicleimage

    public void setVehicleimage(String value) {
        mPref.edit().putString(vehicleimage, value).apply();
    }

    public String getVehicleimage() {
        return mPref.getString(vehicleimage, "");
    }

    //bookingtableid
    public void setBookingtableid(String value) {
        mPref.edit().putString(bookingtableid, value).apply();
    }

    public String getBookingtableid() {
        return mPref.getString(bookingtableid, "");
    }

    //language
    public void setLanguage(String value) {
        mPref.edit().putString(language, value).apply();
    }

    public String getLanguage() {
        return mPref.getString(language, "");
    }
//pk_profileId

    //mobileno
    public void setPK_profileId(String value) {
        mPref.edit().putString(PK_profileId, value).apply();
    }

    public String getPK_profileId() {
        return mPref.getString(PK_profileId, "");
    }


}
