package com.thezs.fabianzachs.tapattack.Game.GameObjects;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.Animation.AnimationManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundHandler;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Arrow;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Circle;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Cross;
import com.thezs.fabianzachs.tapattack.Game.GameObjects.Shapes.Square;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabianzachs on 25/02/18.
 */

public class ShapeBuilder {

    private AnimationManager animationManager;

    public ShapeBuilder(long initTime) {
        // decides durationAlive depending on initTime & current time
        initializeAnimations();
    }


    // one method to create each shape obj

    public Circle buildCircle(String color, Point centerLocation) {
        return new Circle(3, color, centerLocation,
                animationManager.getBitmap("circle", color, false),
                animationManager.getBitmap("circle", color, true));

    }

    public Square buildSquare(String color, Point centerLocation) {
        Square square = new Square(3, color, centerLocation,
                animationManager.getBitmap("square", color, false),
                animationManager.getBitmap("square", color, true));

        return square;
    }

    public Cross buildCross(String color, Point centerLocation) {
        return new Cross(3, color, centerLocation,
                animationManager.getBitmap("cross", color, false),
                animationManager.getBitmap("cross", color, true));
    }

    public Arrow buildArrow(String color, Point centerLocation) {
        Log.d("location", "buildArrow: NEW ARROW");
        return new Arrow(3, color, centerLocation,
                animationManager.getBitmap("arrow", color, false),
                animationManager.getBitmap("arrow", color, true), "UP");
    }


    private void initializeAnimations() {

        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerPrefs", MODE_PRIVATE);

        // change error: no theme to basic colorscheme
        String theme = prefs.getString("theme", "error: no theme");

        this.animationManager = new AnimationManager(theme);
    }
}
