package com.thezs.fabianzachs.tapattack.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.GameModeScenes.ClassicGameScene;

import java.util.ArrayList;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class SceneManager {

    private ArrayList<Scene> scenes = new ArrayList<>();
    private static int ACTIVE_SCENE;
    private static Scene CURRENT_SCENE;

    // TODO update to take game mode x (ACTIVE_SCENE is specified before)
    public SceneManager() {
        ACTIVE_SCENE = 0;
        scenes.add(new ClassicGameScene());
        CURRENT_SCENE = scenes.get(ACTIVE_SCENE);
    }

    public void recieveTouch(MotionEvent event) {
        new StyleableToast
                .Builder(Constants.CURRENT_CONTEXT)
                .text(String.valueOf(scenes.get(ACTIVE_SCENE).getGameOver()))
                .textColor(Color.WHITE)
                .backgroundColor(Color.BLUE)
                .show();
        if (Constants.GAMEBOUNDARY.contains((int) event.getX(), (int) event.getY()))
            scenes.get(ACTIVE_SCENE).recieveTouch(event);
    }

    public void update() {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }

    public void setActiveScene(int sceneNumber) {
        ACTIVE_SCENE = sceneNumber;
    }

    public static void setGameOver(Boolean gameOver) {
        CURRENT_SCENE.setGameOver(true);
    }

}
