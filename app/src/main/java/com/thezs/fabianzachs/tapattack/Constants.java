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
    //public static String[] NEONCOLORS = {"yellow", "red", "green", "blue", "pink", "purple"};
    //public static String[] SHAPES = {"circle", "square", "star", "cross", "triangle",
    //                                    "arrow"};

    public static String[] SHAPES = {"circle", "square", "cross", "arrow", "star"};
    public static String[] NEONCOLORS = {"blue","yellow","red","purple","green"};
    public static Map<String, String[]> COLORS;


    public static Map<String, int[]> progressBarHolderAndWarningHolderColors;
    public static int[] holderBlue = {0xff0040ff, 0xff00bcfe};
    public static int[] holderGreen = {0xff6aed6a, 0xff37d537};

    public static int[] holderYellow = {0xff0040ff, 0xff00bcfe};
    public static int[] holderRed = {0xff6aed6a, 0xff37d537};
    public static int[] holderPurple = {0xff6aed6a, 0xff37d537};

    public static Rect CLICK_AREA;
    public static Rect SHAPE_CREATION_AREA;

    public static BootstrapProgressBar progressBar;

    public static LayerDrawable warningComponent;
    public static ImageView progressBarHolder;
    public static Activity GAME_ACTIVITY;
}
