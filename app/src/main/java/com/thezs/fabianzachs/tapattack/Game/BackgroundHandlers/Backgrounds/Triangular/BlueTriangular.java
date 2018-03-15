package com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.Backgrounds.Triangular;

import android.graphics.Bitmap;
import android.provider.Contacts;

import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.Backgrounds.GameBackground;

/**
 * Created by fabianzachs on 15/03/18.
 */

public class BlueTriangular extends GameBackground {

    public static int[] UIHolderColors = {0xff0040ff, 0xff00bcfe};

    public BlueTriangular() {
        super("backgroundtriangleblue");
        setupHolders(UIHolderColors);
    }
}
