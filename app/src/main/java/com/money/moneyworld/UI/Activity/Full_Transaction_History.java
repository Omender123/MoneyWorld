package com.money.moneyworld.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivityFullTransactionHistoryBinding;
import com.money.moneyworld.utils.AppUtils;

public class Full_Transaction_History extends AppCompatActivity {
ActivityFullTransactionHistoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_full__transaction__history);
        AppUtils.setUpToolbar(this,binding.toolbar,true,true,"Transaction History");
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        binding.showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}