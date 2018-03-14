package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 14/03/18.
 */

public class VibrantTheme extends SimpleTheme {

    public static Integer c1 = 0xff2D7DD2;
    public static Integer c2 = 0xff44355B;
    public static Integer c3 = 0xffEEB902;
    public static Integer c4 = 0xffF45D01;
    public static Integer c5 = 0xffDB2B39;

    public VibrantTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();

        addToColorsMap("blue",c1);
        addToColorsMap("purple",c2);
        addToColorsMap("yellow",c3);
        addToColorsMap("orange",c4);
        addToColorsMap("red",c5);
    }
}
