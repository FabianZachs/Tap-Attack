package com.thezs.fabianzachs.tapattack.Game.GameObjects.ShapePickers;

import android.content.SharedPreferences;

import com.thezs.fabianzachs.tapattack.Constants;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabianzachs on 29/07/18.
 */

public abstract class ShapePicker {

    protected Random rand = new Random();


    public static ShapePicker getShapePicker() {

        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerInfo", MODE_PRIVATE);
        String gamemode = prefs.getString(Constants.GAME_MODE_TAG, Constants.GAMEMODES[1]);

        switch (gamemode) {
            case "classic":
                return new ClassicShapePicker();
            case "circle":
                return new SingleShapePicker("circle");
            case "square":
                return new SingleShapePicker("square");
            case "arrow":
                return new SingleShapePicker("arrow");
                /*
            case "discrete":
                return;
                /*
            case "simple":
                return;
                */
        }
        return null;
    }







    public ShapePicker() {
        // todo based on gamemode select the correct ShapePicker system
    }

    public abstract String getShape();

}
