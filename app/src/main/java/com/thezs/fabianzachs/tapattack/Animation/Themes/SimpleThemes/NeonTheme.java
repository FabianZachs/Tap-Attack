package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 13/03/18.
 */

public class NeonTheme extends SimpleTheme {

    // TODO we ask for a shape & color and get it --
    // TODO WHEN REUSING PAINT WE NEED TO RESET THE COLORFILTER!!-- or do we since for all shapes we change it again
    public static Integer[] intColors = {0xff00ffff,0xff00ff00,0xffffff00,0xffff0000,0xffcc00ff};
    public static String[] strColors = {"blue","green","yellow","red","purple"};


    public NeonTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();
        addToColorsMap(strColors, intColors);

        /*
        addToColorsMap(strColors[0],intColors[0]);
        addToColorsMap(strColors[1],intColors[1]);
        addToColorsMap(strColors[2],intColors[2]);
        addToColorsMap(strColors[3],intColors[3]);
        addToColorsMap(strColors[4],intColors[4]);
        */
    }








}
