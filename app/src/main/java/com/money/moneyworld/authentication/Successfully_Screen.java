package com.money.moneyworld.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivitySuccessfullyScreenBinding;

public class Successfully_Screen extends AppCompatActivity implements View.OnClickListener {
ActivitySuccessfullyScreenBinding binding;

String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_successfully__screen);
        try {
            Bundle bundle = getIntent().getExtras();
            type = bundle.getString("type");
        } catch (Exception e) {
        }
        ;

        if (type.equalsIgnoreCase("signup")){
            binding.textCreateAccount.setText("Account is created");

        }else {
            binding.textCreateAccount.setText("Password is Change");
        }

        // binding.textCreateAccount.setText("");
        binding.btnDone.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_done:
            startActivity(new Intent(getApplicationContext(),SignIn.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
            break;
        }
    }
}