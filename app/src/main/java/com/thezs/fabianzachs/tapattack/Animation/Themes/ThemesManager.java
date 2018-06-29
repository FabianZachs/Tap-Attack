package com.thezs.fabianzachs.tapattack.Animation.Themes;

import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.FlatTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.FourthOfJulyTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.GirlyTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.GoogleTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.MutedTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.NeonTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.OldFashionTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.PrimaryTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.RageTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.RussiaWCTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.SummerTheme;
import com.thezs.fabianzachs.tapattack.Animation.Themes.SimpleThemes.VibrantTheme;

/**
 * Created by fabianzachs on 15/03/18.
 */

public class ThemesManager {

    private ThemeObject currentTheme;

    public ThemesManager() {

    }

    public void setCurrentTheme(ThemeObject theme) {
        this.currentTheme = theme;
    }

    public ThemeObject getCurrentTheme() {
        return this.currentTheme;
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
            case "girly":
                return new GirlyTheme(shapeType);
            case "oldfashion":
                return new OldFashionTheme(shapeType);
            case "fourthofjuly":
                return new FourthOfJulyTheme(shapeType);
            case "google":
                return new GoogleTheme(shapeType);
            case "russia wc":
                return new RussiaWCTheme(shapeType);
            case "muted":
                return new MutedTheme(shapeType);
            case "primary":
                return new PrimaryTheme(shapeType);
            case "summer":
                return new SummerTheme(shapeType);
            case "rage":
                return new RageTheme(shapeType);
        }
        return null;
    }

    public static String[] getStrColors(String theme) {

        switch (theme) {
            case "neon":
                return NeonTheme.strColors;
            case "vibrant":
                return VibrantTheme.strColors;
            case "flat":
                return FlatTheme.strColors;
            case "girly":
                return GirlyTheme.strColors;
            case "oldfashion":
                return OldFashionTheme.strColors;
            case "fourthofjuly":
                return FourthOfJulyTheme.strColors;
            case "google":
                return GoogleTheme.strColors;
            case "russia wc":
                return RussiaWCTheme.strColors;
            case "muted":
                return MutedTheme.strColors;
            case "primary":
                return PrimaryTheme.strColors;
            case "summer":
                return SummerTheme.strColors;
            case "rage":
                return RageTheme.strColors;

        }
        return null;
    }

    public static Integer[] getIntColors(String theme) {

        switch (theme) {
            case "neon":
                return NeonTheme.intColors;
            case "vibrant":
                return VibrantTheme.intColors;
            case "flat":
                return FlatTheme.intColors;
            case "girly":
                return GirlyTheme.intColors;
            case "oldfashion":
                return OldFashionTheme.intColors;
            case "fourthofjuly":
                return FourthOfJulyTheme.intColors;
            case "google":
                return GoogleTheme.intColors;
            case "russia wc":
                return RussiaWCTheme.intColors;
            case "muted":
                return MutedTheme.intColors;
            case "primary":
                return PrimaryTheme.intColors;
            case "summer":
                return SummerTheme.intColors;
            case "rage":
                return RageTheme.intColors;

        }
        return null;
    }
}
