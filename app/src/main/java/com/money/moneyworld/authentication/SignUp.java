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
import com.money.moneyworld.Model.ResponseModel.SignUpResponse;
import com.money.moneyworld.Model.request.User;
import com.money.moneyworld.R;
import com.money.moneyworld.SharedPerfence.MyPreferences;
import com.money.moneyworld.SharedPerfence.PrefConf;
import com.money.moneyworld.databinding.ActivitySignUpBinding;
import com.money.moneyworld.utils.AppUtils;
import com.money.moneyworld.utils.Validation;
import com.money.moneyworld.view_presenter.SignUpPresenter;

import de.mateware.snacky.Snacky;

public class SignUp extends AppCompatActivity implements View.OnClickListener, SignUpPresenter.SignUpView {
ActivitySignUpBinding binding;
    private Context context;
    private Dialog dialog;
    private SignUpPresenter presenter;
    private View view;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);

        view = binding.getRoot();
        context = SignUp.this;
        presenter = new SignUpPresenter(this);
        dialog = AppUtils.hideShowProgress(context);


        binding.cardSignup.setOnClickListener(this);
        binding.textLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_signup:
                doRegister();
                break;
            case R.id.text_login:
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showHideProgress(boolean isShow) {
        if(isShow){
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
    public void onSuccess(SignUpResponse response,String message) {
        MyPreferences.getInstance(context).putString(PrefConf.KEY_USER_OTP,response.getOtp());
        MyPreferences.getInstance(context).putString(PrefConf.KEY_USER_PHONE,phone);
        Toast.makeText(context, ""+response.getApicoderesult(), Toast.LENGTH_SHORT).show();
        if (message.equalsIgnoreCase("ok")){
            Intent intent = new Intent(this, Verify_otp.class);
            intent.putExtra("type","signup");
            startActivity(intent);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Snackbar.make(view,t.getLocalizedMessage(),Snackbar.LENGTH_SHORT).show();
    }

    private  void  doRegister(){
        String Name = binding.name.getText().toString().trim();
        String email = binding.email.getText().toString().trim();
         phone = binding.number.getText().toString().trim();
        String pass = binding.password.getText().toString().trim();
        String c_pass = binding.cPassword.getText().toString().trim();
        if(Name.isEmpty()){
            binding.name.requestFocus();
            Snacky.builder()
                    .setActivity(SignUp.this)
                    .setText("Empty Field!")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();

        }else if(email.isEmpty()){
            binding.email.requestFocus();
            Snacky.builder()
                    .setActivity(SignUp.this)
                    .setText("Empty Field!")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();

        }else if(!Validation.isValidEmail(email)){
            binding.email.requestFocus();
            Snacky.builder()
                    .setActivity(SignUp.this)
                    .setText("Enter a valid email!")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        } else if(phone.isEmpty()){
            binding.number.requestFocus();
            Snacky.builder()
                    .setActivity(SignUp.this)
                    .setText("Empty Field!")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
          //  binding.number.setError("Empty Field!");
        }else if(!Validation.isValidPhoneNumber(phone)){
            binding.number.requestFocus();
            Snacky.builder()
                    .setActivity(SignUp.this)
                    .setText("Phone number not valid!")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
           }else if(pass.isEmpty()){
            binding.password.requestFocus();
            Snacky.builder()
                    .setActivity(SignUp.this)
                    .setText("Empty Field!")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
          //  binding.password.setError("");
        }else if (c_pass.isEmpty()){
            binding.cPassword.requestFocus();
            Snacky.builder()
                    .setActivity(SignUp.this)
                    .setText("Empty Field!")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        }else if(!pass.equals(c_pass)){
            binding.cPassword.requestFocus();
            Snacky.builder()
                    .setActivity(SignUp.this)
                    .setText("Confirm Password!")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
         //   binding.cPassword.setError("Confirm Password!");
        }
        else {


            User user = new User(Name,phone,pass,email);
            presenter.createUser(user);
        }


    }

}