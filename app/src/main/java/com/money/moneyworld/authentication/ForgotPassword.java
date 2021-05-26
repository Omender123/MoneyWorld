package com.money.moneyworld.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.money.moneyworld.Model.ResponseModel.ForgetResponse;
import com.money.moneyworld.R;
import com.money.moneyworld.SharedPerfence.MyPreferences;
import com.money.moneyworld.SharedPerfence.PrefConf;
import com.money.moneyworld.databinding.ActivityForgotPasswordBinding;
import com.money.moneyworld.utils.AppUtils;
import com.money.moneyworld.view_presenter.ForgetPresenter;
import com.money.moneyworld.view_presenter.SignUpPresenter;

import de.mateware.snacky.Snacky;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener, ForgetPresenter.ForgetView {
ActivityForgotPasswordBinding binding;
    private Context context;
    private Dialog dialog;
    private ForgetPresenter presenter;
    private View view;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);

        view = binding.getRoot();
        context = ForgotPassword.this;
        presenter = new ForgetPresenter(this);
        dialog = AppUtils.hideShowProgress(context);
        binding.cardNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_next:

                forgetPassword();
                 break;
        }
    }

    private void forgetPassword() {
        phone = binding.email.getText().toString().trim();
        if (phone.isEmpty()){
            binding.email.requestFocus();
            Snacky.builder()
                    .setActivity(ForgotPassword.this)
                    .setText("Enter a valid Mobile Number  !")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        }else {

            presenter.VerifyUser(phone);
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
        Snackbar.make(view,"Mobile Number Not Found",Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void onSuccess(ForgetResponse forgetResponse, String message) {

        MyPreferences.getInstance(context).putString(PrefConf.KEY_USER_OTP,forgetResponse.getData());
        MyPreferences.getInstance(context).putString(PrefConf.KEY_USER_PHONE,phone);
        Toast.makeText(context, ""+forgetResponse.getApicoderesult(), Toast.LENGTH_SHORT).show();
        if (message.equalsIgnoreCase("ok")){
            Intent intent = new Intent(this, Verify_otp.class);
            intent.putExtra("type","forgotPassword");
            startActivity(intent);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Snackbar.make(view,t.getLocalizedMessage(),Snackbar.LENGTH_SHORT).show();
    }
}