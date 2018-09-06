package com.thezs.fabianzachs.tapattack.GameFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import com.thezs.fabianzachs.tapattack.Constants;

import java.util.Random;

public class ThemeManager {

    private Integer[] colors;

    public ThemeManager() {
        SharedPreferences prefs = Constants.CURRENT_CONTEXT.getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
        setupCurrentTheme(prefs.getString(Constants.SHAPE_THEME_TAG,Constants.SHAPE_THEMES[0]));
        colors = RandomizeArray(colors);
    }

    public Integer[] getColors() {
        return colors;
    }

    public Paint getShapePaint(Paint paint, Integer color) {
        ColorFilter filter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN);
        paint.setColorFilter(filter);
        return paint;
    }

    private void setupCurrentTheme(String themeName) {

        switch (themeName) {
            case "neon":
                colors = new Integer[] {0xff00ffff,0xff00ff00,0xffffff00,0xffff0000,0xffcc00ff};
                break;
            case "flat":
                colors = new Integer[] {0xff231123,0xff82204A,0xff558C8C,0xffE8DB7D,0xffEFF7FF};
                break;
            case "vibrant":
                colors = new Integer[] {0xff2D7DD2,0xff44355B,0xffEEB902,0xffF47101,0xffDB2B39};
                break;
            case "girly":
                colors = new Integer[] {0xfff273b3,0xffb498ec,0xffffb9ad,0xffa34bae,0xffffffff};
                break;
            case "oldfashion":
                colors = new Integer[] {0xfff9c3d3,0xffdb9c24,0xffd42f2f,0xff008b7e,0xff092366};
                break;
            case "fourthofjuly":
                colors = new Integer[] {0xff0d1867,0xffa70d0d,0xffe5dece,0xff5b75f9,0xff543e3e};
                break;
            case "google":
                colors = new Integer[] {0xff008744,0xff0057e7,0xffd62d20,0xffffa700,0xffffffff};
                break;
            case "russia wc":
                colors = new Integer[] {0xffd30208,0xffe5c685,0xff171714,0xff015386,0xff0074b1};
                break;
            case "muted":
                colors = new Integer[] {0xff27567b,0xffb63b3b,0xfff9c414,0xfff7ebd8,0xff505050};
                break;
            case "primary":
                colors = new Integer[] {0xff192fd0,0xff3b974c,0xffffe820,0xffde0134,0xff2a2a2a};
                break;
            case "summer":
                colors = new Integer[] {0xfff40234,0xfff0532c,0xfff0882f,0xff688b2e,0xff013f73};
                break;
            case "rage":
                colors = new Integer[] {0xffff0000,0xffbf0000,0xff800000,0xff400000,0xff000000};
                break;
        }

    }

    private static Integer[] RandomizeArray(Integer[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            Integer temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
        return array;
    }

}
