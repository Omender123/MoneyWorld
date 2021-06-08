package com.money.moneyworld.Fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.money.moneyworld.Adapter.Pager_Adapter;
import com.money.moneyworld.R;
import com.money.moneyworld.databinding.FragmentResultBinding;

import static com.google.android.material.tabs.TabLayout.GRAVITY_FILL;


public class Result extends Fragment {
FragmentResultBinding binding;
View view;
    Pager_Adapter pager_adapter;
    public Result() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result,container,false);
        view = binding.getRoot();

        pager_adapter = new Pager_Adapter(getChildFragmentManager(),1);
        pager_adapter.addFragment(new Lastest_Result(),"Latest Result");
        pager_adapter.addFragment(new Our_Betting(),"Our Betting");
        binding.tablayout.setTabGravity(GRAVITY_FILL);

        binding.pager.setAdapter(pager_adapter);
        binding.tablayout.setupWithViewPager( binding.pager);
        binding.pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tablayout));
        binding.tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return binding.getRoot();}
}