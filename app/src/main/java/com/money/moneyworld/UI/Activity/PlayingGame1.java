package com.money.moneyworld.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.money.moneyworld.Adapter.GameAdapter;
import com.money.moneyworld.Model.GameModel;
import com.money.moneyworld.R;
import com.money.moneyworld.databinding.ActivityPlayingGame1Binding;
import com.money.moneyworld.utils.AppUtils;

import java.util.ArrayList;

public class PlayingGame1 extends AppCompatActivity {
ActivityPlayingGame1Binding activityPlayingGame1Binding;
ArrayList<GameModel>gameModels;
GameAdapter gameAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPlayingGame1Binding = DataBindingUtil.setContentView(this,R.layout.activity_playing_game1);

        AppUtils.setUpToolbar(this,activityPlayingGame1Binding.toolbar,true,true,"Play 01-10 Game");
        activityPlayingGame1Binding.toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        gameModels = new ArrayList<GameModel>();

        for (int i= 1; i<=10;i++ ){
            GameModel gameModel = new GameModel();
            gameModel.setNumber(i);
            gameModels.add(gameModel);
        }
        gameAdapter =new GameAdapter(getApplicationContext(),gameModels);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        activityPlayingGame1Binding.recyclerView.setHasFixedSize(true);
        activityPlayingGame1Binding.recyclerView.setLayoutManager(mLayoutManager);
        activityPlayingGame1Binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        activityPlayingGame1Binding.recyclerView.setAdapter(gameAdapter);
        gameAdapter.notifyDataSetChanged();

        activityPlayingGame1Binding.submitexpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
}