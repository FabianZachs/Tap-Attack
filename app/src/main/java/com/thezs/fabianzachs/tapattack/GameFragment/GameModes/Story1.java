package com.thezs.fabianzachs.tapattack.GameFragment.GameModes;

import android.view.View;

import com.thezs.fabianzachs.tapattack.GameFragment.Mediator;

public class Story1 extends IntroLevels {

    public Story1(View view, Mediator mediator) {
        super(view, mediator, 10, "circle", 3500, false);
    }


    @Override
    void changeGameComponent() {
    }

    @Override
    void checkLevelPassed() {
        super.checkLevelPassed();
    }

}
