package com.thezs.fabianzachs.tapattack.GameFragment;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.GameFragment.GameModes.GameModeManager;
import com.thezs.fabianzachs.tapattack.R;

public class MainGameFragment extends Fragment {

    private GameAnimation gameAnimation;
    public GameUI gameUI;
    private boolean gameShowing = false;
    private View view;
    private GameModeManager gameModeManager;
    private GameFragmentListener listener;

    public interface GameFragmentListener{
        void closeGame();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_game, container, false);

        // todo remove later
        ImageView exitButton = view.findViewById(R.id.warning_component);
        exitButton.setOnTouchListener(new ButtonOnTouchListener(getActivity(), exitButton, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                gameShowing = false;
                gameModeManager = null;
                listener.closeGame();
            }
        }));


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onShow();
    }

    public void onShow() {
        gameModeManager = new GameModeManager(view);
        gameShowing = true;

        gameUI = new GameUI(getActivity(), view, gameModeManager.warningUIVisible());
        this.gameAnimation = new GameAnimation(view);
        gameAnimation.startGameAnimation();

        //todo set currentGameMode to null after game over since gamefragment is onShow and it wont reinitialize

    }

    public void onHide() {
        gameShowing = false;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainGameFragment.GameFragmentListener) {
            listener = (GameFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MainGameFragment.GameFragmentListener");
        }
    }

    public void draw(Canvas canvas) {
        if (gameShowing && gameModeManager != null) {
            gameModeManager.draw(canvas);
        }

    }

    public void onTouchEvent(MotionEvent event) {
        if (gameShowing && gameModeManager != null) {
            gameModeManager.receiveTouch(event);
        }
    }

    public void update() {
        if (gameShowing && gameModeManager != null) {
            gameModeManager.update();
        }

    }

}
