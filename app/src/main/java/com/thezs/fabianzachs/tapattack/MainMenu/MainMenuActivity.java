package com.thezs.fabianzachs.tapattack.MainMenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Game.MainGameActivity;
import com.thezs.fabianzachs.tapattack.MainMenu.Menu.MainMenuFragment2;
import com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection.PaidItemType;
import com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection.PaidPointsFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection.PaidShieldsFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection.PaidUnlocksFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Settings.SettingsFragment;
import com.thezs.fabianzachs.tapattack.MainMenu.Store.StoreFragment;
import com.thezs.fabianzachs.tapattack.R;

import java.util.HashMap;

public class MainMenuActivity extends GeneralParent implements StoreFragment.StoreListener,
        SettingsFragment.SettingsListener, MainMenuFragment2.MainMenuListener,
        PaidUnlocksFragment.PaidUnlocksListener, PaidItemType.PaidItemTypeListener {

    private SharedPreferences prefs;
    //private AdView mAdView;
    //private InterstitialAd afterGameAd;
    //private InterstitialAd timedMenuAd;


    private MainMenuFragment2 mainMenuFragment;
    private StoreFragment storeFragment;
    private SettingsFragment settingsFragment;
    private PaidUnlocksFragment paidUnlocksFragment;
    private PaidPointsFragment paidPointsFragment;
    private PaidShieldsFragment paidShieldsFragment;
    private MusicPlayer musicPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("playerInfo", MODE_PRIVATE);
        musicSetup();

        setContentView(R.layout.main_menu4);

        if (findViewById(R.id.main_fragment) != null) {

            if (savedInstanceState != null) {
                return;
            }

            createAndAddFragments();
            displayMainMenuFragment();
        }


        new MyItemDatabase(this, prefs);
        initializeConstants();
    }


    @Override
    public void mainMenuFragmentToSettingsFragment() {
        displaySettingsFragment();
    }

    @Override
    public void mainMenuFragmentToStoreFragment() {
        displayStoreFragment();
    }

    @Override
    public void mainMenuFragmentToMorePointsFragment() {
        displayPaidItemsFragment();
    }

    @Override
    public void storeFragmentToMenuFragment() {
        displayMainMenuFragment();
    }

    @Override
    public void playGameClick() {
        playButtonClick();
    }

    @Override
    public void settingsFragmentToMenuFragment() {
        displayMainMenuFragment();

    }

    @Override
    public void paidUnlocksFragmentToMainMenuFragment() {
        displayMainMenuFragment();
    }

    @Override
    public void paidTypeFragmentToPaidUnlocksFragment() {
        displayPaidItemsFragment();
    }

    @Override
    public void paidTypeFragmentToMainMenuFragment() {
        displayMainMenuFragment();
    }

    @Override
    public void paidUnlocksFragmentToPaidPointsFragment() {
        displayPaidPointsFragment();

    }

    @Override
    public void paidUnlocksFragmentToPaidShieldsFragment() {
        displayPaidShieldsFragment();
    }

    /*
    @Override
    public void paidItemsFragmentToPaidPointsFragment() {
        displayPaidPointsFragment();
    }*/


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
    protected void onPause() {
        super.onPause();
        musicPlayer.pausePlaying();
    }

    private void playButtonClick() {
        removeStoreFragment();
        Intent intent = new Intent(this, MainGameActivity.class);
        intent.putExtra(Constants.GAME_MODE_TAG, "classic");
        this.startActivityForResult(intent, 1);
    }

    public void displayPaidShieldsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (paidShieldsFragment.isAdded()) {
            ft.show(paidShieldsFragment);
        } else {
            ft.add(R.id.main_fragment, paidShieldsFragment, Constants.PAID_SHIELDS_TAG);
        }
        if (storeFragment.isAdded()) { ft.hide(storeFragment); }
        //if (mainMenuFragment.isAdded()) { ft.hide(mainMenuFragment); }
        if (settingsFragment.isAdded()) { ft.hide(settingsFragment); }
        ft.commit();
    }

    public void displayPaidPointsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (paidPointsFragment.isAdded()) {
            ft.show(paidPointsFragment);
        } else {
            ft.add(R.id.main_fragment, paidPointsFragment, Constants.PAID_POINTS_TAG);
        }
        if (storeFragment.isAdded()) { ft.hide(storeFragment); }
        //if (mainMenuFragment.isAdded()) { ft.hide(mainMenuFragment); }
        if (settingsFragment.isAdded()) { ft.hide(settingsFragment); }
        ft.commit();
    }


    public void displayPaidItemsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (paidUnlocksFragment.isAdded()) {
            ft.show(paidUnlocksFragment);
        } else {
            ft.add(R.id.main_fragment, paidUnlocksFragment, Constants.PAID_UNLOCKS_TAG);
        }
        if (paidPointsFragment.isAdded()) { ft.hide(paidPointsFragment); }
        if (paidShieldsFragment.isAdded()) { ft.hide(paidShieldsFragment); }
        if (storeFragment.isAdded()) { ft.hide(storeFragment); }
        //if (mainMenuFragment.isAdded()) { ft.hide(mainMenuFragment); }
        if (settingsFragment.isAdded()) { ft.hide(settingsFragment); }
        ft.commit();
    }
    public void displayMainMenuFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (mainMenuFragment.isAdded()) {
            mainMenuFragment.onShow();
            ft.show(mainMenuFragment);
        } else {
            ft.add(R.id.main_fragment, mainMenuFragment, Constants.MAIN_TAG);
        }
        if (storeFragment.isAdded()) { ft.hide(storeFragment); }
        if (paidUnlocksFragment.isAdded()) { ft.hide(paidUnlocksFragment); }
        if (paidShieldsFragment.isAdded()) { ft.hide(paidShieldsFragment); }
        if (paidPointsFragment.isAdded()) { ft.hide(paidPointsFragment); }
        if (settingsFragment.isAdded()) { ft.hide(settingsFragment); }
        ft.commit();
    }

    public void displayStoreFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (storeFragment.isAdded()) {
            storeFragment.onShow();
            ft.show(storeFragment);
        } else {
            ft.add(R.id.main_fragment, storeFragment, Constants.STORE_TAG);
        }
        if (mainMenuFragment.isAdded()) { ft.hide(mainMenuFragment); }
        if (paidUnlocksFragment.isAdded()) { ft.hide(paidUnlocksFragment); }
        if (settingsFragment.isAdded()) { ft.hide(settingsFragment); }
        ft.commit();
    }

    public void displaySettingsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (settingsFragment.isAdded()) {
            ft.show(settingsFragment);
        } else {
            ft.add(R.id.main_fragment, settingsFragment, Constants.SETTINGS_TAG);
        }
        if (mainMenuFragment.isAdded()) { ft.hide(mainMenuFragment); }
        if (paidUnlocksFragment.isAdded()) { ft.hide(paidUnlocksFragment); }
        if (storeFragment.isAdded()) { ft.hide(storeFragment); }
        ft.commit();
    }

    private void removeStoreFragment() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Constants.STORE_TAG);
        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }



    private void createAndAddFragments() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        this.mainMenuFragment = new MainMenuFragment2();
        this.storeFragment = new StoreFragment();
        this.settingsFragment = new SettingsFragment();
        this.paidUnlocksFragment = new PaidUnlocksFragment();
        this.paidPointsFragment = new PaidPointsFragment();
        this.paidShieldsFragment = new PaidShieldsFragment();
        //ft.add(R.id.main_fragment, mainMenuFragment, "mainmenu");
        //ft.add(R.id.main_fragment, storeFragment,Constants.STORE_TAG);
        //ft.add(R.id.main_fragment, settingsFragment, Constants.SETTINGS_TAG);
        ft.commit();
    }

    private void musicSetup() {
        this.musicPlayer = new MusicPlayer(this);
        if (prefs.getInt("music", 1) == 1)
            musicPlayer.play();
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

/*
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
    */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((MainMenuFragment2) getSupportFragmentManager().findFragmentByTag(Constants.MAIN_TAG)).onShow();

        SharedPreferences.Editor editor = prefs.edit();
        if(resultCode == 1){
            editor.putInt("gamesSinceLastAd", prefs.getInt("gamesSinceLastAd",0)+1);
            editor.apply();
        }

        if (prefs.getInt("gamesSinceLastAd",0) > 2 /*&& afterGameAd.isLoaded()*/) {
            //afterGameAd.show();
            editor.putInt("gamesSinceLastAd", 0);
            editor.apply();
            //requestNewAfterGameAd();
        }

        //super.onActivityResult(requestCode, resultCode, data);
    }

}

