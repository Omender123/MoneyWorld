package com.money.moneyworld.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivitySuccessfullyScreenBinding;

public class Successfully_Screen extends AppCompatActivity implements View.OnClickListener {
ActivitySuccessfullyScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_successfully__screen);

       // binding.textCreateAccount.setText("");
        binding.btnDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_done:
            startActivity(new Intent(getApplicationContext(),SignIn.class));
            break;
        }
    }
}