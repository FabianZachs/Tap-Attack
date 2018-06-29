package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 29/06/18.
 */
// http://www.color-hex.com/color-palette/62241

public class RussiaWCTheme extends SimpleTheme {
    public static Integer[] intColors = {0xffd30208,0xffe5c685,0xff171714,0xff015386,0xff0074b1};
    public static String[] strColors = {"red","kacky","black","blue","darkblue"};

    public RussiaWCTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();

        addToColorsMap(strColors, intColors);
    }
}
