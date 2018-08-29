package com.thezs.fabianzachs.tapattack.GameFragment.GameModes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.thezs.fabianzachs.tapattack.GameFragment.ShapeBitmapManager;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.NormalShapeBuilder;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeObjects.NormalShapes.ShapeObject;
import com.thezs.fabianzachs.tapattack.GameFragment.SoundEffectsManager;
import com.thezs.fabianzachs.tapattack.GameFragment.ThemeManager;
import com.thezs.fabianzachs.tapattack.GameFragment.WarningColorComponent;

public abstract class GameMode {

    protected boolean warningColorEnabled;


    private ShapeObject object;
    private SoundEffectsManager soundEffectsManager;

    public GameMode(View view, boolean warningColorEnabled) {
        this.warningColorEnabled = warningColorEnabled;

        ThemeManager themeManager = new ThemeManager();
        if (warningColorEnabled)
            new WarningColorComponent(view,themeManager.getColors());

        ShapeBitmapManager shapeBitmapManager = new ShapeBitmapManager();

        NormalShapeBuilder builder = new NormalShapeBuilder();
        object = builder.buildShape("cross", 0xffffffff, new Point(100,100), new Paint(), new Rect(0,0,0,0),100);

        this.soundEffectsManager = new SoundEffectsManager();
    }



    public void update() {

    }

    public void draw(Canvas canvas) {
        object.draw(canvas);
        Log.d("gamdraw", "draw: ");

    }

    public void recieveTouch(MotionEvent event) {
        Log.d("touchreca", "recieveTouch: ");
        soundEffectsManager.soundEffects.playArrowSwipe();

    }
}
