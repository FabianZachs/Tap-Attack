package com.thezs.fabianzachs.tapattack;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.ImageView;

import com.beardedhen.androidbootstrap.BootstrapProgressBar;
import com.thezs.fabianzachs.tapattack.Game.Scene;

import java.util.Map;

/**
 * Created by fabianzachs on 06/02/18.
 */


/* CREDITS:
COOLORS and COLOR-HEX for amazing color palettes inspirations
LUNAPIC for great background filters
LOGOS BY NICK on youtube for inkscape tutorials
 */



public class Constants {
    public static int SCREEN_HEIGHT;
    public static int SCREEN_WIDTH;
    public static int SHAPE_HEIGHT;
    public static int SHAPE_WIDTH;
    public static Context CURRENT_CONTEXT;

    public final static int VIDEO_AD_REWARD = 200;
    public final static int NUMBER_OF_INITIALY_UNLOCKED_ITEMS = 9; // todo may change
    public final static int[] RANDOM_PRICE_BY_SECTION = {0,2000,1000,2000};
    public final static int[] PURCHASE_PRICE_BY_SECTION = {0,4000,2000,4000};
    public final static String GAME_MODE_TAG = "game mode";
    public final static String SHAPE_TYPE_TAG = "shape type";
    public final static String SHAPE_THEME_TAG = "shape theme";
    public final static String BACKGROUND_TAG = "background";
    public final static String SETTINGS_TAG = "settings";
    public final static String MAIN_TAG = "main";
    public final static String STORE_TAG = "store";
    public final static String PAID_UNLOCKS_TAG = "paid unlock";
    public final static String PAID_POINTS_TAG = "paid points";
    public final static String PAID_SHIELDS_TAG = "paid shields";

    public static String[] SHAPES = {"circle", "square", "cross", "arrowup", "arrowleft", "arrowright", "star"};

    /*
    public static final String[] WARNING_COLOR_STREAK_REWARDS = {"unlock item","shift up", "reduce speed", "stars", "more points"};
    public static Integer[] WARNING_COLOR_STREAK_REWARDS_IDS = {R.drawable.unlockitem2, R.drawable.shiftshapesupicon, R.drawable.reducespeedicon, R.drawable.turnintostarsicon, R.drawable.morepointsicon};
    public static String[] WARNING_COLOR_STREAK_REWARDS_FILES = {"unlockitem2", "shiftshapesupicon", "reducespeedicon", "turnintostarsicon", "morepointsicon"};
    public static int[] WARNING_COLOR_STREAK_REWARDS_IDS_PRICE_POINTS = {5000, 5000, 5000, 5000, 5000};
    public static int[] WARNING_COLOR_STREAK_REWARDS_IDS_PRICE_MONEY = {0, 1, 1, 1, 1};


    public static String[] MULTIPLIERS = {"basic", "decent", "heating up","burning","fire!","insane!!"};
    public static Integer[] MULTIPLIER_IDS = {R.drawable.firstmultiplier, R.drawable.secondmultiplier,
            R.drawable.thirdmultiplier, R.drawable.fourthmultiplier, R.drawable.fifthmultiplier, R.drawable.sixthmultiplier};
    public static String[] MULTIPLIER_FILES = {"firstmultiplier", "secondmultiplier",
            "thirdmultiplier", "fourthmultiplier", "fifthmultiplier", "sixthmultiplier"};
    public static int[] MULTIPLIERS_PRICE_POINTS = {-1, -1, -1, -1, -1, -1};
    public static int[] MULTIPLIERS_PRICE_MONEY = {0, 1, 2, 3, 4, 5};
*/

    public static String[] GAMEMODES = {"tutorial", "classic", "circle", "square", "arrow", "discrete"};
    public static Integer[] GAMEMODES_IDS = {R.drawable.instructionsgamemode,R.drawable.startbluebluered, R.drawable.circlestart, R.drawable.squarestart, R.drawable.arrowstart, R.drawable.discretestart};
    public static String[] GAMEMODES_FILES = {"instructionsgamemode", "startbluebluered", "circlestart", "squarestart", "arrowstart", "discretestart"};
    public static int[] GAMEMODES_PRICE_POINTS = {0, 0, 0, 0, 0, 0};
    public static int[] GAMEMODES_PRICE_MONEY = {0, 0, 0, 0, 0, 0};

    public static String[] SHAPE_TYPES = {"straight","curved","paint","bit","drip","tornpaper"};
    public static Integer[] SHAPE_TYPES_IDS = {R.drawable.straightoutline, R.drawable.curvedoutline, R.drawable.paintoutline, R.drawable.bitoutline, R.drawable.dripoutline, R.drawable.tornpaperoutline};
    public static String[] SHAPE_TYPES_FILES = {"straightoutline", "curvedoutline", "paintoutline", "bitoutline", "dripoutline", "tornpaperoutline"};
    public static int[] SHAPE_TYPES_PRICE_POINTS = {10000, 10000, 10000, 10000, 10000, 10000};
    public static int[] SHAPE_TYPES_PRICE_MONEY = {1, 1, 1, 1,1, 1};
    public static int SHAPE_TYPES_POINTS_COST = 2000;

    public static String[] SHAPE_THEMES = {"neon","flat","vibrant","girly","oldfashion","fourthofjuly","google","russia wc","muted","primary","summer","rage"};
    public static String[] SHAPE_THEMES_FILES = {"neonthemetemplate", "flatthemetemplate",
            "vibrantthemetemplate", "girlythemetemplate", "oldfashionthemetemplate",
            "fourthofjulythemetemplate", "googlethemetempalte", "russiawcthemetemplate",
            "mutedthemetemplate", "primarythemetemplate", "summerthemetemplate",
            "ragethemetemplate"};
    public static Integer[] SHAPE_THEMES_ID = {R.drawable.neonthemetemplate, R.drawable.flatthemetemplate,
            R.drawable.vibrantthemetemplate, R.drawable.girlythemetemplate, R.drawable.oldfashionthemetemplate,
            R.drawable.fourthofjulythemetemplate, R.drawable.googlethemetempalte, R.drawable.russiawcthemetemplate,
            R.drawable.mutedthemetemplate, R.drawable.primarythemetemplate,R.drawable.summerthemetemplate,
            R.drawable.ragethemetemplate};
    public static int[] SHAPE_THEMES_PRICE_POINTS = {2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000, 2000};
    public static int[] SHAPE_THEMES_PRICE_MONEY = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    public static int SHAPE_THEME_POINTS_COST = 1000;

    //public static String[] BACKGROUNDS  = {"backgroundtriangleblue"};
    public static String[] BACKGROUNDS  = {"tri-blue", "tri-point", "tri-old", "tri-gold", "tri-edge", "tri-red", "tri-green", "tri-art1", "tri-art2","tri-sky","sky"};
    public static String[] BACKGROUNDS_FILES = {"backgroundtriangleblue", "backgroundtrianglepointy", "backgroundtriangleblackandwhite",
            "backgroundtrianglecaramel", "backgroundtriangleedge", "backgroundtrianglered", "backgroundtrianglegreen", "backgroundtriangleart1",
            "backgroundtriangleart2", "backgroundtrianglesky", "skybackground"};
    public static Integer[] BACKGROUNDS_ID = {R.drawable.backgroundtriangleblue, R.drawable.backgroundtrianglepointy, R.drawable.backgroundtriangleblackandwhite,
            R.drawable.backgroundtrianglecaramel, R.drawable.backgroundtriangleedge, R.drawable.backgroundtrianglered, R.drawable.backgroundtrianglegreen, R.drawable.backgroundtriangleart1,
            R.drawable.backgroundtriangleart2, R.drawable.backgroundtrianglesky, R.drawable.skybackground};
    public static int[] BACKGROUNDS_PRICE_POINTS = {5000, 5000, 5000, 5000,5000, 5000,5000, 5000,5000, 5000,5000};
    public static int[] BACKGROUNDS_PRICE_MONEY = {1, 1,  1, 1, 1, 1, 1, 1, 1, 1, 1};
    public static Integer[] BACKGROUND_WARNINGCOLOR_1 = {0xff0040ff, 0xffffffff, 0xffffffff,0xffffffff,0xffffffff,0xffffffff,0xffffffff,0xffffffff,0xffffffff,0xffffffff,0xffffffff};
    public static Integer[] BACKGROUND_WARNINGCOLOR_2 =  {0xff00bcfe, 0xffffffff, 0xffffffff,0xffffffff,0xffffffff,0xffffffff,0xffffffff,0xffffffff,0xffffffff,0xffffffff,0xffffffff};
    public static int BACKGROUND_POINTS_COST = 2000;

    public static Map<String, String[]> COLORS; // theme -> [] of colors
    public static String[] NEONCOLORS = {"blue","yellow","red","purple","green"};
    public static String CURRENT_THEME;
    public static String CURRENT_SHAPE_TYPE;
    // todo check below
    public static String CURRENT_BACKGROUND = "backgroundtriangleblue";



    public static int[] neonColorsInt = {0xff0040ff,0xff6aed6a,0xffffff00,0xffff0000,0xff9d00ff};
    // NEON
    public static Map<String, int[]> progressBarHolderAndWarningHolderColors;
    public static int[] holderBlue = {0xff0040ff, 0xff00bcfe};
    public static int[] holderGreen = {0xff6aed6a, 0xff37d537};
    public static int[] holderYellow = {0xffffff00, 0xfff2ea02};
    public static int[] holderRed = {0xffff0000, 0xffff3300};
    public static int[] holderPurple = {0xff9d00ff, 0xffcc00ff};

    public static Rect WARNING_COLOR_CLICK_AREA_RIGHT;
    public static Rect WARNING_COLOR_CLICK_AREA_LEFT;
    public static Rect SHAPE_CLICK_AREA;
    public static Rect SHAPE_CREATION_AREA;

    public static BootstrapProgressBar progressBar;

    public static LayerDrawable warningComponent;
    public static ImageView progressBarHolder;
    public static Activity GAME_ACTIVITY;
    public static ImageView warningComponentImg;
    public static Drawable /*LayerDrawable*/ warningComponentButtonRight;
    public static Drawable/*LayerDrawable*/ warningComponentButtonLeft;


}
