package com.thezs.fabianzachs.tapattack.Animation.Themes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by fabianzachs on 13/03/18.
 */

public abstract class ThemeObject {

    private Map<String, Bitmap> bitmapsMap;
    private Map<String, Integer> colorsMap;
    private String themeTitle;

    public ThemeObject() {
        this.bitmapsMap = new HashMap<>();
        this.colorsMap = new HashMap<>();
    }

    public abstract Bitmap getShapeBitmap(String shape, String color, boolean click);
    public abstract Paint getShapePaint(Paint paint, String color);

    // BITMAPS
    protected void addToBitmapsMap(String name, Bitmap bitmap) {
        this.bitmapsMap.put(name,bitmap);
    }
    protected Bitmap getBitmapFromMap(String string) {
        return this.bitmapsMap.get(string);
    }


    // Colors
    public String colorIntToStr(int colorInt) {
        String colorStr = "blue";
        for(String color : colorsMap.keySet()){
            if(colorsMap.get(color).equals(colorInt)) {
                colorStr = color;
            }
        }
        return colorStr;
    }

    public Set getColorsStr() {
        return this.colorsMap.keySet();
    }

    public Integer getColorToInt(String colorName) {
        return this.colorsMap.get(colorName);
    }

    protected void addToColorsMap(String colorName, Integer colorInt) {
        this.colorsMap.put(colorName,colorInt);
    }

    protected void addToColorsMap(String[] strColors, Integer[] intColors) {
        for (int i = 0; i < strColors.length; i++) {
            this.colorsMap.put(strColors[i], intColors[i]);
        }
    }




    // Theme
    protected void setThemeTitle(String theme) {
        this.themeTitle = theme;
    }

    protected String getThemeTitle() {
        return this.themeTitle;
    }




}
