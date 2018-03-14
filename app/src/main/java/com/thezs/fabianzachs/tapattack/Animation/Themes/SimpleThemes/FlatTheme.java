package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 14/03/18.
 */

public class FlatTheme extends SimpleTheme {

    public static Integer c1 = 0xff231123;
    public static Integer c2 = 0xff82204A;
    public static Integer c3 = 0xff558C8C;
    public static Integer c4 = 0xffE8DB7D;
    public static Integer c5 = 0xffEFF7FF;

    public FlatTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();

        addToColorsMap("purple",c1);
        addToColorsMap("red",c2);
        addToColorsMap("blue",c3);
        addToColorsMap("yellow",c4);
        addToColorsMap("white",c5);
    }
}
