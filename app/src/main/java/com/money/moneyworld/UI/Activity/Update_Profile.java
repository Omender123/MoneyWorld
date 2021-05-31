package com.money.moneyworld.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivityUpdateProfileBinding;
import com.money.moneyworld.utils.AppUtils;

public class Update_Profile extends AppCompatActivity {
ActivityUpdateProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_update__profile);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update__profile);

        AppUtils.setUpToolbar(this, binding.toolbar, true, true, "Update Profile");
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));

    }

}