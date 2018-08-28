package com.thezs.fabianzachs.tapattack.GameFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.GameComponents.GameModes.GameModeManager;
import com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection.PaidUnlockAdapter;
import com.thezs.fabianzachs.tapattack.R;

public class MainGameFragment extends Fragment {

    private GameAnimation gameAnimation;
    public GameUI gameUI;
    private boolean gameShowing = false;
    private View view;
    private GameModeManager gameModeManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_game, container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gameModeManager = new GameModeManager(view);
        gameShowing = true;

        gameUI = new GameUI(getActivity(), view, gameModeManager.warningUIVisible());
        this.gameAnimation = new GameAnimation(view);
        gameAnimation.startGameAnimation();
    }

    public void onShow() {

    }

    public void onHide() {
        gameShowing = false;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void draw(Canvas canvas) {
        if (gameShowing && gameModeManager != null) {
            gameModeManager.draw(canvas);
        }

    }

    public void onTouchEvent(MotionEvent event) {
        if (gameShowing && gameModeManager != null) {
            gameModeManager.recieveTouch(event);
        }
    }

    public void update() {
        if (gameShowing && gameModeManager != null) {
            gameModeManager.update();
        }

    }

}
