package com.money.moneyworld.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivityChangePasswordBinding;

public class Change_Password extends AppCompatActivity implements View.OnClickListener {
ActivityChangePasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_change__password);

        binding.cardDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_Done:
                startActivity(new Intent(getApplicationContext(), Successfully_Screen.class));
                break;
        }
    }
}