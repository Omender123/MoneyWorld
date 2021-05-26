package com.money.moneyworld.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.money.moneyworld.MainActivity;
import com.money.moneyworld.Model.ResponseModel.LoginResponse;
import com.money.moneyworld.Model.request.Login;
import com.money.moneyworld.R;
import com.money.moneyworld.SharedPrefernce.SharedPrefManager;
import com.money.moneyworld.databinding.ActivitySignInBinding;
import com.money.moneyworld.utils.AppUtils;
import com.money.moneyworld.view_presenter.LoginPresenter;
import com.money.moneyworld.view_presenter.SignUpPresenter;

import de.mateware.snacky.Snacky;

public class SignIn extends AppCompatActivity implements View.OnClickListener, LoginPresenter.LoginView {
    ActivitySignInBinding binding;

    private Context context;
    private Dialog dialog;
    private LoginPresenter presenter;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);

        view = binding.getRoot();
        context = SignIn.this;
        presenter = new LoginPresenter(this);
        dialog = AppUtils.hideShowProgress(context);

        if (SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()) {
            startActivity(new Intent(SignIn.this, MainActivity.class));
            finish();
        }
        binding.textSign.setOnClickListener(this);
        binding.cardLogin.setOnClickListener(this);
        binding.forgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_Login:
                GoLogin();
              //  startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.text_Sign:
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
                break;

            case R.id.forgot:
                startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
                break;
        }
    }

    private void GoLogin() {
        String moblie = binding.email.getText().toString().trim();
        String password = binding.password.getText().toString().trim();

        if (moblie.isEmpty() || password.isEmpty()){
            Snacky.builder()
                    .setActivity(SignIn.this)
                    .setText("Please Enter all detail")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        }else{

            Login  login = new Login(moblie,password);
            presenter.loginUser(login);
        }

    }

    @Override
    public void showHideLoginProgress(boolean isShow) {
        if (isShow){
            dialog.show();
        }else {
            dialog.dismiss();
        }
    }

    @Override
    public void onLoginError(String message) {
        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginSuccess(LoginResponse response, String message) {
        SharedPrefManager.getInstance(getApplicationContext()).userLogin(response.getLogindetails());
        if (message.equalsIgnoreCase("ok")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

            Snackbar.make(view,response.getApicoderesult(),Snackbar.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onLoginFailure(Throwable t) {
        Snackbar.make(view,t.getLocalizedMessage(),Snackbar.LENGTH_SHORT).show();
    }
}