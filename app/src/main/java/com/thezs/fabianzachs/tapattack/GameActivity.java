package com.thezs.fabianzachs.tapattack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends GeneralParent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper.makeFullscreen(this);
        setContentView(R.layout.activity_game);
    }

    // code to update progress bar
    // code to update streak & score
    // code for pause button
    // code for entire background
    // code for warning color

}
