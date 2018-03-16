package com.thezs.fabianzachs.tapattack;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.ImageView;

import com.beardedhen.androidbootstrap.BootstrapProgressBar;
import com.thezs.fabianzachs.tapattack.Game.Scene;

import java.util.Map;

/**
 * Created by fabianzachs on 06/02/18.
 */

public class Constants {
    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public static int SHAPE_HEIGHT;
    public static int SHAPE_WIDTH;
    public static Context CURRENT_CONTEXT;

    public static String[] SHAPES = {"circle", "square", "cross", "arrow", "star"};

    public static Map<String, String[]> COLORS; // theme -> [] of colors
    public static String[] NEONCOLORS = {"blue","yellow","red","purple","green"};
    public static String CURRENT_THEME;
    public static String CURRENT_BACKGROUND = "backgroundtriangleblue";



    public static int[] neonColorsInt = {0xff0040ff,0xff6aed6a,0xffffff00,0xffff0000,0xff9d00ff};
    // NEON
    public static Map<String, int[]> progressBarHolderAndWarningHolderColors;
    public static int[] holderBlue = {0xff0040ff, 0xff00bcfe};
    public static int[] holderGreen = {0xff6aed6a, 0xff37d537};
    public static int[] holderYellow = {0xffffff00, 0xfff2ea02};
    public static int[] holderRed = {0xffff0000, 0xffff3300};
    public static int[] holderPurple = {0xff9d00ff, 0xffcc00ff};

    public static Rect CLICK_AREA;
    public static Rect SHAPE_CREATION_AREA;

    public static BootstrapProgressBar progressBar;

    public static LayerDrawable warningComponent;
    public static ImageView progressBarHolder;
    public static Activity GAME_ACTIVITY;
}
