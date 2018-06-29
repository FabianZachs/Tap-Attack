package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 29/06/18.
 */
// http://www.color-hex.com/color-palette/62784

public class OldFashionTheme extends SimpleTheme {
    public static Integer[] intColors = {0xfff9c3d3,0xffdb9c24,0xffd42f2f,0xff008b7e,0xff092366};
    public static String[] strColors = {"pink","yellow","red","blue","darkblue"};

    public OldFashionTheme(String shapeType) {
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
