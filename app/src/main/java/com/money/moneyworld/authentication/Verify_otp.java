package com.money.moneyworld.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.money.moneyworld.Model.request.OTP_VerifyModel;
import com.money.moneyworld.R;
import com.money.moneyworld.SharedPerfence.MyPreferences;
import com.money.moneyworld.SharedPerfence.PrefConf;
import com.money.moneyworld.databinding.ActivityVerifyOtpBinding;
import com.money.moneyworld.utils.AppUtils;
import com.money.moneyworld.view_presenter.ForgetOtpVerifyPresenter;
import com.money.moneyworld.view_presenter.OTP_VerifyPresenter;
import com.money.moneyworld.view_presenter.SignUpPresenter;

import de.mateware.snacky.Snacky;

public class Verify_otp extends AppCompatActivity implements View.OnClickListener, OTP_VerifyPresenter.OTP_VerifyView, ForgetOtpVerifyPresenter.Forget_OTP_VerifyView {
ActivityVerifyOtpBinding binding;
    private Context context;
    private Dialog dialog;
    private OTP_VerifyPresenter presenter;
    private ForgetOtpVerifyPresenter presenter1;
    private View view;

    String type,otp,Number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_verify_otp);

        view = binding.getRoot();
        context = Verify_otp.this;
        presenter = new OTP_VerifyPresenter(this);
        presenter1 = new ForgetOtpVerifyPresenter(this);
        dialog = AppUtils.hideShowProgress(context);

        try {
            Bundle bundle = getIntent().getExtras();
            type =bundle.getString("type");
        }catch (Exception e){};


        otp =   MyPreferences.getInstance(Verify_otp.this).getString(PrefConf.KEY_USER_OTP,"");
        Number =   MyPreferences.getInstance(Verify_otp.this).getString(PrefConf.KEY_USER_PHONE,"");


        setOtp();

        binding.cardDone.setOnClickListener(this);



    }

    private void setOtp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.enterOtp.setText(otp);
            }
        },1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_done:
                if (type.equalsIgnoreCase("signup")){
                  otpVerify();
                }else {
                  //  startActivity(new Intent(getApplicationContext(),Change_Password.class));

                    ForgetotpVerify();
                }
                break;

        }
    }

    private void ForgetotpVerify() {

        String otp = binding.enterOtp.getText().toString().trim();

        if (otp.isEmpty()){

            binding.enterOtp.requestFocus();
            Snacky.builder()
                    .setActivity(Verify_otp.this)
                    .setText("Please enter Otp")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        }else{
            presenter1.ForgetOTPVerify(Number,otp);
        }
    }


    private void otpVerify() {

        String otp = binding.enterOtp.getText().toString().trim();

        if (otp.isEmpty()){

            binding.enterOtp.requestFocus();
            Snacky.builder()
                    .setActivity(Verify_otp.this)
                    .setText("Please enter Otp")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        }else{

            OTP_VerifyModel otp_verifyModel = new OTP_VerifyModel(Number,otp);
            presenter.VerifyUser(otp_verifyModel);
        }
    }

    @Override
    public void showHideProgress(boolean isShow) {
        if (isShow){
            dialog.show();
        }else {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(String message) {

        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String message) {
        if (message.equalsIgnoreCase("ok")){
            Intent intent = new Intent(this, Successfully_Screen.class);
            intent.putExtra("type","signup");
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Snackbar.make(view,t.getLocalizedMessage(),Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showHideProgressForget(boolean isShow) {
        if (isShow){
            dialog.show();
        }else {
            dialog.dismiss();
        }
    }

    @Override
    public void onForgetError(String message) {
        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onForgetSuccess(String message) {
        if (message.equalsIgnoreCase("ok")){
            Intent intent = new Intent(this, Change_Password.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onForgetFailure(Throwable t) {
        Snackbar.make(view,t.getLocalizedMessage(),Snackbar.LENGTH_SHORT).show();
    }
}