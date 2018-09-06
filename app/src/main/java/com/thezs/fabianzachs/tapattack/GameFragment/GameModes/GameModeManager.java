package com.thezs.fabianzachs.tapattack.GameFragment.GameModes;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.SoundEffectsManager;
import com.thezs.fabianzachs.tapattack.R;

import static android.content.Context.MODE_PRIVATE;

public class GameModeManager {

    private GameMode currentGameMode;
    private View view;

    public GameModeManager(View view) {
        this.view = view;
        currentGameMode = getSelectedGameMode(view);


    }

    public boolean warningUIVisible() {
        return currentGameMode.warningColorEnabled;
    }

    public void update() {
        currentGameMode.update();
    }

    public void receiveTouch(MotionEvent event) {
        if (touchIsAboveAdSection(event))
            currentGameMode.receiveTouch(event);
    }

    public void draw(Canvas canvas) {
        currentGameMode.draw(canvas);
    }

    private GameMode getSelectedGameMode(View view) {
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerInfo", MODE_PRIVATE);
        String gamemode = prefs.getString(Constants.GAME_MODE_TAG, Constants.GAMEMODES[0]);

        Mediator mediator = new Mediator();
        mediator.addObject(new SoundEffectsManager());

        switch (gamemode) {
            case "classic":
                return IntroLevels.buildIntroLevel(view, mediator,21);
                //return Stage1Levels.buildStage1Level(view,mediator,25);
                //return new Endless(view, mediator);
               //return new Story2(view, mediator);

        }

        Toast toast = Toast.makeText(Constants.CURRENT_CONTEXT, "Unknown Selected Gamemode", Toast.LENGTH_LONG);
        toast.show();
        ((Activity) Constants.CURRENT_CONTEXT).finish();
        return null;
    }

    private boolean touchIsAboveAdSection(MotionEvent event) {
        return event.getY() < view.findViewById(R.id.bottom_image).getBottom();
    }

}
