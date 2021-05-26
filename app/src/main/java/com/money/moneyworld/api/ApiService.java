package com.money.moneyworld.api;


import com.money.moneyworld.Model.ResponseModel.LoginResponse;
import com.money.moneyworld.Model.ResponseModel.SignUpResponse;
import com.money.moneyworld.Model.request.Login;
import com.money.moneyworld.Model.request.OTP_VerifyModel;
import com.money.moneyworld.Model.request.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("restapi/register")
    Call<SignUpResponse> createUser(@Body User user);

    @POST("restapi/login")
    Call<LoginResponse> doLogin(@Body Login credential);

    @POST("restapi/otpverify")
    Call<ResponseBody> OtpVerify(@Body OTP_VerifyModel otp);

}
