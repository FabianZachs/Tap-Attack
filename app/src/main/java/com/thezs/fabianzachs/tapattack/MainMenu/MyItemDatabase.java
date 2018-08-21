package com.thezs.fabianzachs.tapattack.MainMenu;

import android.app.Activity;
import android.content.SharedPreferences;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.Database.StoreItem;

/**
 * Created by fabianzachs on 18/08/18.
 */

public class MyItemDatabase {

    private MyDBHandler myDBHandler;

    public MyItemDatabase(Activity activity, SharedPreferences prefs) {
        //prefs.edit().putBoolean("firstTime", true).apply(); // for testing

        if (prefs.getBoolean("firstTime", true)) {
            myDBHandler = new MyDBHandler(activity, null, null, 2);
            databaseSetup();

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", false);
            editor.apply();
        }


    }

    public MyDBHandler getMyDBHandler() {
        return myDBHandler;
    }

    private void databaseSetup() {
        myDBHandler.removeAllRows();
        storeItemsSetup(Constants.SHAPE_THEME_TAG, Constants.SHAPE_THEMES, Constants.SHAPE_THEMES_FILES, Constants.SHAPE_THEMES_PRICE_POINTS, Constants.SHAPE_THEMES_PRICE_MONEY);
        storeItemsSetup(Constants.SHAPE_TYPE_TAG, Constants.SHAPE_TYPES, Constants.SHAPE_TYPES_FILES, Constants.SHAPE_TYPES_PRICE_POINTS, Constants.SHAPE_TYPES_PRICE_MONEY);
        storeItemsSetupBackground(Constants.BACKGROUND_TAG, Constants.BACKGROUNDS, Constants.BACKGROUNDS_FILES, Constants.BACKGROUND_WARNINGCOLOR_1, Constants.BACKGROUND_WARNINGCOLOR_2, Constants.BACKGROUNDS_PRICE_POINTS, Constants.BACKGROUNDS_PRICE_MONEY);
        storeItemsSetup(Constants.GAME_MODE_TAG, Constants.GAMEMODES, Constants.GAMEMODES_FILES, Constants.GAMEMODES_PRICE_POINTS, Constants.GAMEMODES_PRICE_MONEY);
        //storeItemsSetup(dbHandler,"multiplier", Constants.MULTIPLIERS, Constants.MULTIPLIER_FILES, Constants.MULTIPLIERS_PRICE_POINTS, Constants.MULTIPLIERS_PRICE_MONEY);
        //storeItemsSetup(dbHandler,"color streak", Constants.WARNING_COLOR_STREAK_REWARDS, Constants.WARNING_COLOR_STREAK_REWARDS_FILES, Constants.WARNING_COLOR_STREAK_REWARDS_IDS_PRICE_POINTS, Constants.WARNING_COLOR_STREAK_REWARDS_IDS_PRICE_MONEY);

        unlockBeginningItems();
    }

    private void unlockBeginningItems() {
        myDBHandler.unlockItemViaName(Constants.SHAPE_THEMES[0]);
        //dbHandler.unlockItemViaName(Constants.SHAPE_THEMES[1]);
        myDBHandler.unlockItemViaName(Constants.BACKGROUNDS[0]);
        //dbHandler.unlockItemViaName(Constants.BACKGROUNDS[1]);
        myDBHandler.unlockItemViaName(Constants.SHAPE_TYPES[0]);
        //dbHandler.unlockItemViaName(Constants.SHAPE_TYPES[1]);
        myDBHandler.unlockItemViaName(Constants.GAMEMODES[0]);
        myDBHandler.unlockItemViaName(Constants.GAMEMODES[1]);
        myDBHandler.unlockItemViaName(Constants.GAMEMODES[2]);
        myDBHandler.unlockItemViaName(Constants.GAMEMODES[3]);
        myDBHandler.unlockItemViaName(Constants.GAMEMODES[4]);
        myDBHandler.unlockItemViaName(Constants.GAMEMODES[5]);
        //dbHandler.unlockItemViaName(Constants.MULTIPLIERS[0]);
        //dbHandler.unlockItemViaName(Constants.WARNING_COLOR_STREAK_REWARDS[0]);
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

    private void storeItemsSetup(String category, String names[], String files[], int[] pricePoints, int[] priceMoney) {
        for (int i = 0;  i < names.length ; i++ ) {
            myDBHandler.addStoreItem(new StoreItem(category,names[i], files[i],0, 0, pricePoints[i], priceMoney[i], 0));
        }

    }

    private void storeItemsSetupBackground(String category, String names[], String files[], Integer[] warningColor1, Integer[] warningColor2, int[] pricePoints, int[] priceMoney) {
        for (int i = 0;  i < names.length ; i++ ) {
            myDBHandler.addStoreItem(new StoreItem(category,names[i], files[i],warningColor1[i], warningColor2[i], pricePoints[i], priceMoney[i], 0));
        }
    }
}
