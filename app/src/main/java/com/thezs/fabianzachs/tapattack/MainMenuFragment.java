package com.thezs.fabianzachs.tapattack;

import android.annotation.TargetApi;
import android.app.AlertDialog;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.thezs.fabianzachs.tapattack.Animation.Themes.ThemesManager;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.Database.StoreItem;
import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.BackgroundManager;
import com.thezs.fabianzachs.tapattack.Game.MainGameActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabianzachs on 09/08/18.
 */

public class MainMenuFragment extends Fragment {

    private ArrayList<MediaPlayer> mediaPlayers; // these players loop -> turn of onStop()
    private ThemesManager themesManager;
    private BackgroundManager backgroundManager;
    private SharedPreferences prefs;
    private AdView mAdView;
    private Store store;
    private MorePointsMenu morePointsMenu;
    private InterstitialAd afterGameAd;
    private InterstitialAd timedMenuAd;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //helper.makeFullscreen(getActivity());


        // set up Constants
        initializeConstants();

        prefs = getActivity().getSharedPreferences("playerInfo", MODE_PRIVATE);
        //if (!prefs.getBoolean("firstTime", false)) {
        Log.d("thisran", "onstart: ran");
        databaseSetup();

        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstTime", true);
        editor.apply();
        //}

        this.store = new Store(getActivity(), prefs);
        this.morePointsMenu = new MorePointsMenu(getActivity(), prefs);
        // method instantiation
        //mediaPlayers = new ArrayList<MediaPlayer>();

        //editor.putString("gamemode","classic");

        /*
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString("background", Constants.BACKGROUNDS[1]);
        prefsEditor.apply();
*/
        //setContentView(R.layout.activity_main_menu);
        //setContentView(R.layout.main_menu_fragment);

        //buttonEffect(/*findViewById(R.id.menu_text)*/);

        //multiShapesMessaroundDELETE(); //todo erase
        /* programmatically set background
        LinearLayout layout = (LinearLayout) findViewById(R.id.parent_layout);
        layout.setBackground(ContextCompat.getDrawable(this, R.drawable.backgroundtriangleblue));
        */



        // for images in store
        //gameTheme = themesManager.buildTheme("vibrant", "curved");
        //themesManager.setCurrentGameConstants(themesManager.buildTheme(Constants.CURRENT_THEME,Constants.CURRENT_SHAPE_TYPE));

        //this.backgroundManager = new BackgroundManager(prefs.getString("background","backgroundtriangleblue"));


        //initMusic(R.raw.mainmenu);

        /////////////// DATABASE ////////////////






        //MyDBHandler dbHandler = new MyDBHandler(this, null, null, 1);
        //dbHandler.deleteStoreItem("neon");
        //Log.d("database", "stuff "+dbHandler.databaseToString());



        /*
        afterGameAd = new InterstitialAd(this);
        afterGameAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        AdRequest request = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        afterGameAd.loadAd(request);
        */
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.main_menu_fragment, container, false);
        setupGameModeImageAndTextAndHighscore(view);
        setupPointsDisplay(view);
        //helper.bannerAdSetup(getActivity(), mAdView);
        /*
        store.getMainStoreDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                setupGameModeImageAndTextAndHighscore(view);
            }
        });
        */
        TextView menuButton = (TextView) view.findViewById(R.id.menu_text);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuClick(view);
            }
        });

        RelativeLayout morePointsButton = (RelativeLayout) view.findViewById(R.id.more_points_section);
        morePointsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pointsSectionClick(view);
            }
        });

        startAnimatingMorePointsImg(view);
        requestNewAfterGameAd();
        requestNewTimedMenuAd();
        startIntervalAd();
        TextView storeButton = (TextView) view.findViewById(R.id.store_text);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainMenuActivity)getActivity()).setViewPager(1);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        //helper.makeFullscreen(getActivity());


        // set up Constants
        /*
        initializeConstants();

        prefs = getActivity().getSharedPreferences("playerInfo", MODE_PRIVATE);
        //if (!prefs.getBoolean("firstTime", false)) {
        Log.d("thisran", "onstart: ran");
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
        //setContentView(R.layout.main_menu_fragment);


        setupGameModeImageAndTextAndHighscore();

        setupPointsDisplay();
        //multiShapesMessaroundDELETE(); //todo erase

        Log.d("adcreation", "onCreate: ad created");
        helper.bannerAdSetup(getActivity(), mAdView);

        this.store = new Store(getActivity(), prefs);
        store.getMainStoreDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                setupGameModeImageAndTextAndHighscore();
            }
        });

        this.morePointsMenu = new MorePointsMenu(getActivity(), prefs);

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



        /*
        Thread timer = new Thread() {
            public void run() {
                boolean running = true;
                while (running) {
                    try {
                        // todo show ad, load another ad (image ad not video)
                        if (timedMenuAd.isLoaded()) {
                            timedMenuAd.show();
                            requestNewTimedMenuAd();
                        }

                        Thread.sleep(8000);

                    } catch (InterruptedException e) {
                        Log.d("running", "run: error");
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.start();
        */


        /*
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // todo show ad, load another ad (image ad not video)
                        if (timedMenuAd.isLoaded()) {
                            timedMenuAd.show();
                            requestNewTimedMenuAd();
                        }

                        Thread.sleep(8000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
*/

        /*
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // do your stuff - don't create a new runnable here!
                if (timedMenuAd.isLoaded()) {
                    timedMenuAd.show();
                    requestNewTimedMenuAd();
                }
                    handler.postDelayed(this, 10000);
            }
        };
        handler.post(runnable);
        */


    }

    private void startAnimatingMorePointsImg(View view) {
        ImageView morePointsImg = (ImageView) view.findViewById(R.id.more_points_sign);
        YoYo.with(Techniques.Tada).duration(2000).repeat(100).playOn(morePointsImg); // todo this works for text animation if bestScore/bestStreak >= score do animaton
        // todo which of Bounce, Swing, Pulse, Flash, Tada
    }

    private void setupGameModeImageAndTextAndHighscore(View view) {
        MyDBHandler myDBHandler = new MyDBHandler(Constants.CURRENT_CONTEXT, null, null, 1);

        ImageView gamemodeImg = (ImageView) view.findViewById(R.id.play_button);
        gamemodeImg.setImageResource(helper.getResourceId(Constants.CURRENT_CONTEXT, myDBHandler.getCurrentGamemodeFile()));

        TextView gamemodeText = (TextView) view.findViewById(R.id.gamemode_title);
        gamemodeText.setText(prefs.getString("gamemode", Constants.GAMEMODES[0]).toUpperCase());


        String selectedGameMode = prefs.getString("gamemode", Constants.GAMEMODES[0]);
        if (!selectedGameMode.equals(Constants.GAMEMODES[0])) {
            TextView highscoreText = (TextView) view.findViewById(R.id.highscore_text);
            int highscoreForSelectedGame = prefs.getInt(selectedGameMode+"highscore", 0);
            //highscoreText.setText(prefs.getInt(selectedGameMode + "highscore"));
            //highscoreText.setText(prefs.getInt(prefs.getString("gamemode", Constants.GAMEMODES[0]) + "highscore", 0));
            highscoreText.setText("Highscore: " + Integer.toString(highscoreForSelectedGame));

        }


    }

    private void databaseSetup() {
        MyDBHandler dbHandler = new MyDBHandler(getActivity(), null, null, 1);
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

    private void setupPointsDisplay(View view) {
        TextView pointsText = (TextView) view.findViewById(R.id.points_text);
        pointsText.setText(Integer.toString(prefs.getInt("points", 0)));
    }

    private void initializeConstants() {

        Constants.CURRENT_CONTEXT = getActivity();

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
                (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point screenResolution = new Point();

        if (Build.VERSION.SDK_INT < 14)
            throw new RuntimeException("Unsupported Android version.");
        display.getRealSize(screenResolution);

        return screenResolution;
    }


    // shows the settings alert dialog
    public void menuClick(View view) {

        //view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_item));

        YoYo.with(Techniques.Pulse).duration(500).repeat(0).playOn(view);
        // play settings click noise
        //playSound(R.raw.opensettings);

        // inflate the dialog layout
        View alertView = getActivity().getLayoutInflater().inflate(R.layout.dialog_settings, null);

        //soundTogglerSetup(alertView);

        // create a builder for the alert
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(getActivity());

        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();

        okButtonSetup(alertView, dialog);

        dialogFullscreen(dialog);
    }

    public void dialogFullscreen(AlertDialog dialog) {

        // to remove square edges from custom dialog shape
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Set the dialog to not focusable (makes navigation ignore us adding the window)
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.show();
        //Set the dialog to immersive
        dialog.getWindow().getDecorView().setSystemUiVisibility(
                getActivity().getWindow().getDecorView().getSystemUiVisibility());

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
        MediaPlayer mp = MediaPlayer.create(getActivity(), sound);
        mp.setLooping(true);
        mediaPlayers.add(mp);
        if (soundOn()) mp.start();
    }


    // plays sound if sound is ON
    private void playSound(int sound) {

        if (soundOn()) {
            MediaPlayer mp = MediaPlayer.create(getActivity(), sound);
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
    public void onResume() {
        super.onResume();
        //startAnimatingMorePointsImg();
        //requestNewTimedMenuAd();
        //Log.d("resumecalled", "onResume: RESUME");
        //repeatMpResume();
    }


    // when app is closed
    @Override
    public void onStop() {
        super.onStop();
        //requestNewTimedMenuAd();
        //repeatMpStop();
    }


    public void playButtonClick(View view) {
        //YoYo.with(Techniques.Pulse).duration(500).repeat(0).playOn(view);
        //view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_item));
        Intent intent = new Intent(getActivity(), MainGameActivity.class);
        intent.putExtra("gamemode", "classic");
        //this.startActivity(intent);
        this.startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        updatePoints();
        setupGameModeImageAndTextAndHighscore(getView());

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

    public void updatePoints() {
        TextView pointsText = (TextView) getView().findViewById(R.id.points_text);
        pointsText.setText(Integer.toString(prefs.getInt("points", 0)));
        YoYo.with(Techniques.BounceIn).duration(2000).repeat(0).playOn(pointsText); // todo this works for text animation if bestScore/bestStreak >= score do animaton
    }

    private void requestNewAfterGameAd() {
        afterGameAd = new InterstitialAd(getActivity());
        afterGameAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        AdRequest request = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        afterGameAd.loadAd(request);
    }

    private void requestNewTimedMenuAd() {
        timedMenuAd = new InterstitialAd(getActivity());
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

        //this.store = new Store(this);
    }



    public void storeClick(View view) {
        //((MainMenuActivity)getActivity()).setViewPager(1);

        //view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_item));

        //Intent intent = new Intent(getActivity(), StoreFragment.class);
        //this.startActivity(intent);
        //store.storeClicked();
















        //YoYo.with(Techniques.TakingOff).duration(1000).repeat(0).playOn(findViewById(R.id.store_text)); // todo this works for text animation if bestScore/bestStreak >= score do animaton

        /*

        // todo ne    already done
        View alertView = getLayoutInflater().inflate(R.layout.store_main_menu, null);


        AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);

        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();
        //^ todo ne    already done
        /*

        okButtonSetup(alertView, dialog);

        dialogFullscreen(dialog);

// start jere
        /// =====================
        // shape_color_image
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


        ////// ================================

        // shape_type_image
        ImageView shapeTypeImg = (ImageView) alertView.findViewById(R.id.shape_type_image);

        shapeTypeImg.setLayoutParams(layoutParams);

        String outlineName = prefs.getString("shapeTpe", "curved") + "outline";
        int resID = this.getResources().getIdentifier(outlineName, "drawable", this.getPackageName());
        shapeTypeImg.setImageResource(resID);


        TextView setTypeShape = (TextView) alertView.findViewById(R.id.shape_type_set);
        setTypeShape.setText(prefs.getString("shapeType", "curved").toUpperCase());

        // =======================================

        // background_image
        ImageView backgroundImg = (ImageView) alertView.findViewById(R.id.background_image);
        backgroundImg.setLayoutParams(layoutParams);
        //backgroundImg.setImageBitmap(backgroundManager.getBackground());
        backgroundImg.setImageBitmap(GameBackground.getBackgroundBitmap(prefs.getString("background","backgroundtriangleblue")));

        // =======================================

        //SharedPreferences.Editor prefsEditor = prefs.edit();
        //prefsEditor.putString("shapeTheme", "neon");
        //prefsEditor.apply();



        //StyleableToast.makeText(this,  prefs.getString("theme","error-no theme"), R.style.successtoast).show();
        */
    }


    public void openShapeTypeStore(View view) {
        // todo complete
        store.openStoreSection(view, "type");
        /*
        View alertView = getLayoutInflater().inflate(R.layout.store_item_list, null);
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);
        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();
        okButtonSetup(alertView, dialog);
        dialogFullscreen(dialog);

        ListView mList = (ListView) alertView.findViewById(R.id.item_list);


        CustomListView customListView = new CustomListView(this, Constants.SHAPE_TYPES, Constants.SHAPE_TYPES_IDS);
        mList.setAdapter(customListView);
        */


    }


    public void openBackgroundStore(View view) {
        store.openStoreSection(view, "background");
        /*
        View alertView = getLayoutInflater().inflate(R.layout.store_item_list, null);
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);
        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();
        okButtonSetup(alertView, dialog);
        dialogFullscreen(dialog);

        ListView mList = (ListView) alertView.findViewById(R.id.item_list);
        CustomListView customListView = new CustomListView(this, Constants.BACKGROUNDS, Constants.BACKGROUNDS_ID);
        mList.setAdapter(customListView);
        */
    }

    public void openShapeColorStore(View view) {
        store.openStoreSection(view, "color");
        /*
        View alertView = getLayoutInflater().inflate(R.layout.store_item_list, null);
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(this);
        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();
        okButtonLockInSetup(alertView, dialog, view);
        dialogFullscreen(dialog);



        ListView mList = (ListView) alertView.findViewById(R.id.item_list);
        CustomListView customListView = new CustomListView(this, Constants.SHAPE_THEMES, Constants.SHAPE_THEMES_ID);
        mList.setAdapter(customListView);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                view.setSelected(true);


                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putString("shapeTheme", Constants.SHAPE_THEMES[position]);
                prefsEditor.apply();
                //Intent intent = new Intent(this, SendMessage.class);
                //String message = "abc";
                //intent.putExtra(EXTRA_MESSAGE, message);
                //startActivity(intent);
            }
        }); */

    }

    public void openGameModeStore(View view) {
        store.openStoreSection(view, "gamemode");
    }

    public void openPointmultiplierStore(View view) {
        store.openStoreSection(view, "multiplier");
    }

    public void openWarningColorStreakStore(View view) {
        store.openStoreSection(view, "warningcolorstreakreward");
    }

    public void showload(View view) {
        Intent intent = new Intent(getActivity(), LoadingActivity.class);
        intent.putExtra("gamemode", "classic");
        //this.startActivity(intent);
        this.startActivityForResult(intent, 1);
    }

    public void pointsSectionClick(View view) {
        // todo we want a dialog with the current coins
        // todo first option is watch a video to gain z more coins
        // todo offer coin x2 multiplier for $0.99
        // todo yoyo shake the more coins icon every x seconds??
        //StyleableToast.makeText(Constants.CURRENT_CONTEXT, "COIN DIALOG WOULD LAUNCH", R.style.successtoast).show();
        morePointsMenu.pointsSectionClick();
    }

    public void extraPointsViaVidClick(View view) {
        this.morePointsMenu.extraPointsViaVidClick();
    }

    public void doublePointsPurchaseClick(View view) {
        this.morePointsMenu.doublePointsPurchaseClick();
    }
}
