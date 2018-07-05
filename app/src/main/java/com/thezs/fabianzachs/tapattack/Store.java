package com.thezs.fabianzachs.tapattack;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by fabianzachs on 05/06/18.
 */

public class Store {

    private SharedPreferences prefs;
    Activity mainMenuActivity;


    private View mainStoreAlertView;
    final private AlertDialog mainStoreDialog;

    private View colorSectionAlertView;
    final private AlertDialog colorSectionDialog;

    private View typeSectionAlertView;
    final private AlertDialog typeSectionDialog;

    private View backgroundSectionAlertView;
    final private AlertDialog backgroundSectionDialog;

    private View gamemodeSectionAlertView;
    final private AlertDialog gamemodeSectionDialog;

    private View multiplierSectionAlertView;
    final private AlertDialog multiplierSectionDialog;

    private View warningColorStreakSectionAlertView;
    final private AlertDialog warningColorStreakSectionDialog;

    // todo instead of creating dialog on click of store section, create them in start of game
    public Store(Activity mainMenuActivity, SharedPreferences prefs) {

        this.prefs = prefs;
        this.mainMenuActivity = mainMenuActivity;

        this.mainStoreAlertView = helper.getAlertView(mainMenuActivity, R.layout.store_main_menu);
        this.colorSectionAlertView = helper.getAlertView(mainMenuActivity, R.layout.store_item_list);
        this.typeSectionAlertView = helper.getAlertView(mainMenuActivity, R.layout.store_item_list);
        this.backgroundSectionAlertView = helper.getAlertView(mainMenuActivity, R.layout.store_item_list);
        this.gamemodeSectionAlertView = helper.getAlertView(mainMenuActivity, R.layout.store_item_list);
        this.multiplierSectionAlertView = helper.getAlertView(mainMenuActivity, R.layout.store_item_list);
        this.warningColorStreakSectionAlertView = helper.getAlertView(mainMenuActivity, R.layout.store_item_list);

        this.mainStoreDialog = helper.getBuiltDialog(mainMenuActivity, mainStoreAlertView);
        this.colorSectionDialog = helper.getBuiltDialog(mainMenuActivity, colorSectionAlertView);
        this.typeSectionDialog = helper.getBuiltDialog(mainMenuActivity, typeSectionAlertView);
        this.backgroundSectionDialog = helper.getBuiltDialog(mainMenuActivity, backgroundSectionAlertView);
        this.gamemodeSectionDialog = helper.getBuiltDialog(mainMenuActivity, gamemodeSectionAlertView);
        this.multiplierSectionDialog = helper.getBuiltDialog(mainMenuActivity, multiplierSectionAlertView);
        this.warningColorStreakSectionDialog = helper.getBuiltDialog(mainMenuActivity, warningColorStreakSectionAlertView);


        //standardOkButtonSetup(mainStoreAlertView, mainStoreDialog);
        helper.setupStandardDialogDismissButton(R.id.ok_button,mainStoreAlertView, mainStoreDialog);
        setupImgAndTextOfStoreSectons();

        setupDialogDismissForStoreSection(colorSectionAlertView, colorSectionDialog);
        setupDialogDismissForStoreSection(typeSectionAlertView, typeSectionDialog);
        setupDialogDismissForStoreSection(backgroundSectionAlertView, backgroundSectionDialog);
        setupDialogDismissForStoreSection(gamemodeSectionAlertView, gamemodeSectionDialog);
        setupDialogDismissForStoreSection(multiplierSectionAlertView, multiplierSectionDialog);
        setupDialogDismissForStoreSection(warningColorStreakSectionAlertView, warningColorStreakSectionDialog);

        setupStoreSectionList(colorSectionAlertView, colorSectionDialog, Constants.SHAPE_THEMES,
            Constants.SHAPE_THEMES_ID, "shapeTheme");

        setupStoreSectionList(typeSectionAlertView,typeSectionDialog,Constants.SHAPE_TYPES,
               Constants.SHAPE_TYPES_IDS, "shapeType");

        setupStoreSectionList(backgroundSectionAlertView,backgroundSectionDialog, Constants.BACKGROUNDS,
                Constants.BACKGROUNDS_ID,"background");

        setupStoreSectionList(gamemodeSectionAlertView, gamemodeSectionDialog, Constants.GAMEMODES,
                Constants.GAMEMODES_IDS, "gamemode");

        setupStoreSectionList(multiplierSectionAlertView,multiplierSectionDialog,Constants.MULTIPLIERS,
                Constants.MULTIPLIER_IDS, "multiplier");

        setupStoreSectionList(warningColorStreakSectionAlertView,warningColorStreakSectionDialog, Constants.WARNING_COLOR_STREAK_REWARDS,
                Constants.WARNING_COLOR_STREAK_REWARDS_IDS, "warningcolorstreakreward");

//        this.mainStoreAlertView = mainMenuActivity.getLayoutInflater().inflate(R.layout.store_main_menu, null);
        //AlertDialog.Builder mainStoredbuilder = new AlertDialog.Builder(mainMenuActivity);
        //mainStoredbuilder.setView(mainStoreAlertView);
      //  this.mainStoreDialog = mainStoredbuilder.create();
        //this.store = new Store(mainStoreAlertView, mainStoreDialog, this);
    }

    private void setupDialogDismissForStoreSection(View alertView, AlertDialog dialog) {

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                setupImgAndTextOfStoreSectons();
            }
        });
        okButtonLockInSetup(alertView, dialog, mainStoreAlertView);
    }

    private void setupStoreSectionList(View alertView , AlertDialog dialog, final String[] names, final Integer[] IDs, final String prefKey) {

        ListView mList = (ListView) alertView.findViewById(R.id.item_list);
        CustomListView customListView = new CustomListView(mainMenuActivity, names, IDs);
        mList.setAdapter(customListView);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                view.setSelected(true);

                // todo if its an unlock item click: 1) check if valid number of points 2) get random index of locked item 3) unlock that item 4) remove that index from the locked list 5) remove cost of points from prefs

                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putString(prefKey, names[position]);
                prefsEditor.apply();
            }
        });



    }
    /*
    private void buildDialogOLD(final String[] names, Integer[] IDs, final String prefKey) {

        View alertView = mainMenuActivity.getLayoutInflater().inflate(R.layout.store_item_list, null);
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(mainMenuActivity);
        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                setupImgAndTextOfStoreSectons();
            }
        });
        okButtonLockInSetup(alertView, dialog, mainStoreAlertView);
        dialogFullscreen(dialog);

        ListView mList = (ListView) alertView.findViewById(R.id.item_list);
        CustomListView customListView = new CustomListView(mainMenuActivity, names, IDs);
        mList.setAdapter(customListView);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                view.setSelected(true);

                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putString(prefKey, names[position]);
                prefsEditor.apply();
            }
        });

    }
    */

    /*
    private AlertDialog buildDialog(View alertView) {
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(mainMenuActivity);
        dbuilder.setView(alertView);
        return dbuilder.create();

    }
*/
    /*
    private View getAlertView(Integer resID) {
        return mainMenuActivity.getLayoutInflater().inflate(resID, null);
    }*/


    public void storeClicked() {
        //standardOkButtonSetup(mainStoreAlertView, mainStoreDialog);
        //dialogFullscreen(mainStoreDialog);
        helper.dialogFullscreen(mainMenuActivity, mainStoreDialog);

        //setupImgAndTextOfStoreSectons();
    }

    private void setupImgAndTextOfStoreSectons() {

        setupStoreSectionPreviewImg("color", R.id.shape_theme_image);
        setupStoreSectionPreviewImg("type", R.id.shape_type_image);
        setupStoreSectionPreviewImg("background", R.id.background_image);
        setupStoreSectionPreviewImg("gamemode", R.id.gamemode_image);
        setupStoreSectionPreviewImg("multiplier", R.id.pointmultiplier_image);
        setupStoreSectionPreviewImg("warningcolorstreakreward", R.id.warning_color_streak_image);

        setupStoreSectionText("color", R.id.shape_theme_image_description);
        setupStoreSectionText("type", R.id.shape_type_image_description);
        //setupStoreSectionText("gamemode", R.id.gamemode_image_description);
    }

    public void openStoreSection(View view, String section) {

        switch (section) {
            case "color":
                //long startTime = System.currentTimeMillis();
                //long endTime = System.currentTimeMillis();

                //Log.d("timetakencolor", "openStoreSection: color" + (endTime - startTime));

                //buildDialogOLD(/*view, R.layout.store_item_list,*/ Constants.SHAPE_THEMES, Constants.SHAPE_THEMES_ID, "shapeTheme");

                //dialogFullscreen(colorSectionDialog);
                helper.dialogFullscreen(mainMenuActivity, colorSectionDialog);
                return;
            case "type":
                //dialogFullscreen(typeSectionDialog);
                helper.dialogFullscreen(mainMenuActivity, typeSectionDialog);
                /*
                long startTime2 = System.currentTimeMillis();
                buildDialogOLD(Constants.SHAPE_TYPES, Constants.SHAPE_TYPES_IDS, "shapeType");
                long endTime2 = System.currentTimeMillis();

                Log.d("timetakencolor", "openStoreSection: type" + (endTime2 - startTime2));
                */
                return;
            case "background":
                //buildDialogOLD(Constants.BACKGROUNDS, Constants.BACKGROUNDS_ID, "background");
                helper.dialogFullscreen(mainMenuActivity, backgroundSectionDialog);
                //dialogFullscreen(backgroundSectionDialog);
                return;
            case "gamemode":
                helper.dialogFullscreen(mainMenuActivity, gamemodeSectionDialog);
                return;
            case "multiplier":
                helper.dialogFullscreen(mainMenuActivity, multiplierSectionDialog);
                return;
            case "warningcolorstreakreward":
                helper.dialogFullscreen(mainMenuActivity, warningColorStreakSectionDialog);
                return;

        }

        throw new RuntimeException("UNKNOWN STORE SECTION");
        // todo item click listened
    }

    private void setUpList(String[] names, Integer[] IDs) {

    }
/*
    private void buildDialogOLD(final String[] names, Integer[] IDs, final String prefKey) {

        View alertView = mainMenuActivity.getLayoutInflater().inflate(R.layout.store_item_list, null);
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(mainMenuActivity);
        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                setupImgAndTextOfStoreSectons();
            }
        });
        okButtonLockInSetup(alertView, dialog, mainStoreAlertView);
        helper.dialogFullscreen(mainMenuActivity, dialog);

        ListView mList = (ListView) alertView.findViewById(R.id.item_list);
        CustomListView customListView = new CustomListView(mainMenuActivity, names, IDs);
        mList.setAdapter(customListView);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                view.setSelected(true);

                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putString(prefKey, names[position]);
                prefsEditor.apply();
            }
        });

    }*/

    private void okButtonLockInSetup(/*final String section, final Integer resID,*/ final View alertView, final AlertDialog dialog, final View viewWithViewToUpdate) {
        TextView okButt = (TextView) alertView.findViewById(R.id.ok_button);


        okButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //playSound(R.raw.closesettings);
                setupImgAndTextOfStoreSectons();
                dialog.dismiss();
            }
        });
    }

    private void setupStoreSectionText(String section, Integer resID) {
        TextView textViewToChange = (TextView) mainStoreAlertView.findViewById(resID);

        switch (section) {
            case "color":
                textViewToChange.setText(prefs.getString("shapeTheme", "neon").toUpperCase());
                return;
            case "type":
                textViewToChange.setText(prefs.getString("shapeType", "curved").toUpperCase());
                return;
            case "gamemode":
                textViewToChange.setText(prefs.getString("gamemode", "tutorial").toUpperCase());
                return;
        }

        throw new RuntimeException("UNKNOWN STORE TEXT TO SETUP");

    }

    private void setupStoreSectionPreviewImg(String section, Integer resID) {
        ImageView img = (ImageView) mainStoreAlertView.findViewById(resID);

        android.view.ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
        layoutParams.width = Constants.SCREEN_WIDTH/4;
        layoutParams.height = Constants.SCREEN_WIDTH/4;
        img.setLayoutParams(layoutParams);

        switch (section) {
            case "color":

                Bitmap bm = BitmapFactory.decodeResource(mainMenuActivity.getResources(),
                        Constants.SHAPE_THEMES_ID[Arrays.asList(Constants.SHAPE_THEMES).indexOf(prefs.getString("shapeTheme","neon"))]);
                img.setImageBitmap(bm);
                return;
            case "type":
                String outlineName = prefs.getString("shapeType", "curved") + "outline";
                int resIDBitmap = mainMenuActivity.getResources().getIdentifier(outlineName, "drawable", mainMenuActivity.getPackageName());
                img.setImageResource(resIDBitmap);

                return;
            case "background":
                Bitmap bm2 = BitmapFactory.decodeResource(mainMenuActivity.getResources(),
                        Constants.BACKGROUNDS_ID[Arrays.asList(Constants.BACKGROUNDS).indexOf(prefs.getString("background","triangle-blue"))]);
                img.setImageBitmap(bm2);
                //String str = prefs.getString("background", "bluetriangle");
                //img.setImageBitmap(GameBackground.getBackgroundBitmap(prefs.getString("background","bluetriangle")));
                return;
            case "gamemode":
                Bitmap bm3 = BitmapFactory.decodeResource(mainMenuActivity.getResources(),
                        Constants.GAMEMODES_IDS[Arrays.asList(Constants.GAMEMODES).indexOf(prefs.getString("gamemode","tutorial"))]);
                img.setImageBitmap(bm3);
                return;
            case "multiplier":
                Bitmap bm4 = BitmapFactory.decodeResource(mainMenuActivity.getResources(),
                        Constants.MULTIPLIER_IDS[Arrays.asList(Constants.MULTIPLIERS).indexOf(prefs.getString("multiplier","basic"))]);
                img.setImageBitmap(bm4);
                return;
            case "warningcolorstreakreward":
                Bitmap bm5 = BitmapFactory.decodeResource(mainMenuActivity.getResources(),
                        Constants.WARNING_COLOR_STREAK_REWARDS_IDS[Arrays.asList(Constants.WARNING_COLOR_STREAK_REWARDS).indexOf(prefs.getString("warningcolorstreakreward","more points"))]);
                img.setImageBitmap(bm5);
                return;
        }
        throw new RuntimeException("UNKNOWN STORE IMAGE TO SETUP");


    }

/*
    private void standardOkButtonSetup(View alertView, final AlertDialog dialog) {

        TextView okButt = (TextView) alertView.findViewById(R.id.ok_button);

        okButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //playSound(R.raw.closesettings);
                dialog.dismiss();
            }
        });
    }
    */
/*
    private void dialogFullscreen(AlertDialog dialog) {

        // to remove square edges from custom dialog shape
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Set the dialog to not focusable (makes navigation ignore us adding the window)
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.show();
        //Set the dialog to immersive
        dialog.getWindow().getDecorView().setSystemUiVisibility(
                mainMenuActivity.getWindow().getDecorView().getSystemUiVisibility());

        //Clear the not focusable flag from the window
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }
    */
}
