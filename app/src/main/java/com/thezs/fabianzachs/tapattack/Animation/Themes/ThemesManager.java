package com.thezs.fabianzachs.tapattack.Animation.Themes;

import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.FlatTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.NeonTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.VibrantTheme;

/**
 * Created by fabianzachs on 15/03/18.
 */

public class ThemesManager {

    public ThemesManager() {

    }

    // TODO put a theme builder here and store it for access
    public ThemeObject buildTheme(String themeName, String shapeType) {
        switch (themeName) {
            case "neon":
                return new NeonTheme(shapeType);
            case "flat":
                return new FlatTheme(shapeType);
            case "vibrant":
                return new VibrantTheme(shapeType);
        }
        return null;
    }

    public static String[] getStrColors(String theme) {

        switch (theme) {
            case "neon":
                return NeonTheme.strColors;
            case "vibrant":
                return VibrantTheme.strColors;

        }
        return null;
    }
}
