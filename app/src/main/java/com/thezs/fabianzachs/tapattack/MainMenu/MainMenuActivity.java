package com.thezs.fabianzachs.tapattack.MainMenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.thezs.fabianzachs.tapattack.Animation.Themes.ThemesManager;
import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.Database.StoreItem;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundManager;
import com.thezs.fabianzachs.tapattack.MainMenu.Settings.SettingsFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.StoreFragment;
import com.thezs.fabianzachs.tapattack.R;
import com.thezs.fabianzachs.tapattack.Store1;
import com.thezs.fabianzachs.tapattack.helper;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenuActivity extends GeneralParent implements StoreFragment.StoreListener, SettingsFragment.SettingsListener, MainMenuFragment.MainMenuListener {

    private ArrayList<MediaPlayer> mediaPlayers; // these players loop -> turn of onStop()
    private ThemesManager themesManager;
    private BackgroundManager backgroundManager;
    private SharedPreferences prefs;
    private AdView mAdView;
    private Store1 store;
    private MorePointsMenu morePointsMenu;
    private InterstitialAd afterGameAd;
    private InterstitialAd timedMenuAd;

    /*
    private static final String TAG = "MainActivity";
    private SectionsPageAdapter sectionsPageAdapter;
    private CustomViewPager viewPager;
    */

    private MainMenuFragment mainMenuFragment;
    private StoreFragment storeFragment;
    private SettingsFragment settingsFragment;

    private MusicPlayer musicPlayer;


    @Override
    public void mainMenuFragmentToSettingsFragment() {
        displaySettingsFragment();
    }

    @Override
    public void mainMenuFragmentToStoreFragment() {
        displayStoreFragment();
    }

    @Override
    public void storeFragmentToMenuFragment() {
        displayMainMenuFragment();
    }

    @Override
    public void settingsFragmentToMenuFragment() {
        displayMainMenuFragment();

    }

    @Override
    public void musicOff() {
        musicPlayer.pausePlaying();
    }

    @Override
    public void musicOn() {
        musicPlayer.play();
    }

    @Override
    public void removeMusicPlayer() {
        musicPlayer.musicPlayerNotNeeded();
    }

    @Override
    public void fxOff() {

    }

    @Override
    public void fxOn() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        musicPlayer.pausePlaying();
    }

    public void displayMainMenuFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mainMenuFragment.isAdded()) {
            ft.show(mainMenuFragment);
        } else {
            Log.d("isadded", "displayMainMenuFragment: menu");
            ft.add(R.id.main_fragment, mainMenuFragment, "mainmenu");
        }
        if (storeFragment.isAdded()) { ft.hide(storeFragment); }
        if (settingsFragment.isAdded()) { ft.hide(settingsFragment); }
        ft.commit();
    }

    public void displayStoreFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (storeFragment.isAdded()) {
            storeFragment.onShow();
            ft.show(storeFragment);
        } else {
            Log.d("isadded", "displayMainMenuFragment: store");
            ft.add(R.id.main_fragment, storeFragment, "store");
        }
        if (mainMenuFragment.isAdded()) { ft.hide(mainMenuFragment); }
        if (settingsFragment.isAdded()) { ft.hide(settingsFragment); }
        ft.commit();
    }

    public void displaySettingsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (settingsFragment.isAdded()) {
            ft.show(settingsFragment);
        } else {
            Log.d("isadded", "displayMainMenuFragment: settings");
            ft.add(R.id.main_fragment, settingsFragment, "settings");
        }
        if (mainMenuFragment.isAdded()) { ft.hide(mainMenuFragment); }
        if (storeFragment.isAdded()) { ft.hide(storeFragment); }
        ft.commit();
    }



    private void addAllFragments() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        this.mainMenuFragment = new MainMenuFragment();
        this.storeFragment = new StoreFragment();
        this.settingsFragment = new SettingsFragment();
        //ft.add(R.id.main_fragment, mainMenuFragment, "mainmenu");
        ft.add(R.id.main_fragment, storeFragment, "store");
        ft.add(R.id.main_fragment, settingsFragment, "settings");
        ft.commit();
    }

    private void musicSetup() {
        this.musicPlayer = new MusicPlayer(this);
        if (prefs.getInt("music", 1) == 1)
            musicPlayer.play();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("playerInfo", MODE_PRIVATE);

        setContentView(R.layout.main_menu4);

        if (findViewById(R.id.main_fragment) != null) {

            if (savedInstanceState != null) {
                return;
            }

            addAllFragments();
            displayMainMenuFragment();
        }


        new MyItemDatabase(this, prefs);
        musicSetup();
        initializeConstants();
    }



    // todo basic code for altering specific pixels of bitmap
    public void multiShapesMessaroundDELETE() {

        /*
        ImageView item = (ImageView) findViewById(R.id.play_button);
        LayerDrawable layers = (LayerDrawable) item.getDrawable();
        Drawable shape1 = layers.getDrawable(0);
        Log.d("bounds", "multiShapesMessaroundDELETE: " + shape1.getBounds());
        shape1.setBounds(0,0,160,5000);
        Log.d("bounds", "multiShapesMessaroundDELETE: " + shape1.getBounds());
        ColorFilter filter = new PorterDuffColorFilter(0xff74AC23, PorterDuff.Mode.SRC_IN);
        shape1.setColorFilter(filter);
        */

        // EDIT BITMAP
        ImageView img = (ImageView) findViewById(R.id.play_button);
        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.neonthemetemplate);
        myBitmap= myBitmap.copy(Bitmap.Config.ARGB_8888, true);

        int [] allpixels = new int [ myBitmap.getHeight()*myBitmap.getWidth()];

        myBitmap.getPixels(allpixels, 0, myBitmap.getWidth(), 0, 0,myBitmap.getWidth(),myBitmap.getHeight());

        for(int i =0; i<myBitmap.getHeight()*myBitmap.getWidth();i++){

            if( allpixels[i] == 0xff00ffff/*|| allpixels[i] == Color.BLUE || allpixels[i] == Color.GREEN*/)
                allpixels[i] = Color.BLACK;
        }

        myBitmap.setPixels(allpixels, 0, myBitmap.getWidth(), 0, 0, myBitmap.getWidth(), myBitmap.getHeight());
        img.setImageBitmap(myBitmap);



        // SAVE IMG TO USER to internal storate -- only app can access this
        Log.d("filecrap", "multiShapesMessaroundDELETE: " + getFilesDir() );

    }

    private void startIntervalAd() {

        // todo commented out since kind of annoying
/*
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (timedMenuAd.isLoaded()) {
                    timedMenuAd.show();
                    requestNewTimedMenuAd();
                }
            }
        }, 1000);

*/

    }


    private void initializeConstants() {

        Constants.CURRENT_CONTEXT = this;

        Point screenDimension = screenResolution();
        // get screen dimensions stored
        Constants.SCREEN_WIDTH = screenDimension.x;
        // TODO bug: screen height from dm is incorrect for pixel
        Constants.SCREEN_HEIGHT = screenDimension.y;
        //Log.d("height", "initializeConstants: height: " + Constants.SCREEN_HEIGHT );

        Constants.SHAPE_WIDTH = Constants.SHAPE_HEIGHT = Constants.SCREEN_WIDTH/5 + Constants.SCREEN_WIDTH/25;


        // put colors for all theme packs
        Constants.COLORS = new HashMap<>();
        Constants.COLORS.put("neon", Constants.NEONCOLORS);

        // holder colors
        Constants.progressBarHolderAndWarningHolderColors = new HashMap<>();
        Constants.progressBarHolderAndWarningHolderColors.put("blue",Constants.holderBlue);
        Constants.progressBarHolderAndWarningHolderColors.put("green",Constants.holderGreen);
        Constants.progressBarHolderAndWarningHolderColors.put("yellow",Constants.holderYellow);
        Constants.progressBarHolderAndWarningHolderColors.put("purple",Constants.holderPurple);
        Constants.progressBarHolderAndWarningHolderColors.put("red",Constants.holderRed);
        // TODO do rest ...

        // top based on where warning color is
        //Log.d("screenwidth", "initializeConstants: width: " + Constants.SCREEN_WIDTH);
        //Constants.SHAPE_CLICK_AREA = new Rect(Constants.SCREEN_WIDTH/20, 30 + (Constants.SCREEN_HEIGHT/40 +25) + 20 + Constants.SCREEN_WIDTH/15 + 10 + Constants.SHAPE_HEIGHT/2, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/20, Constants.SCREEN_HEIGHT - 1);
        //Constants.SHAPE_CREATION_AREA = new Rect(Constants.SHAPE_CLICK_AREA.left + Constants.SHAPE_WIDTH/2,Constants.SHAPE_CLICK_AREA.top + Constants.SHAPE_HEIGHT/2,
        //                                        Constants.SHAPE_CLICK_AREA.right - Constants.SHAPE_WIDTH/2, Constants.SHAPE_CLICK_AREA.bottom - Constants.SHAPE_HEIGHT/2);
        Constants.SHAPE_CLICK_AREA = new Rect(Constants.SCREEN_WIDTH/20, 0, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/20, Constants.SCREEN_HEIGHT - 1);
        Constants.SHAPE_CREATION_AREA = new Rect(Constants.SCREEN_WIDTH/20 + Constants.SHAPE_WIDTH/2, -Constants.SHAPE_HEIGHT -50, Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/20 - Constants.SHAPE_WIDTH/2,0 );

        Constants.WARNING_COLOR_CLICK_AREA_LEFT = new Rect(0,30 + (Constants.SCREEN_HEIGHT/40 +25) + 20 + Constants.SCREEN_WIDTH/15 + 10 + Constants.SHAPE_HEIGHT/2,Constants.SCREEN_WIDTH/20, Constants.SCREEN_HEIGHT);
        Constants.WARNING_COLOR_CLICK_AREA_RIGHT = new Rect(Constants.SCREEN_WIDTH - Constants.SCREEN_WIDTH/20,30 + (Constants.SCREEN_HEIGHT/40 +25) + 20 + Constants.SCREEN_WIDTH/15 + 10 + Constants.SHAPE_HEIGHT/2,Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private Point screenResolution() {
        WindowManager windowManager =
                (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point screenResolution = new Point();

        if (Build.VERSION.SDK_INT < 14)
            throw new RuntimeException("Unsupported Android version.");
        display.getRealSize(screenResolution);

        return screenResolution;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (prefs.getInt("music", 1) == 1)
            musicPlayer.play();
    }

    @Override
    protected void onStop() {
        super.onStop();
        musicPlayer.pausePlaying();
    }


    private void requestNewAfterGameAd() {
        afterGameAd = new InterstitialAd(this);
        afterGameAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        AdRequest request = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        afterGameAd.loadAd(request);
    }

    private void requestNewTimedMenuAd() {
        timedMenuAd = new InterstitialAd(this);
        timedMenuAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        timedMenuAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                timedMenuAd.show();
            }
        });
        AdRequest request = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        timedMenuAd.loadAd(request);
    }
}
