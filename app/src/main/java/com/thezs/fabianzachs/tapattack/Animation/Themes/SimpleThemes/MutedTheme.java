package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 29/06/18.
 */
//http://www.color-hex.com/color-palette/61896

public class MutedTheme extends SimpleTheme {
    public static Integer[] intColors = {0xff27567b	,0xffb63b3b,0xfff9c414,0xfff7ebd8,0xff505050};
    public static String[] strColors = {"blue","red","yellow","pale","grey"};

    public MutedTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();

        addToColorsMap(strColors, intColors);
    }
}
