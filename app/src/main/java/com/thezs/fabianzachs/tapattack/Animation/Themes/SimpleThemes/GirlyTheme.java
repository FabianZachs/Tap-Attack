package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 29/06/18.
 */
// http://www.color-hex.com/color-palette/28852

public class GirlyTheme extends SimpleTheme {
    public static Integer[] intColors = {0xfff273b3,0xffb498ec,0xffffb9ad,0xffa34bae,0xffffffff};
    public static String[] strColors = {"pink","blue","kakky","purple","lightpurple"};

    public GirlyTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();

        addToColorsMap(strColors, intColors);

        /*
        addToColorsMap(strColors[0],intColors[0]);
        addToColorsMap(strColors[1],intColors[1]);
        addToColorsMap(strColors[2],intColors[2]);
        addToColorsMap(strColors[3],intColors[3]);
        addToColorsMap(strColors[4],intColors[4]);
        */

        /*
        addToColorsMap("purple",c1);
        addToColorsMap("red",c2);
        addToColorsMap("blue",c3);
        addToColorsMap("yellow",c4);
        addToColorsMap("white",c5);
        */
    }
}
