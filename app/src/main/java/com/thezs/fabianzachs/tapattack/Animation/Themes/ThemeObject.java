package com.thezs.fabianzachs.tapattack.Animation.Themes;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by fabianzachs on 13/03/18.
 */

public abstract class ThemeObject {

    private Map<String, Bitmap> bitmapsMap;
    private Map<String, Integer> colorsMap;
    private String themeTitle;
    //public Integer[] intColors;
    //public String[] strColors;

    public ThemeObject() {
        this.bitmapsMap = new HashMap<>();
        this.colorsMap = new HashMap<>();
    }

    // TODO call these from super and use super to initialize all the code from theme subclasses
    // TODO think about how we want to store the data -- map & seperate [] for strColors and intColors or just a map
    /*
    public void setIntColors(Integer[] intColors) {
        this.intColors = intColors;
    }*/

    /*public Integer[] getIntColors() {
        return this.intColors;
    }*/

    public ArrayList<Integer> getIntColors() {
        ArrayList<Integer> intColors = new ArrayList<>();

        for (String strColor : colorsMap.keySet())
            intColors.add(colorsMap.get(strColor));

        return intColors;
    }

    public ArrayList<String> getStrColors() {
        ArrayList<String> list = new ArrayList<>();
        list.addAll(this.colorsMap.keySet());
        return list;
    }

    /*public void setStrColors(String[] strColors) {
        this.strColors = strColors;
    }*/

    /*public String[] getStrColors() {
        return this.strColors;
    }*/

    public Map<String, Integer> getColorsMap() {
        return this.colorsMap;
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
