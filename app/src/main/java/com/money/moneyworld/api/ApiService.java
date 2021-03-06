package com.money.moneyworld.api;


import com.money.moneyworld.Model.ResponseModel.CreateOderIdResponse;
import com.money.moneyworld.Model.ResponseModel.ForgetResponse;
import com.money.moneyworld.Model.ResponseModel.LoginResponse;
import com.money.moneyworld.Model.ResponseModel.SignUpResponse;
import com.money.moneyworld.Model.ResponseModel.UploadProfileResponse;
import com.money.moneyworld.Model.ResponseModel.WalletBalanceResponse;
import com.money.moneyworld.Model.request.CreateOrderIdBody;
import com.money.moneyworld.Model.request.Login;
import com.money.moneyworld.Model.request.OTP_VerifyModel;
import com.money.moneyworld.Model.request.SaveBody;
import com.money.moneyworld.Model.request.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {

    @POST("restapi/register")
    Call<SignUpResponse> createUser(@Body User user);

    @POST("restapi/login")
    Call<LoginResponse> doLogin(@Body Login credential);

    @POST("restapi/otpverify")
    Call<ResponseBody> OtpVerify(@Body OTP_VerifyModel otp);

    @FormUrlEncoded
    @POST("restapi/otpforpasswordchange")
    Call<ForgetResponse> ForgetPassword(
          @Field("mobile") String mobile
    );

    @FormUrlEncoded
    @POST("restapi/otpmatch")
    Call<ResponseBody> ForgetOtpVerify(
            @Field("mobile") String mobile,
            @Field("otp") String otp
    );

    @FormUrlEncoded
    @POST("restapi/updatepassword")
    Call<ResponseBody> UpdatePassword(
            @Field("mobile") String mobile,
            @Field("password") String otp
    );


    @FormUrlEncoded
    @POST("restapi/logout")
    Call<ResponseBody> LogOut(
            @Field("user_id") String mobile,
            @Field("live") String otp
    );

    @POST("restapi/createorder")
    Call<CreateOderIdResponse> CreateOderID(@Body CreateOrderIdBody orderIdBody );

    @POST("restapi/walletrazorpayresponse")
    Call<ResponseBody> SavePayment(@Body SaveBody saveBody );


    @FormUrlEncoded
    @POST("restapi/walletdetails")
    Call<WalletBalanceResponse>WALLET_BALANCE(
            @Field("user_id") String userId
    );

    @Multipart
    @POST("restapi/profileupdate")
    Call<UploadProfileResponse>Upload_image(
            @Part("user_id") RequestBody userId,
            @Part MultipartBody.Part image
            );

}
