package com.thezs.fabianzachs.tapattack;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
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
    public static String[] NEONCOLORS = {"blue","green","yellow"};
    public static Map<String, String[]> COLORS;

    public static Rect GAMEBOUNDARY;

    public static BootstrapProgressBar progressBar;
    public static ImageView warningColor;
    public static ImageView warningHolder;
    public static Activity GAME_ACTIVITY;
}
