package com.money.moneyworld.Model.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WalletBalanceResponse {
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("APICODERESULT")
    @Expose
    private String apicoderesult;
    @SerializedName("walletamount")
    @Expose
    private String walletamount;


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

    public String getWalletamount() {
        return walletamount;
    }

    public void setWalletamount(String walletamount) {
        this.walletamount = walletamount;
    }


}
