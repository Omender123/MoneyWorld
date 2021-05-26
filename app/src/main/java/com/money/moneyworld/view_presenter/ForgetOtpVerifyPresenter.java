package com.money.moneyworld.view_presenter;

import android.content.Context;

import com.money.moneyworld.Model.request.OTP_VerifyModel;
import com.money.moneyworld.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetOtpVerifyPresenter {
    private Forget_OTP_VerifyView view;

    public ForgetOtpVerifyPresenter(Forget_OTP_VerifyView view) {
        this.view = view;
    }

    public void ForgetOTPVerify(String  mobile,String otp){
        view.showHideProgressForget(true);
        Call<ResponseBody> userCall = AppUtils.getApi((Context)view).ForgetOtpVerify(mobile,otp);
        userCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.showHideProgressForget(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onForgetSuccess(response.message());
                } else if (response.code()==400){
                    try {
                        String  errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg  = object.getString("APICODERESULT");
                        view.onForgetError("OTP is Incorrect!!!!!!!!");

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showHideProgressForget(false);
                view.onForgetFailure(t);
            }
        });
    }

    public interface Forget_OTP_VerifyView{
        void showHideProgressForget(boolean isShow);
        void onForgetError(String message);
        void onForgetSuccess(String message);
        void onForgetFailure(Throwable t);
    }
    
}
