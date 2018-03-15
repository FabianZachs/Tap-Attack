package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.Log;

import com.thezs.fabianzachs.tapattack.Animation.Themes.ThemeObject;
import com.thezs.fabianzachs.tapattack.Constants;

/**
 * Created by fabianzachs on 14/03/18.
 */

public abstract class SimpleTheme extends ThemeObject {

    public SimpleTheme() {
        super();
    }

    public Bitmap getShapeBitmap(String shape, String color, boolean click) {
        if (click)
            return getBitmapFromMap(getThemeTitle()+shape+"click");
        return getBitmapFromMap(getThemeTitle() + shape);
    }

    public Paint getShapePaint(Paint paint, String color) {
        Log.d("paint", "getShapePaint: color needed: " + color);
        ColorFilter filter = new PorterDuffColorFilter(getColorToInt(color), PorterDuff.Mode.SRC_IN);
        paint.setColorFilter(filter);

        return paint;
    }

    protected void addBitmapsToMap() {
        BitmapFactory bf = new BitmapFactory();

        for (String shape : Constants.SHAPES) {

            String mDrawableName = getThemeTitle() + shape;
            int resID = Constants.CURRENT_CONTEXT.getResources().getIdentifier(mDrawableName,"drawable", Constants.CURRENT_CONTEXT.getPackageName());
            Bitmap img = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), resID);
            addToBitmapsMap(mDrawableName,img);

            if (shape.equals("circle") || shape.equals("square") || shape.equals("arrow")) {
                mDrawableName =getThemeTitle() + shape + "click";
                resID = Constants.CURRENT_CONTEXT.getResources().getIdentifier(mDrawableName,"drawable", Constants.CURRENT_CONTEXT.getPackageName());
                img = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), resID);
                addToBitmapsMap(mDrawableName,img);
            }

        }
    }
}
