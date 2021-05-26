package com.money.moneyworld.view_presenter;

import android.content.Context;

import com.money.moneyworld.Model.ResponseModel.SignUpResponse;
import com.money.moneyworld.Model.request.User;
import com.money.moneyworld.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter {

    private SignUpView view;

    public SignUpPresenter(SignUpView view) {
        this.view = view;
    }

    public void createUser(User user){
        view.showHideProgress(true);
        Call<SignUpResponse> userCall = AppUtils.getApi((Context)view).createUser(user);
        userCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
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
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);
            }
        });
    }

    public interface SignUpView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onSuccess(SignUpResponse response, String message);
        void onFailure(Throwable t);
    }
}
