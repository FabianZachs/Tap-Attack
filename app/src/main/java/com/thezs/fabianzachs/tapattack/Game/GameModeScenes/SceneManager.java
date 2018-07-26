package com.thezs.fabianzachs.tapattack.Game.GameModeScenes;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.widget.Toast;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.Mediator.CentralGameCommunication;
import com.thezs.fabianzachs.tapattack.Game.Scene;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabianzachs on 07/02/18.
 */

public class SceneManager {

    private ArrayList<Scene> scenes = new ArrayList<>();
    private static int ACTIVE_SCENE;
    //private static Scene CURRENT_SCENE;

    // TODO update to take game mode x (ACTIVE_SCENE is specified before)
    public SceneManager(CentralGameCommunication mediator) {
        setCurrentGameConstants();
        ACTIVE_SCENE = 0;
        //scenes.add(new ClassicGameScene(mediator));
        scenes.add(getSelectedGameMode(mediator));

        //CURRENT_SCENE = scenes.get(ACTIVE_SCENE);
    }

    public Scene getSelectedGameMode(CentralGameCommunication mediator) {
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerInfo", MODE_PRIVATE);
        String gamemode = prefs.getString("gamemode", Constants.GAMEMODES[0]);

        switch (gamemode) {
            case "tutorial":
                return new InstructionsGameScene(mediator);
            case "classic":
                return new ClassicGameScene(mediator);

        }

        Toast toast = Toast.makeText(Constants.CURRENT_CONTEXT, "Unknown Selected Gamemode", Toast.LENGTH_LONG);
        toast.show();
        ((Activity) Constants.CURRENT_CONTEXT).finish();
        return null;
    }

    public void recieveTouch(MotionEvent event) {
        if (Constants.SHAPE_CLICK_AREA.contains((int) event.getX(), (int) event.getY()) || Constants.WARNING_COLOR_CLICK_AREA_LEFT.contains((int) event.getX(), (int) event.getY()) || Constants.WARNING_COLOR_CLICK_AREA_RIGHT.contains((int) event.getX(), (int) event.getY()))
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

    /*
    public static void setGameOver(Boolean gameOver) {
        CURRENT_SCENE.setGameOver(true);
    }
    */

    public void setCurrentGameConstants() {
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerInfo", MODE_PRIVATE);

        Constants.CURRENT_THEME = prefs.getString("shapeTheme", Constants.SHAPE_THEMES[1]);
        Constants.CURRENT_SHAPE_TYPE = prefs.getString("shapeType", Constants.SHAPE_TYPES[1]); // or straight
        Constants.CURRENT_BACKGROUND = prefs.getString("background", Constants.BACKGROUNDS[1]);
        //Constants.CURRENT_BACKGROUND = prefs.getString("background", "triangle-blue");
    }
}
