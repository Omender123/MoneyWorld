package com.money.moneyworld.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivityForgotPasswordBinding;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {
ActivityForgotPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);

        binding.cardNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_next:
                startActivity(new Intent(getApplicationContext(),Verify_otp.class).putExtra("type","forgotPassword"));
                break;
        }
    }
}