package com.thezs.fabianzachs.tapattack;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.Game.BackgroundHandlers.Backgrounds.GameBackground;

import org.w3c.dom.Text;

import java.util.Arrays;

/**
 * Created by fabianzachs on 05/06/18.
 */

public class Store {

    private SharedPreferences prefs;
    Activity mainMenuActivity;
    private View mainStoreAlertView;
    final private AlertDialog mainStoreDialog;

    public Store(Activity mainMenuActivity, SharedPreferences prefs) {

        this.prefs = prefs;
        this.mainMenuActivity = mainMenuActivity;
        this.mainStoreAlertView = mainMenuActivity.getLayoutInflater().inflate(R.layout.store_main_menu, null);
        AlertDialog.Builder mainStoredbuilder = new AlertDialog.Builder(mainMenuActivity);
        mainStoredbuilder.setView(mainStoreAlertView);
        this.mainStoreDialog = mainStoredbuilder.create();
        //this.store = new Store(mainStoreAlertView, mainStoreDialog, this);
    }


    public void storeClicked() {
        standardOkButtonSetup(mainStoreAlertView, mainStoreDialog);
        dialogFullscreen(mainStoreDialog);

        setupStoreSectionPreviewImg("color", R.id.shape_color_image);
        setupStoreSectionPreviewImg("type", R.id.shape_type_image);
        setupStoreSectionPreviewImg("background", R.id.background_image);


        setupStoreSectionText("color", R.id.shape_color_set);
        setupStoreSectionText("type", R.id.shape_type_set);
    }

    public void openStoreSection(View view, String section) {

        switch (section) {
            case "color":
                buildDialog(/*view, R.layout.store_item_list,*/ Constants.SHAPE_THEMES, Constants.SHAPE_THEMES_ID, "shapeTheme");
                return;
            case "type":
                buildDialog(Constants.SHAPE_TYPES, Constants.SHAPE_TYPES_IDS, "shapeType");
                return;
            case "background":
                buildDialog(Constants.BACKGROUNDS, Constants.BACKGROUNDS_ID, "background");
                return;
        }

        throw new RuntimeException("UNKNOWN STORE SECTION");
        // todo item click listened
    }

    private void setUpList(String[] names, Integer[] IDs) {

    }

    private void buildDialog(/*View view, Integer resID, */final String[] names, Integer[] IDs, final String prefKey) {

        View alertView = mainMenuActivity.getLayoutInflater().inflate(R.layout.store_item_list, null);
        AlertDialog.Builder dbuilder = new AlertDialog.Builder(mainMenuActivity);
        dbuilder.setView(alertView);
        final AlertDialog dialog = dbuilder.create();
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

    private void okButtonLockInSetup(final View alertView, final AlertDialog dialog, final View viewWithViewToUpdate) {
        TextView okButt = (TextView) alertView.findViewById(R.id.ok_button);


        okButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //playSound(R.raw.closesettings);
                //updateStoreSelected(viewWithViewToUpdate);
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
                String outlineName = prefs.getString("shapeTpe", "curved") + "outline";
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
        }
        throw new RuntimeException("UNKNOWN STORE IMAGE TO SETUP");


    }







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
}
