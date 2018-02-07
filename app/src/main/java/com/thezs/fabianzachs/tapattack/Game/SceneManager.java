package com.thezs.fabianzachs.tapattack.Game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class SceneManager {

    private ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;

    // TODO update to take game mode x (ACTIVE_SCENE is specified before)
    public SceneManager() {
        ACTIVE_SCENE = 0;
        scenes.add(new ClassicGameScene());
    }

    public void recieveTouch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).recieveTouch();
    }

    public void update() {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }
}
