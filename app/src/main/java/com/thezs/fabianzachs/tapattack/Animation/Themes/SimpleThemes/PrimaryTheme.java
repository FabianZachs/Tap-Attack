package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 29/06/18.
 */
// http://www.color-hex.com/color-palette/16562

public class PrimaryTheme extends SimpleTheme {
    public static Integer[] intColors = {0xff192fd0,0xff3b974c,0xffffe820,0xffde0134,0xff2a2a2a};
    public static String[] strColors = {"blue","green","yellow","red","brown"};

    public PrimaryTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();

        addToColorsMap(strColors, intColors);
    }
}
