package com.money.moneyworld.view_presenter;

import android.content.Context;

import com.money.moneyworld.Model.ResponseModel.UploadProfileResponse;
import com.money.moneyworld.Model.request.OTP_VerifyModel;
import com.money.moneyworld.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogOutPresenter {
    private LogOutPresenterView view;

    public LogOutPresenter(LogOutPresenterView view) {
        this.view = view;
    }

    public void LogOutUser(String userId, String status){
        view.showHideProgress(true);
        Call<ResponseBody> userCall = AppUtils.getApi((Context)view).LogOut(userId,status);
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
    public void UploadProfile(RequestBody userId, RequestBody image){
        view.showHideProgress(true);
        Call<UploadProfileResponse> userCall = AppUtils.getApi((Context)view).Upload_image(userId,image);
        userCall.enqueue(new Callback<UploadProfileResponse>() {
            @Override
            public void onResponse(Call<UploadProfileResponse> call, Response<UploadProfileResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onProfileUploadSuccess(response.body(),response.message());
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
            public void onFailure(Call<UploadProfileResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }

    public interface LogOutPresenterView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onSuccess(String message);
        void onProfileUploadSuccess(UploadProfileResponse uploadProfileResponse,String message);
        void onFailure(Throwable t);
    }

}
