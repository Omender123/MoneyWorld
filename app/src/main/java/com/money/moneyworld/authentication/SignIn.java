package com.money.moneyworld.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.money.moneyworld.MainActivity;
import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivitySignInBinding;

public class SignIn extends AppCompatActivity implements View.OnClickListener {
ActivitySignInBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_in);

        binding.textSign.setOnClickListener(this);
        binding.cardLogin.setOnClickListener(this);
        binding.forgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_Login:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.text_Sign:
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
                break;

            case R.id.forgot:
               startActivity(new Intent(getApplicationContext(),ForgotPassword.class));
                break;
        }
    }
}