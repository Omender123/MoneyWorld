package com.money.moneyworld.view_presenter;

import android.content.Context;

import com.money.moneyworld.Model.ResponseModel.WalletBalanceResponse;
import com.money.moneyworld.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletBalancePresenter {
    private WalletBalanceView view;

    public WalletBalancePresenter(WalletBalanceView view) {
        this.view = view;
    }

    
    public void WalletBalanceView(String userId,Context context){
        view.showHideProgress(true);
        Call<WalletBalanceResponse> userCall = AppUtils.getApi(context).WALLET_BALANCE(userId);
        userCall.enqueue(new Callback<WalletBalanceResponse>() {
            @Override
            public void onResponse(Call<WalletBalanceResponse> call, Response<WalletBalanceResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onSuccess(response.body(),response.message());
                } else if (response.code()==400){
                    try {
                        String  errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg  = object.getString("APICODERESULT");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<WalletBalanceResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });

    }
    
    public interface WalletBalanceView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onSuccess(WalletBalanceResponse walletBalanceResponse,String message);
        void onFailure(Throwable t);
    }
}
