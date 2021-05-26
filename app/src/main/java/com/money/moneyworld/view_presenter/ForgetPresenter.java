package com.money.moneyworld.view_presenter;

import android.content.Context;

import com.money.moneyworld.Model.ResponseModel.ForgetResponse;
import com.money.moneyworld.Model.request.OTP_VerifyModel;
import com.money.moneyworld.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPresenter {
    private ForgetView view;

    public ForgetPresenter(ForgetView view) {
        this.view = view;
    }

    public void VerifyUser(String mobile){
        view.showHideProgress(true);
        Call<ForgetResponse> userCall = AppUtils.getApi((Context)view).ForgetPassword(mobile);
        userCall.enqueue(new Callback<ForgetResponse>() {
            @Override
            public void onResponse(Call<ForgetResponse> call, Response<ForgetResponse> response) {
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
            public void onFailure(Call<ForgetResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }

    public interface ForgetView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onSuccess(ForgetResponse  forgetResponse,String message);
        void onFailure(Throwable t);
    }
}
