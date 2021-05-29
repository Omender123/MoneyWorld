package com.money.moneyworld.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.money.moneyworld.Model.ResponseModel.LoginResponse;
import com.money.moneyworld.Model.ResponseModel.WalletBalanceResponse;
import com.money.moneyworld.R;
import com.money.moneyworld.SharedPrefernce.SharedPrefManager;
import com.money.moneyworld.UI.Activity.Add_Money;
import com.money.moneyworld.databinding.FragmentContactUsBinding;
import com.money.moneyworld.databinding.FragmentWalletBinding;
import com.money.moneyworld.utils.AppUtils;
import com.money.moneyworld.view_presenter.PartialPaymentPresenter;
import com.money.moneyworld.view_presenter.WalletBalancePresenter;
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;

public class Wallet extends Fragment implements SingleSelectToggleGroup.OnCheckedChangeListener, WalletBalancePresenter.WalletBalanceView {

    private FragmentWalletBinding binding;
    private View view;
    private Dialog dialog;
    private WalletBalancePresenter presenter;
    LoginResponse.Logindetails logindetails;
    public Wallet() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet,container,false);

        logindetails = SharedPrefManager.getInstance(getContext()).getUser();

        view = binding.getRoot();
        dialog = AppUtils.hideShowProgress(getContext());
        presenter = new WalletBalancePresenter(this);

        binding.toggleGroup.setOnCheckedChangeListener(this);

        presenter.WalletBalanceView(logindetails.getUserId(),getContext());
     
        return binding.getRoot();}


    @Override
    public void onCheckedChanged(SingleSelectToggleGroup group, int checkedId) {
           switch (group.getCheckedId()){
               case R.id.add:
                   startActivity(new Intent(getContext(), Add_Money.class));
                   break;

               case R.id.withdraw:
                   Toast.makeText(getContext(), "WithDraw Money", Toast.LENGTH_SHORT).show();

                   break;
           }


    }

    @Override
    public void showHideProgress(boolean isShow) {

        if (isShow){
            dialog.show();
        }else{
            dialog.dismiss();
        }
    }

    @Override
    public void onError(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(WalletBalanceResponse walletBalanceResponse, String message) {
        if (message.equalsIgnoreCase("ok")){
            String balance = walletBalanceResponse.getWalletamount();
            binding.balText.setText("₹ "+balance);
        }

    }

    @Override
    public void onFailure(Throwable t) {
        Snackbar.make(view,t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
    }
}