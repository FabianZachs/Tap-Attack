package com.thezs.fabianzachs.tapattack;

import android.os.Bundle;
import android.view.View;

import com.thezs.fabianzachs.tapattack.MainMenu.GeneralParent;

public class LoadingActivityOLD extends GeneralParent {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_fragment);
    }

    public void goback(View view) {
        finish();
    }
}
