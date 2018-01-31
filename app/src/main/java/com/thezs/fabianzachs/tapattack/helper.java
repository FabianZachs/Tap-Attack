package com.thezs.fabianzachs.tapattack;

import android.app.Activity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by fabianzachs on 29/01/18.
 */

public class helper {


    public static void makeFullscreen(Activity activity) {
        // task bar
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        View decorView = activity.getWindow().getDecorView();
        // Hide both the navigation bar and the status bar.
        // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
        // a general rule, you should design your app to hide the status bar whenever you
        // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public static void bannerAdSetup(Activity activity, AdView AdView) {
        // ads (below setContentView)
        MobileAds.initialize(activity, "ca-app-pub-3940256099942544~3347511713");
        AdView = (AdView) activity.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        AdView.loadAd(adRequest);
    }
}
