package com.thezs.fabianzachs.tapattack.GameFragment.GameOver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public /*abstract*/ class GameOverFragment extends Fragment {

    // todo now using gameOverType later use strategy
    public static GameOverFragment newInstance(String gameOverType, String reason, int testStuff) {
        GameOverFragment gameOverFragment = new GameOverFragment();
        Bundle args = new Bundle();
        args.putString("gameOverType", gameOverType);
        args.putInt("testStuff", testStuff);
        gameOverFragment.setArguments(args);
        return gameOverFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
