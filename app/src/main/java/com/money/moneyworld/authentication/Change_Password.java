package com.money.moneyworld.authentication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.money.moneyworld.R;
import com.money.moneyworld.SharedPerfence.MyPreferences;
import com.money.moneyworld.SharedPerfence.PrefConf;
import com.money.moneyworld.databinding.ActivityChangePasswordBinding;
import com.money.moneyworld.utils.AppUtils;
import com.money.moneyworld.view_presenter.ForgetPresenter;
import com.money.moneyworld.view_presenter.UpdatePasswordPresenter;

import de.mateware.snacky.Snacky;

public class Change_Password extends AppCompatActivity implements View.OnClickListener, UpdatePasswordPresenter.UpdatePassword {
ActivityChangePasswordBinding binding;
Context context;
    private Dialog dialog;
    private UpdatePasswordPresenter presenter;
    private View view;
    String  Number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_change__password);

        view = binding.getRoot();
        context = Change_Password.this;
        presenter = new UpdatePasswordPresenter(this);
        dialog = AppUtils.hideShowProgress(context);

        Number =   MyPreferences.getInstance(Change_Password.this).getString(PrefConf.KEY_USER_PHONE,"");

        binding.cardDone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_Done:
               ChangePassword();
                break;
        }
    }

    private void ChangePassword() {
        String newPassword = binding.newPassword.getText().toString().trim();
        String C_Password = binding.cPassword.getText().toString().trim();

        if (newPassword.isEmpty() || C_Password.isEmpty()){
            Snacky.builder()
                    .setActivity(Change_Password.this)
                    .setText("Please enter all Details")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        }else if(!newPassword.equals(C_Password)) {
            binding.cPassword.requestFocus();
            Snacky.builder()
                    .setActivity(Change_Password.this)
                    .setText("Confirm Password!")
                    .setTextColor(getResources().getColor(R.color.white))
                    .warning()
                    .show();
        }else{
            presenter.UpdatePassword(Number,newPassword);
        }
    }

    @Override
    public void showHideProgress(boolean isShow) {
        if (isShow){
            dialog.show();
        }else {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(String message) {
        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String message) {

        if (message.equalsIgnoreCase("ok")){
            Intent intent = new Intent(Change_Password.this, Successfully_Screen.class);
            intent.putExtra("type","forgotPassword");
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Snackbar.make(view,t.getLocalizedMessage(),Snackbar.LENGTH_SHORT).show();
    }
}