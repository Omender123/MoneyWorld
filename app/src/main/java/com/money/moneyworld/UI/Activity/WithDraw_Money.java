package com.money.moneyworld.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivityWithDrawMoneyBinding;
import com.money.moneyworld.utils.AppUtils;

public class WithDraw_Money extends AppCompatActivity {
    ActivityWithDrawMoneyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_with_draw__money);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_with_draw__money);

        AppUtils.setUpToolbar(this, binding.toolbar, true, true, "Withdraw Money");
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));


    }
}