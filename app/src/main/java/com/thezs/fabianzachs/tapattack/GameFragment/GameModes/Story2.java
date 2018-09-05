package com.thezs.fabianzachs.tapattack.GameFragment.GameModes;

import android.view.View;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.GameFragment.ColorPicker;
import com.thezs.fabianzachs.tapattack.GameFragment.GameOverClasses.GameOver;
import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ContinuousShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapeMovers.ShapeMover;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapePicker;
import com.thezs.fabianzachs.tapattack.GameFragment.ShapesManager;
import com.thezs.fabianzachs.tapattack.GameFragment.ThemeManager;

public class Story2 extends IntroLevels {



    public Story2(View view, Mediator mediator) {
        super(view, mediator, 10, "square", 3500, false);
    }


    @Override
    void changeGameComponent() {
    }

    @Override
    void checkLevelPassed() {
        super.checkLevelPassed();
    }
}
