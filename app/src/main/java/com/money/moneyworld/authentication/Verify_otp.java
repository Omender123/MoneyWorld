package com.money.moneyworld.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivityVerifyOtpBinding;

public class Verify_otp extends AppCompatActivity implements View.OnClickListener {
ActivityVerifyOtpBinding binding;
String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_verify_otp);

        binding.cardDone.setOnClickListener(this);

       try {
           Bundle bundle = getIntent().getExtras();
           type =bundle.getString("type");
       }catch (Exception e){};


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_done:
                if (type.equalsIgnoreCase("signup")){
                    startActivity(new Intent(getApplicationContext(),Successfully_Screen.class));
                }else {
                    startActivity(new Intent(getApplicationContext(),Change_Password.class));
                }
                break;

        }
    }
}