package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 29/06/18.
 */
// http://www.color-hex.com/color-palette/3686

public class RageTheme extends SimpleTheme {
    public static Integer[] intColors = {0xffff0000,0xffbf0000,0xff800000,0xff400000,0xff000000};
    public static String[] strColors = {"redest","reder","red","dark","black"};

    public RageTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();

        addToColorsMap(strColors, intColors);
    }
}
