package com.money.moneyworld.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetResponse {
    private String statusCode;
    @SerializedName("APICODERESULT")
    @Expose
    private String apicoderesult;
    @SerializedName("data")
    @Expose
    private String data;

   
    public ForgetResponse() {
    }
    public ForgetResponse(String statusCode, String apicoderesult, String data) {
        super();
        this.statusCode = statusCode;
        this.apicoderesult = apicoderesult;
        this.data = data;
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
