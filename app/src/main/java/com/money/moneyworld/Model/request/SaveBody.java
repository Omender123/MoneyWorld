package com.money.moneyworld.Model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveBody {
    @SerializedName("amount")
    @Expose
    public String amount;
    @SerializedName("order_id")
    @Expose
    public String orderId;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("payment_id")
    @Expose
    public String paymentId;

    public SaveBody(String amount, String orderId, String status, String paymentId) {
        this.amount = amount;
        this.orderId = orderId;
        this.status = status;
        this.paymentId = paymentId;
    }
}
