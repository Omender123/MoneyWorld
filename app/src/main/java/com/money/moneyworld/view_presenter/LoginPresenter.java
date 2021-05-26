package com.money.moneyworld.view_presenter;

import android.content.Context;

import com.money.moneyworld.Model.ResponseModel.LoginResponse;
import com.money.moneyworld.Model.request.Login;
import com.money.moneyworld.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }
    public void loginUser(Login credential) {
        view.showHideLoginProgress(true);
        Call<LoginResponse> loginCall = AppUtils.getApi((Context) view).doLogin(credential);

        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                view.showHideLoginProgress(false);

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {

                        view.onLoginSuccess(response.body(), response.message());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (response.code()==400) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("APICODERESULT");
                        view.onLoginError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.showHideLoginProgress(false);
                view.onLoginFailure(t);
            }
        });

    }

    public  interface  LoginView{
        void showHideLoginProgress(boolean isShow);
        void onLoginError(String message);
        void onLoginSuccess(LoginResponse response, String message);
        void onLoginFailure(Throwable t);
    }
}
