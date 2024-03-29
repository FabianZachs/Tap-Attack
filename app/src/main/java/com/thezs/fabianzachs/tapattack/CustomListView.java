package com.thezs.fabianzachs.tapattack;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.Database.BasicStoreItem;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;

import java.util.ArrayList;

/**
 * Created by fabianzachs on 04/06/18.
 */

public class CustomListView extends ArrayAdapter<String> {

    //private String[] itemNames;
    //private Integer[] imgIDs;
    private Activity context;
    //private SharedPreferences unlockedPrefs;

    private MyDBHandler dbHandler;
    //private String category;
    private ArrayList<BasicStoreItem> itemsToDisplay;
    private String category;

    // todo needs item name, item drawable id, if locked
    public CustomListView(Activity context, MyDBHandler dbHandler,/*SharedPreferences unlockedPrefs,*/ String category, String[] itemNames/*, Integer[] imgIDs*/) {
        super(context, R.layout.store_item_skeleton, itemNames);

        this.context = context;
        /*
        this.itemNames = itemNames;
        this.imgIDs = imgIDs;
        */
        //this.unlockedPrefs = unlockedPrefs;

        // database version:
        this.dbHandler = dbHandler;
        itemsToDisplay = dbHandler.getBasicStoreItemsFromCategory(category);
        this.category = category;
        // todo databse takes category and returns BasicStoreItems[]


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r=layoutInflater.inflate(R.layout.store_item_skeleton,null,true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) r.getTag();
        }

        //viewHolder.itemNameSection.setText(itemNames[position].toUpperCase());
        //viewHolder.itemImageSection.setImageResource(imgIDs[position]);
        viewHolder.itemNameSection.setText(itemsToDisplay.get(position).get_name().toUpperCase());
        viewHolder.itemImageSection.setImageResource(helper.getResourceId(context, itemsToDisplay.get(position).get_file()));

        if ( dbHandler.isItemUnlocked(itemsToDisplay.get(position).get_name()) != 1)
            viewHolder.itemLockedImage.setImageResource(helper.getResourceId(context, "lockeditem"));


        else
            viewHolder.itemLockedImage.setImageResource(android.R.color.transparent);



        //dbHandler.getWritableDatabase().execSQL("SELECT ");
        //viewHolder.itemNameSection.setText();




// to unlock an item:


        /*
        SharedPreferences.Editor prefsEditior = unlockedPrefs.edit();
        prefsEditior.putBoolean("neon", true);
        prefsEditior.apply();
        */



        /*
        // we only want to display the lock icon if it is locked
        if (!unlockedPrefs.getBoolean( itemNames[position], false) && (position != 0)) {
            Log.d("position", "getView: "+position);
            viewHolder.itemLockedImage.setImageResource(R.drawable.lockeditem); // todo check with prefs if that item is locked/unlocked

        }
        else
            viewHolder.itemLockedImage.setImageResource(android.R.color.transparent);
            */



        return r;




    }

    class ViewHolder {
        TextView itemNameSection;
        ImageView itemImageSection;
        ImageView itemLockedImage;

        ViewHolder(View v) {
            itemNameSection = (TextView) v.findViewById(R.id.item_name);


            itemImageSection = (ImageView) v.findViewById(R.id.item_image);
            android.view.ViewGroup.LayoutParams layoutParams = itemImageSection.getLayoutParams();
            layoutParams.width = Constants.SCREEN_WIDTH/6;
            layoutParams.height = Constants.SCREEN_WIDTH/6;
            itemImageSection.setLayoutParams(layoutParams);

            itemLockedImage = (ImageView) v.findViewById(R.id.item_locked_image);
            itemLockedImage.setLayoutParams(layoutParams);
        }
    }
}
