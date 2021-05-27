package com.money.moneyworld.view_presenter;

import android.content.Context;

import com.money.moneyworld.Model.ResponseModel.CreateOderIdResponse;
import com.money.moneyworld.Model.request.CreateOrderIdBody;
import com.money.moneyworld.Model.request.SaveBody;
import com.money.moneyworld.utils.AppUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartialPaymentPresenter {
    private PartialPaymentView view;

    public PartialPaymentPresenter(PartialPaymentView view) {
        this.view = view;
    }
    public void CreateOderID(CreateOrderIdBody orderIdBody) {
        view.showHideProgress(true);
        Call<CreateOderIdResponse> call = AppUtils.getApi((Context) view).CreateOderID(orderIdBody);
        call.enqueue(new Callback<CreateOderIdResponse>() {
            @Override
            public void onResponse(Call<CreateOderIdResponse> call, Response<CreateOderIdResponse> response) {
                view.showHideProgress(false);
                if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                    view.onCreateOrderID(response.body());
                } else if (response.code()==400) {
                    try {
                        String errorRes = response.errorBody().string();
                        JSONObject object = new JSONObject(errorRes);
                        String err_msg = object.getString("APICODERESULT");
                        view.onError(err_msg);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CreateOderIdResponse> call, Throwable t) {
                view.showHideProgress(false);
                view.onFailure(t);

            }
        });
    }
        public void savePaymentData(SaveBody saveBody, Context context){
            view.showHideProgress(true);
            Call<ResponseBody>call = AppUtils.getApi(context).SavePayment(saveBody);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    view.showHideProgress(false);
                    if (response.isSuccessful() && response.code() == 200 && response.body() != null) {
                        view.onRazorDataSaved(response.message());
                    } else {
                        try {
                            String errorRes = response.errorBody().string();
                            JSONObject object = new JSONObject(errorRes);
                            String err_msg = object.getString("APICODERESULT");
                            view.onError(err_msg);
                        }
                        catch (Exception ex){
                            ex.printStackTrace();
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

    


        public interface PartialPaymentView{
        void showHideProgress(boolean isShow);
        void onError(String message);
        void onCreateOrderID(CreateOderIdResponse createOderIdResponse);
        void onRazorDataSaved(String  message);
        void onFailure(Throwable t);
    }
}
