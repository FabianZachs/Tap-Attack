package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 29/06/18.
 */
// http://www.color-hex.com/color-palette/62594

public class FourthOfJulyTheme extends SimpleTheme {
    public static Integer[] intColors = {0xff0d1867,0xffa70d0d	,0xffe5dece,0xff5b75f9,0xff543e3e};
    public static String[] strColors = {"darkblue","red","white","blue","brown"};

    public FourthOfJulyTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();

        addToColorsMap(strColors, intColors);
    }
}
