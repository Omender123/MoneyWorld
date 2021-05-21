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
import com.money.moneyworld.UI.Activity.PlayingGame1;
import com.money.moneyworld.UI.Activity.PlayingGame2;
import com.money.moneyworld.databinding.FragmentContactUsBinding;
import com.money.moneyworld.databinding.FragmentHomeBinding;


public class Home extends Fragment implements View.OnClickListener {
    private FragmentHomeBinding binding;
    private View view;

    public Home() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);
        view = binding.getRoot();

        binding.cardBatting.setOnClickListener(this);
        binding.cardBatting1.setOnClickListener(this);
        return binding.getRoot();}

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.card_batting:
                intent = new Intent(getContext(), PlayingGame1.class);
                break;

            case R.id.card_batting1:
               intent = new Intent(getContext(), PlayingGame2.class);
                break;

        }

        if (intent!=null){
            startActivity(intent);
        }
    }
}