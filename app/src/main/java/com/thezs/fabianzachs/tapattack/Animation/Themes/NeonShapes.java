package com.thezs.fabianzachs.tapattack.Animation.Themes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import com.thezs.fabianzachs.tapattack.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by fabianzachs on 13/03/18.
 */

public class NeonShapes extends ThemeObject {

    // TODO we ask for a shape & color and get it --
    // TODO WHEN REUSING PAINT WE NEED TO RESET THE COLORFILTER!!-- or do we since for all shapes we change it again

    private Map<String, Bitmap> bitmapsMap;

    private final Map<String, Integer> colorsMap;

    public NeonShapes() {
        bitmapsMap = new HashMap<>();
        addBitmapsToMap();

        colorsMap = new HashMap<>();
        colorsMap.put("blue", 0xff00ffff);
        colorsMap.put("green", 0xff00ff00);
        colorsMap.put("yellow", 0xffffff00);
        colorsMap.put("red", 0xffff0000);
        colorsMap.put("purple", 0xff9d00ff);
    }

    private void addBitmapsToMap() {
        //String[] colors = colorsMap.keySet().toArray(new String[0]); //TODO dont need colors -- just each shape
        BitmapFactory bf = new BitmapFactory();

        for (String shape : Constants.SHAPES) {

            String mDrawableName = "curved" + shape;
            int resID = Constants.CURRENT_CONTEXT.getResources().getIdentifier(mDrawableName,"drawable", Constants.CURRENT_CONTEXT.getPackageName());
            Bitmap img = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), resID);
            bitmapsMap.put(mDrawableName, img);

            if (shape.equals("circle") || shape.equals("square")) {
                mDrawableName = "curved" + shape + "click";
                resID = Constants.CURRENT_CONTEXT.getResources().getIdentifier(mDrawableName,"drawable", Constants.CURRENT_CONTEXT.getPackageName());
                img = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), resID);
                bitmapsMap.put(mDrawableName, img);
            }

        }
    }

    // TODO for themes that don't require a specific color paint (when we have bitmaps for all images (more complex themes)),
        // TODO - we just return the paint
    public Paint getShapePaint(Paint paint, String color) {
        ColorFilter filter = new PorterDuffColorFilter(colorsMap.get(color), PorterDuff.Mode.SRC_IN);
        paint.setColorFilter(filter);

        return paint;
    }

    public Bitmap getShapeBitmap(String shape, String color, boolean click) {
        if (click)
            return bitmapsMap.get("curved"+shape+"click");
        return bitmapsMap.get("curved"+shape);
    }




    // TODO maybe return list ??
    public Set getColorsStr() {
        return this.colorsMap.keySet();
    }

    public int colorStrToInt(String color) {
        return colorsMap.get(color);
    }

    public String colorIntToStr(int colorInt) {

        String colorStr = "blue";
        for(String color : colorsMap.keySet()){
            if(colorsMap.get(color).equals(colorInt)) {
                colorStr = color;
            }
        }
        return colorStr;
    }
}
