package com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes;

/**
 * Created by fabianzachs on 14/03/18.
 */

public class VibrantTheme extends SimpleTheme {

    public static Integer[] intColors = {0xff2D7DD2,0xff44355B,0xffEEB902,0xffF45D01,0xffDB2B39};
    public static String[] strColors = {"blue","purple","yellow","orange","red"};

    public VibrantTheme(String shapeType) {
        super();
        setThemeTitle(shapeType);
        addBitmapsToMap();


        addToColorsMap(strColors[0],intColors[0]);
        addToColorsMap(strColors[1],intColors[1]);
        addToColorsMap(strColors[2],intColors[2]);
        addToColorsMap(strColors[3],intColors[3]);
        addToColorsMap(strColors[4],intColors[4]);
    }
}
