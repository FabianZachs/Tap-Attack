package com.thezs.fabianzachs.tapattack.Animation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fabianzachs on 12/02/18.
 */

public class AnimationManager {

    private Map<String, Bitmap> animations;

    public AnimationManager(String theme) {
        animations = new HashMap<>();
        addBitmapsToMap(theme);
    }


    private void addBitmapsToMap(String theme) {
        BitmapFactory bf = new BitmapFactory();
        for (String shape : Constants.SHAPES) {
            for (String color : Constants.COLORS.get(theme)) {
                // for non-click img
                String mDrawableName = theme + shape + color;
                int resID = Constants.CURRENT_CONTEXT.getResources().getIdentifier(mDrawableName , "drawable", Constants.CURRENT_CONTEXT.getPackageName());
                Bitmap img = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), resID);
                animations.put(mDrawableName, img);

                // for click img
                mDrawableName = theme + shape + color + "click";
                resID = Constants.CURRENT_CONTEXT.getResources().getIdentifier(mDrawableName , "drawable", Constants.CURRENT_CONTEXT.getPackageName());
                img = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), resID);
                animations.put(mDrawableName, img);
            }
        }
    }

}
