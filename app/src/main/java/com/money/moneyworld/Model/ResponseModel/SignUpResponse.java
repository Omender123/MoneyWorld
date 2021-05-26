package com.money.moneyworld.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUpResponse {
    @SerializedName("statusCode")
    @Expose
    private String statusCode;
    @SerializedName("APICODERESULT")
    @Expose
    private String apicoderesult;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("otp")
    @Expose
    private String otp;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getApicoderesult() {
        return apicoderesult;
    }

    public void setApicoderesult(String apicoderesult) {
        this.apicoderesult = apicoderesult;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
