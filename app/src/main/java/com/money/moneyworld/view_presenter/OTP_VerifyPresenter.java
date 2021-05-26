package com.money.moneyworld.view_presenter;

import android.content.Context;
import com.money.moneyworld.Model.request.OTP_VerifyModel;
import com.money.moneyworld.Model.request.User;
import com.money.moneyworld.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTP_VerifyPresenter {
    private OTP_VerifyView view;

    public OTP_VerifyPresenter(OTP_VerifyView view) {
        this.view = view;
    }

    public void VerifyUser(OTP_VerifyModel otpVerifyModel){
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi((Context)view).OtpVerify(otpVerifyModel);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onSuccess(response.message());
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
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }

    public interface OTP_VerifyView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onSuccess(String message);
        void onFailure(Throwable t);
    }
}
