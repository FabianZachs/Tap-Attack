package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 29/06/18.
 */
// http://www.color-hex.com/color-palette/62728

public class SummerTheme extends SimpleTheme {
    public static Integer[] intColors = {0xfff40234,0xfff0532c,0xfff0882f,0xff688b2e,0xff013f73};
    public static String[] strColors = {"red","orange","yellow","green","blue"};

    public SummerTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();

        addToColorsMap(strColors, intColors);
    }
}
