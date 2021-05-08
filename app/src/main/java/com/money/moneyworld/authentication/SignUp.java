package com.money.moneyworld.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);

        binding.cardSignup.setOnClickListener(this);
        binding.textLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.card_signup:
               startActivity(new Intent(getApplicationContext(),Verify_otp.class).putExtra("type","signup"));
                break;
            case R.id.text_login:
                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
                break;
        }
    }
}