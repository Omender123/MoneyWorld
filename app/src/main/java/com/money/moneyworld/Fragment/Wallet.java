package com.money.moneyworld.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.money.moneyworld.R;
import com.money.moneyworld.UI.Activity.Add_Money;
import com.money.moneyworld.databinding.FragmentContactUsBinding;
import com.money.moneyworld.databinding.FragmentWalletBinding;
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;

public class Wallet extends Fragment implements SingleSelectToggleGroup.OnCheckedChangeListener {

    private FragmentWalletBinding binding;
    private View view;
    public Wallet() {
        // Required empty public constructor
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet,container,false);
        view = binding.getRoot();

        binding.toggleGroup.setOnCheckedChangeListener(this);
     
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
}