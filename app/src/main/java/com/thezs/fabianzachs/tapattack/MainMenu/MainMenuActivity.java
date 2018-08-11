package com.thezs.fabianzachs.tapattack.MainMenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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
import com.thezs.fabianzachs.tapattack.LoadingActivityOLD;
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
        // todo check if these fragments have already been added like above methods
        ft.add(R.id.main_fragment, mainMenuFragment, "mainmenu");
        ft.add(R.id.main_fragment, storeFragment, "store");
        ft.add(R.id.main_fragment, settingsFragment, "settings");
        ft.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_menu4);

        this.mainMenuFragment = new MainMenuFragment();
        this.storeFragment = new StoreFragment();
        this.settingsFragment = new SettingsFragment();


        if (findViewById(R.id.main_fragment) != null) {
            //addAllFragments(); //todo fix this so fragment layout loads before mainmenu opens

            if (savedInstanceState != null) {
                return;
            }

            displayMainMenuFragment();
        }

        // todo data base should be initiated here
        // todo music should be initiated here








        /*
        helper.makeFullscreen(this);


        // set up Constants
        initializeConstants();

        prefs = getSharedPreferences("playerInfo", MODE_PRIVATE);
        //if (!prefs.getBoolean("firstTime", false)) {
            Log.d("thisran", "onCreate: ran");
            databaseSetup();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.apply();
        //}

        // method instantiation
        //mediaPlayers = new ArrayList<MediaPlayer>();


        //SharedPreferences.Editor prefsEditor = prefs.edit();
        //prefsEditor.putString("background", Constants.BACKGROUNDS[1]);
        //prefsEditor.apply();
        //setContentView(R.layout.activity_main_menu);
        setContentView(R.layout.main_menu_fragment);


        setupGameModeImageAndTextAndHighscore();

        setupPointsDisplay();
        //multiShapesMessaroundDELETE(); //todo erase
        // programmatically set background
        //LinearLayout layout = (LinearLayout) findViewById(R.id.parent_layout);
        //layout.setBackground(ContextCompat.getDrawable(this, R.drawable.backgroundtriangleblue));

        Log.d("adcreation", "onCreate: ad created");
        helper.bannerAdSetup(this, mAdView);

        this.store = new Store1(this, prefs);
        store.getMainStoreDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                setupGameModeImageAndTextAndHighscore();
            }
        });

        this.morePointsMenu = new MorePointsMenu(this, prefs);

        // for images in store
        //gameTheme = themesManager.buildTheme("vibrant", "curved");
        //themesManager.setCurrentGameConstants(themesManager.buildTheme(Constants.CURRENT_THEME,Constants.CURRENT_SHAPE_TYPE));

        //this.backgroundManager = new BackgroundManager(prefs.getString("background","backgroundtriangleblue"));


        //initMusic(R.raw.mainmenu);

        /////////////// DATABASE ////////////////






        //MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        //dbHandler.deleteStoreItem("neon");
        //Log.d("database", "stuff "+dbHandler.databaseToString());

        startAnimatingMorePointsImg();


        //afterGameAd = new InterstitialAd(this);
        //afterGameAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        //AdRequest request = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        //afterGameAd.loadAd(request);

        requestNewAfterGameAd();
        requestNewTimedMenuAd();
        startIntervalAd();
        */
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

    private void startAnimatingMorePointsImg() {
        ImageView morePointsImg = (ImageView) findViewById(R.id.more_points_sign);
        YoYo.with(Techniques.Tada).duration(2000).repeat(100).playOn(morePointsImg); // todo this works for text animation if bestScore/bestStreak >= score do animaton
        // todo which of Bounce, Swing, Pulse, Flash, Tada
    }

    private void setupGameModeImageAndTextAndHighscore() {
        MyDBHandler myDBHandler = new MyDBHandler(Constants.CURRENT_CONTEXT, null, null, 1);

        ImageView gamemodeImg = (ImageView) findViewById(R.id.play_button);
        gamemodeImg.setImageResource(helper.getResourceId(Constants.CURRENT_CONTEXT, myDBHandler.getCurrentGamemodeFile()));

        TextView gamemodeText = (TextView) findViewById(R.id.gamemode_title);
        gamemodeText.setText(prefs.getString("gamemode", Constants.GAMEMODES[0]).toUpperCase());


        String selectedGameMode = prefs.getString("gamemode", Constants.GAMEMODES[0]);
        if (!selectedGameMode.equals(Constants.GAMEMODES[0])) {
            TextView highscoreText = (TextView) findViewById(R.id.highscore_text);
            int highscoreForSelectedGame = prefs.getInt(selectedGameMode+"highscore", 0);
            //highscoreText.setText(prefs.getInt(selectedGameMode + "highscore"));
            //highscoreText.setText(prefs.getInt(prefs.getString("gamemode", Constants.GAMEMODES[0]) + "highscore", 0));
            highscoreText.setText("Highscore: " + Integer.toString(highscoreForSelectedGame));

        }


    }

    private void databaseSetup() {
        MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        dbHandler.removeAllRows();
        //StoreItem storeItem = new StoreItem("theme", "neon", R.drawable.neonthemetemplate,
        //        5000, 1, false);
        //dbHandler.addStoreItem(storeItem);


        storeItemsSetup(dbHandler,"shape theme", Constants.SHAPE_THEMES, Constants.SHAPE_THEMES_FILES, Constants.SHAPE_THEMES_PRICE_POINTS, Constants.SHAPE_THEMES_PRICE_MONEY);
        storeItemsSetup(dbHandler,"shape type", Constants.SHAPE_TYPES, Constants.SHAPE_TYPES_FILES, Constants.SHAPE_TYPES_PRICE_POINTS, Constants.SHAPE_TYPES_PRICE_MONEY);
        storeItemsSetupBackground(dbHandler,"background", Constants.BACKGROUNDS, Constants.BACKGROUNDS_FILES, Constants.BACKGROUND_WARNINGCOLOR_1, Constants.BACKGROUND_WARNINGCOLOR_2, Constants.BACKGROUNDS_PRICE_POINTS, Constants.BACKGROUNDS_PRICE_MONEY);
        storeItemsSetup(dbHandler,"game mode", Constants.GAMEMODES, Constants.GAMEMODES_FILES, Constants.GAMEMODES_PRICE_POINTS, Constants.GAMEMODES_PRICE_MONEY);
        storeItemsSetup(dbHandler,"multiplier", Constants.MULTIPLIERS, Constants.MULTIPLIER_FILES, Constants.MULTIPLIERS_PRICE_POINTS, Constants.MULTIPLIERS_PRICE_MONEY);
        storeItemsSetup(dbHandler,"color streak", Constants.WARNING_COLOR_STREAK_REWARDS, Constants.WARNING_COLOR_STREAK_REWARDS_FILES, Constants.WARNING_COLOR_STREAK_REWARDS_IDS_PRICE_POINTS, Constants.WARNING_COLOR_STREAK_REWARDS_IDS_PRICE_MONEY);

        unlockBeginningItems(dbHandler);
        /*
        for(String name : dbHandler.getListOfLockedItems("shape theme")) {
            Log.d("database", "" + name);
        }*/
        //Log.d("database", ""+dbHandler.getListOfLockedItems("shape theme").length);
        //Log.d("database", ""+dbHandler.getListOfLockedItems("color streak").length);
        Log.d("database", ""+dbHandler.databaseToString());

    }


    private void unlockBeginningItems(MyDBHandler dbHandler) {
        dbHandler.unlockItemViaName(Constants.SHAPE_THEMES[0]);
        dbHandler.unlockItemViaName(Constants.SHAPE_THEMES[1]);
        dbHandler.unlockItemViaName(Constants.BACKGROUNDS[0]);
        dbHandler.unlockItemViaName(Constants.BACKGROUNDS[1]);
        dbHandler.unlockItemViaName(Constants.SHAPE_TYPES[0]);
        dbHandler.unlockItemViaName(Constants.SHAPE_TYPES[1]);
        dbHandler.unlockItemViaName(Constants.GAMEMODES[0]);
        dbHandler.unlockItemViaName(Constants.GAMEMODES[1]);
        dbHandler.unlockItemViaName(Constants.GAMEMODES[2]);
        dbHandler.unlockItemViaName(Constants.GAMEMODES[3]);
        dbHandler.unlockItemViaName(Constants.GAMEMODES[4]);
        dbHandler.unlockItemViaName(Constants.GAMEMODES[5]);
        dbHandler.unlockItemViaName(Constants.MULTIPLIERS[0]);
        dbHandler.unlockItemViaName(Constants.WARNING_COLOR_STREAK_REWARDS[0]);
        //dbHandler.getWritableDatabase().execSQL("UPDATE " + dbHandler.TABLE_STOREITEMS + " SET " + dbHandler.COLUMN_UNLOCKED + " = 1 WHERE " + dbHandler.COLUMN_NAME + " = '" + Constants.SHAPE_THEMES[0] + "';");
        //dbHandler.getWritableDatabase().execSQL("UPDATE " + dbHandler.TABLE_STOREITEMS + " SET " + dbHandler.COLUMN_UNLOCKED + " = 1 WHERE " + dbHandler.COLUMN_NAME + " = '" + Constants.SHAPE_THEMES[1] + "';");
        //dbHandler.getWritableDatabase().execSQL("UPDATE " + dbHandler.TABLE_STOREITEMS + " SET " + dbHandler.COLUMN_UNLOCKED + " = 1 WHERE " + dbHandler.COLUMN_NAME + " = '" + Constants.BACKGROUNDS[0] + "';");
        //////////dbHandler.getWritableDatabase().execSQL("UPDATE " + dbHandler.TABLE_STOREITEMS + " SET " + dbHandler.COLUMN_UNLOCKED + " = 1 WHERE " + dbHandler.COLUMN_NAME + " = '" + Constants.BACKGROUNDS[1] + "';");
        //////////dbHandler.getWritableDatabase().execSQL("UPDATE " + dbHandler.TABLE_STOREITEMS + " SET " + dbHandler.COLUMN_UNLOCKED + " = 1 WHERE " + dbHandler.COLUMN_NAME + " = '" + Constants.SHAPE_TYPES[0] + "';");
        //////////dbHandler.getWritableDatabase().execSQL("UPDATE " + dbHandler.TABLE_STOREITEMS + " SET " + dbHandler.COLUMN_UNLOCKED + " = 1 WHERE " + dbHandler.COLUMN_NAME + " = '" + Constants.SHAPE_TYPES[1] + "';");
        //////////dbHandler.getWritableDatabase().execSQL("UPDATE " + dbHandler.TABLE_STOREITEMS + " SET " + dbHandler.COLUMN_UNLOCKED + " = 1 WHERE " + dbHandler.COLUMN_NAME + " = '" + Constants.GAMEMODES[0] + "';");
        //////////dbHandler.getWritableDatabase().execSQL("UPDATE " + dbHandler.TABLE_STOREITEMS + " SET " + dbHandler.COLUMN_UNLOCKED + " = 1 WHERE " + dbHandler.COLUMN_NAME + " = '" + Constants.GAMEMODES[1] + "';");
        //////////dbHandler.getWritableDatabase().execSQL("UPDATE " + dbHandler.TABLE_STOREITEMS + " SET " + dbHandler.COLUMN_UNLOCKED + " = 1 WHERE " + dbHandler.COLUMN_NAME + " = '" + Constants.GAMEMODES[2] + "';");
        //////////dbHandler.getWritableDatabase().execSQL("UPDATE " + dbHandler.TABLE_STOREITEMS + " SET " + dbHandler.COLUMN_UNLOCKED + " = 1 WHERE " + dbHandler.COLUMN_NAME + " = '" + Constants.GAMEMODES[3] + "';");
        //////////dbHandler.getWritableDatabase().execSQL("UPDATE " + dbHandler.TABLE_STOREITEMS + " SET " + dbHandler.COLUMN_UNLOCKED + " = 1 WHERE " + dbHandler.COLUMN_NAME + " = '" + Constants.MULTIPLIERS[0] + "';");
        //////////dbHandler.getWritableDatabase().execSQL("UPDATE " + dbHandler.TABLE_STOREITEMS + " SET " + dbHandler.COLUMN_UNLOCKED + " = 1 WHERE " + dbHandler.COLUMN_NAME + " = '" + Constants.WARNING_COLOR_STREAK_REWARDS[0] + "';");

    }

    private void storeItemsSetup(MyDBHandler dbHandler, String category, String names[], String files[], int[] pricePoints, int[] priceMoney) {
        for (int i = 0;  i < names.length ; i++ ) {
            dbHandler.addStoreItem(new StoreItem(category,names[i], files[i],0, 0, pricePoints[i], priceMoney[i], 0));
        }

    }

    private void storeItemsSetupBackground(MyDBHandler dbHandler, String category, String names[], String files[], Integer[] warningColor1, Integer[] warningColor2, int[] pricePoints, int[] priceMoney) {
        for (int i = 0;  i < names.length ; i++ ) {
            dbHandler.addStoreItem(new StoreItem(category,names[i], files[i],warningColor1[i], warningColor2[i], pricePoints[i], priceMoney[i], 0));
        }
    }

    private void setupPointsDisplay() {
        TextView pointsText = (TextView) findViewById(R.id.points_text);
        pointsText.setText(Integer.toString(prefs.getInt("points", 0)));
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


    // shows the settings alert dialog
    /*
    public void menuClick(View view) {

        //view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_item));

        YoYo.with(Techniques.Pulse).duration(500).repeat(0).playOn(view);
        // play settings click noise
        //playSound(R.raw.opensettings);

        // inflate the dialog layout
        View alertView = getLayoutInflater().inflate(R.layout.dialog_settings, null);

        //soundTogglerSetup(alertView);

        // create a builder for the alert
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);

        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();

        okButtonSetup(alertView, dialog);

        dialogFullscreen(dialog);
    }
    */

    public void dialogFullscreen(AlertDialog dialog) {

        // to remove square edges from custom dialog shape
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Set the dialog to not focusable (makes navigation ignore us adding the window)
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.show();
        //Set the dialog to immersive
        dialog.getWindow().getDecorView().setSystemUiVisibility(
                this.getWindow().getDecorView().getSystemUiVisibility());

        //Clear the not focusable flag from the window
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }


    private void okButtonSetup(View alertView, final AlertDialog dialog) {

        TextView okButt = (TextView) alertView.findViewById(R.id.ok_button);

        okButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //playSound(R.raw.closesettings);
                dialog.dismiss();
            }
        });
    }


    /*
    private void soundTogglerSetup(View alertView) {
        // sound toggler view
        final TextView soundText = (TextView) alertView.findViewById(R.id.sound_setting);

        // depending on the current sound setting- set to ON or OFF img
        //setSoundText(soundText);

        // on click of the soundText, set to opposite img
        soundText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (soundOn()) {
                    // set OFF
                    setSoundPrefAndText(false, soundText);
                    //repeatMpStop();
                } else {
                    // set ON
                    setSoundPrefAndText(true, soundText);
                    //repeatMpResume();
                }
                //playSound(R.raw.settingsswitch);
            }
        });
    }
    */


    // plays music
    private void initMusic(int sound) {
        MediaPlayer mp = MediaPlayer.create(this, sound);
        mp.setLooping(true);
        mediaPlayers.add(mp);
        if (soundOn()) mp.start();
    }


    // plays sound if sound is ON
    private void playSound(int sound) {

        if (soundOn()) {
            MediaPlayer mp = MediaPlayer.create(this, sound);
            mp.start();
        }
    }


    public boolean soundOn() {
        return prefs.getBoolean("sound", true);
    }


    public void setSoundText(TextView soundText) {
        if (soundOn()) {
            // set ON img
            soundText.setText("ON");
            soundText.setTextColor(getResources().getColor(R.color.soundon));
        } else {
            // set OFF toggle
            soundText.setText("OFF");
            soundText.setTextColor(getResources().getColor(R.color.soundoff));
        }
    }


    private void setSoundPrefAndText(boolean onOrOff, TextView soundText) {
        SharedPreferences.Editor prefsEditior = prefs.edit();
        prefsEditior.putBoolean("sound", onOrOff);
        prefsEditior.apply();
        setSoundText(soundText);
    }

/*
    public void repeatMpStop() {
        for (MediaPlayer mp : mediaPlayers) {
                mp.pause();
        }
    }*/


    /*public void repeatMpResume() {
        for (MediaPlayer mp : mediaPlayers) {
            if (soundOn())
                mp.start();
        }
    }*/


    // when app opens up again
    @Override
    protected void onResume() {
        super.onResume();
        //startAnimatingMorePointsImg();
        //requestNewTimedMenuAd();
        //Log.d("resumecalled", "onResume: RESUME");
        //repeatMpResume();
    }


    // when app is closed
    @Override
    protected void onStop() {
        super.onStop();
        //repeatMpStop();
    }


    /*
    public void playButtonClick(View view) {
        //YoYo.with(Techniques.Pulse).duration(500).repeat(0).playOn(view);
        //view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_item));
        Intent intent = new Intent(this, MainGameActivity.class);
        intent.putExtra("gamemode", "classic");
        //this.startActivity(intent);
        this.startActivityForResult(intent, 1);
    }
    */

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        updatePoints();
        setupGameModeImageAndTextAndHighscore();

        SharedPreferences.Editor editor = prefs.edit();
        if(resultCode == 1){
            Log.d("gamesads", "onActivityResult: game over exit" );
            //Log.d("endintent", Integer.toString(prefs.getInt("points",0)));
            //TextView pointsText = (TextView) findViewById(R.id.points_text);
            //pointsText.setText(Integer.toString(prefs.getInt("points", 0)));
            //YoYo.with(Techniques.BounceIn).duration(2000).repeat(0).playOn(pointsText);
            //updatePoints();
            //setupGameModeImageAndTextAndHighscore();
            //startIntervalAd();
            //int gamesSinceLastAd = prefs.getInt("gamesSinceLastAd",0);
            //editor.putInt("gamesSinceLastAd", gamesSinceLastAd+1);
            editor.putInt("gamesSinceLastAd", prefs.getInt("gamesSinceLastAd",0)+1);
            editor.apply();
        }

        Log.d("gamesads", "onActivityResult: " + prefs.getInt("gamesSinceLastAd",0));
        if (prefs.getInt("gamesSinceLastAd",0) > 2 && afterGameAd.isLoaded()) {
            afterGameAd.show();
            editor.putInt("gamesSinceLastAd", 0);
            editor.apply();
            requestNewAfterGameAd();
        }

        //super.onActivityResult(requestCode, resultCode, data);
    }
    */

    public void updatePoints() {
        TextView pointsText = (TextView) findViewById(R.id.points_text);
        pointsText.setText(Integer.toString(prefs.getInt("points", 0)));
        YoYo.with(Techniques.BounceIn).duration(2000).repeat(0).playOn(pointsText); // todo this works for text animation if bestScore/bestStreak >= score do animaton
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

    public void storeSetup() {

        //this.store = new Store1(this);
    }






    public void extraPointsViaVidClick(View view) {
        this.morePointsMenu.extraPointsViaVidClick();
    }

    public void doublePointsPurchaseClick(View view) {
        this.morePointsMenu.doublePointsPurchaseClick();
    }



    /*
    private void okButtonLockInSetup(final View alertView, final AlertDialog dialog, final View viewWithViewToUpdate) {

        TextView okButt = (TextView) alertView.findViewById(R.id.ok_button);

        okButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //playSound(R.raw.closesettings);
                updateStoreSelected(viewWithViewToUpdate);
                dialog.dismiss();
            }
        });
    }*/

    /*
    private void updateStoreSelected(View alertView) {
        // todo first the colors section
        ImageView shapeColorImg = (ImageView) alertView.findViewById(R.id.shape_color_image);

        android.view.ViewGroup.LayoutParams layoutParams = shapeColorImg.getLayoutParams();
        layoutParams.width = Constants.SCREEN_WIDTH/4;
        layoutParams.height = Constants.SCREEN_WIDTH/4;
        shapeColorImg.setLayoutParams(layoutParams);

        Bitmap bm = BitmapFactory.decodeResource(this.getResources(),
                Constants.SHAPE_THEMES_ID[Arrays.asList(Constants.SHAPE_THEMES).indexOf(prefs.getString("shapeTheme","neon"))]);
        shapeColorImg.setImageBitmap(bm);

        TextView setColorTheme = (TextView) alertView.findViewById(R.id.shape_color_set);
        setColorTheme.setText(prefs.getString("shapeTheme", "neon").toUpperCase());
    }*/

    /*
    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new LoadingFragment(), "load");
        adapter.addFragment(new MainMenuFragment(), "mainmenu");
        adapter.addFragment(new SettingsFragment(), "settings");
        adapter.addFragment(new StoreFragment(), "store");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);

    }
    */
}
