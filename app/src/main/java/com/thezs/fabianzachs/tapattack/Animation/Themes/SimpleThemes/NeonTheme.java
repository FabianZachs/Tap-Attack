package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 13/03/18.
 */

public class NeonTheme extends SimpleTheme {

    // TODO we ask for a shape & color and get it --
    // TODO WHEN REUSING PAINT WE NEED TO RESET THE COLORFILTER!!-- or do we since for all shapes we change it again
    public static Integer c1 = 0xff00ffff;
    public static Integer c2 = 0xff00ff00;
    public static Integer c3 = 0xffffff00;
    public static Integer c4 = 0xffff0000;
    public static Integer c5 = 0xffff0000;

    public NeonTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();

        addToColorsMap("blue",c1);
        addToColorsMap("green",c2);
        addToColorsMap("yellow",c3);
        addToColorsMap("red",c4);
        addToColorsMap("purple",c5);
    }








}
