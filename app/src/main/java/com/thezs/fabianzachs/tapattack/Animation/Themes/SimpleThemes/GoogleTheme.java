package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 29/06/18.
 */
// http://www.color-hex.com/color-palette/1872

public class GoogleTheme extends SimpleTheme {
    public static Integer[] intColors = {0xff008744,0xff0057e7,0xffd62d20,0xffffa700,0xffffffff};
    public static String[] strColors = {"green","blue","red","orange","white"};

    public GoogleTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();

        addToColorsMap(strColors, intColors);
    }
}
