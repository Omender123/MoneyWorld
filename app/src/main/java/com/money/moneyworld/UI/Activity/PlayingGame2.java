package com.money.moneyworld.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.money.moneyworld.Adapter.GameAdapter;
import com.money.moneyworld.Model.GameModel;
import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivityPlayingGame2Binding;
import com.money.moneyworld.utils.AppUtils;

import java.util.ArrayList;

public class PlayingGame2 extends AppCompatActivity {
ActivityPlayingGame2Binding activityShowBattingBinding;
ArrayList<GameModel>gameModels;
GameAdapter gameAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityShowBattingBinding = DataBindingUtil.setContentView(this,R.layout.activity_playing_game2);

        AppUtils.setUpToolbar(this,activityShowBattingBinding.toolbar,true,true,"Play 01-100 Game");
        activityShowBattingBinding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        gameModels = new ArrayList<GameModel>();

        for (int i= 1; i<=100;i++ ){
            GameModel gameModel = new GameModel();
            gameModel.setNumber(i);
            gameModels.add(gameModel);
        }
        gameAdapter =new GameAdapter(getApplicationContext(),gameModels);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),5);
        activityShowBattingBinding.recyclerView.setHasFixedSize(true);
        activityShowBattingBinding.recyclerView.setLayoutManager(mLayoutManager);
        activityShowBattingBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityShowBattingBinding.recyclerView.setAdapter(gameAdapter);
        gameAdapter.notifyDataSetChanged();

        activityShowBattingBinding.submitexpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    }
