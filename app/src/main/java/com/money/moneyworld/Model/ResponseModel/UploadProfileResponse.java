package com.money.moneyworld.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadProfileResponse {
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("APICODERESULT")
    @Expose
    private String apicoderesult;
    @SerializedName("profile")
    @Expose
    private String profile;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getApicoderesult() {
        return apicoderesult;
    }

    public void setApicoderesult(String apicoderesult) {
        this.apicoderesult = apicoderesult;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


}
