package com.thezs.fabianzachs.tapattack.Animation.Themes;

import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.NeonTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.VibrantTheme;

/**
 * Created by fabianzachs on 15/03/18.
 */

public class ThemesManager {

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
