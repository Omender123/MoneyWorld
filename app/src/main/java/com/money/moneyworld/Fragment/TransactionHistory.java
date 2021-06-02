package com.money.moneyworld.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.money.moneyworld.Adapter.Transaaction_history_adapter;
import com.money.moneyworld.R;
import com.money.moneyworld.databinding.FragmentTransactionHistoryBinding;


public class TransactionHistory extends Fragment {
    FragmentTransactionHistoryBinding binding;
    View view;
    String [] price = {"₹ 210","₹ 210","₹ 210","₹ 210","₹ 210","₹ 210"};

    public TransactionHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_history,container,false);
        view = binding.getRoot();

        getAllprouct();
       return binding.getRoot();
    }

    private void getAllprouct() {
        Transaaction_history_adapter  transaaction_history_adapter = new Transaaction_history_adapter(getContext(),price);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL ,false);
        binding.recyclerTransation.setLayoutManager(mLayoutManager1);
        binding.recyclerTransation.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerTransation.setAdapter(transaaction_history_adapter);
    }
}