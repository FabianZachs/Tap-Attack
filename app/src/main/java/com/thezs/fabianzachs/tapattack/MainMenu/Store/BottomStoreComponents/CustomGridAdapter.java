package com.thezs.fabianzachs.tapattack.MainMenu.Store.BottomStoreComponents;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.thezs.fabianzachs.tapattack.Constants;
import com.thezs.fabianzachs.tapattack.Database.BasicStoreItem;
import com.thezs.fabianzachs.tapattack.Database.MyDBHandler;
import com.thezs.fabianzachs.tapattack.Database.StoreItem;
import com.thezs.fabianzachs.tapattack.R;
import com.thezs.fabianzachs.tapattack.helper;

import java.util.ArrayList;

/**
 * Created by fabianzachs on 10/08/18.
 */

public class CustomGridAdapter extends BaseAdapter {

    private int selectedItemPosition;
    private ArrayList<BasicStoreItem> storeItemsToDisplay;
    private Context context;
    private LayoutInflater layoutInflater;
    private MyDBHandler dbHandler;
    private String category;

    public CustomGridAdapter(Context context, MyDBHandler dbHandler, String category, int initiallySelectedItem) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.category = category;
        this.selectedItemPosition = initiallySelectedItem;


        this.dbHandler = dbHandler;
        storeItemsToDisplay = dbHandler.getBasicStoreItemsFromCategory(category);

    }

    public int getIndexOfItemWithName(String name) {
        for (int i = 0; i < storeItemsToDisplay.size(); i++) {
            if (storeItemsToDisplay.get(i).get_name().equals(name))
                return i;
        }
        throw new IllegalArgumentException("index not found of item");
    }

    public void setSelectedItemPosition(int position) {
        Log.d("purchaseunlock", "setSelectedItemPosition: " + position);
        selectedItemPosition = position;
    }

    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }


    @Override
    public int getCount() {
        return storeItemsToDisplay.size();
    }

    @Override
    public BasicStoreItem getItem(int position) {
        return storeItemsToDisplay.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        storeItemsToDisplay = dbHandler.getBasicStoreItemsFromCategory(category);
        if (view== null) {
            view = layoutInflater.inflate(R.layout.grid_item, viewGroup, false );
            ImageView itemImage = (ImageView) view.findViewById(R.id.item_image);
            ViewGroup.LayoutParams layoutParams = itemImage.getLayoutParams();
            layoutParams.width = Constants.SCREEN_WIDTH/6;
            layoutParams.height = Constants.SCREEN_WIDTH/6;
            itemImage.setLayoutParams(layoutParams);
            itemImage.setImageResource(helper.getResourceId(context, storeItemsToDisplay.get(position).get_file()));

            ImageView itemImageHolder = (ImageView) view.findViewById(R.id.item_image_holder);
            ViewGroup.LayoutParams layoutParams2 = itemImageHolder.getLayoutParams();
            layoutParams2.width = layoutParams.width + 20;
            layoutParams2.height = layoutParams.height + 20;
            itemImageHolder.setLayoutParams(layoutParams2);

            View bufferRegion = view.findViewById(R.id.buffer_region);
            ViewGroup.LayoutParams layoutParams3 = bufferRegion.getLayoutParams();
            layoutParams3.width = layoutParams.width + 40;
            layoutParams3.height = layoutParams.height + 40;
            bufferRegion.setLayoutParams(layoutParams3);


        }

        if (storeItemsToDisplay.get(position).get_unlocked() != 1) {
            ImageView lockImage = (ImageView) view.findViewById(R.id.locked_image);
            ViewGroup.LayoutParams layoutParams = lockImage.getLayoutParams();
            layoutParams.width = Constants.SCREEN_WIDTH/6;
            layoutParams.height = Constants.SCREEN_WIDTH/6;
            lockImage.setLayoutParams(layoutParams);
            lockImage.setImageResource(R.drawable.lockeditem);
        }
        else {
            ImageView lockImage = (ImageView) view.findViewById(R.id.locked_image);
            ViewGroup.LayoutParams layoutParams = lockImage.getLayoutParams();
            layoutParams.width = Constants.SCREEN_WIDTH/6;
            layoutParams.height = Constants.SCREEN_WIDTH/6;
            lockImage.setLayoutParams(layoutParams);
            lockImage.setImageResource(android.R.color.transparent);
        }





        // todo maybe refactor to only have one holder per item section and just change drawable color of that holder
        ImageView itemImageHolder = (ImageView) view.findViewById(R.id.item_image_holder);

        switch (category) {
            case Constants.GAME_MODE_TAG:
                itemImageHolder.setImageDrawable(getItemHolder(context.getResources().getDrawable(R.drawable.holder_gamemode), position));
                break;
            case Constants.BACKGROUND_TAG:
                itemImageHolder.setImageDrawable(getItemHolder(context.getResources().getDrawable(R.drawable.holder_background), position));
                break;

            case Constants.SHAPE_TYPE_TAG:
            case Constants.SHAPE_THEME_TAG:
                itemImageHolder.setImageDrawable(getItemHolder(context.getResources().getDrawable(R.drawable.holder_theme_item), position));
                break;
        }

        if (position == selectedItemPosition)
            startAnimationAtSelectedPosition((RelativeLayout) view.findViewById(R.id.grid_item_section));
        else
            stopAnimation((RelativeLayout) view.findViewById(R.id.grid_item_section));

        return view;
    }

    private Drawable getItemHolder(Drawable holderDrawable, int position) {
        if (position != selectedItemPosition)
            holderDrawable.setColorFilter(context.getResources().getColor(R.color.item_holder), PorterDuff.Mode.SRC);
        else if (storeItemsToDisplay.get(position).get_unlocked() == 1)
            holderDrawable.setColorFilter(context.getResources().getColor(R.color.selected_item_holder), PorterDuff.Mode.SRC);
        else
            holderDrawable.setColorFilter(context.getResources().getColor(R.color.selected_locked_item_holder), PorterDuff.Mode.SRC);

        return holderDrawable;
    }

    private void startAnimationAtSelectedPosition(RelativeLayout itemSection) {

        Animation selectedItemAnimation = AnimationUtils.loadAnimation(context, R.anim.selected_item);
        itemSection.startAnimation(selectedItemAnimation);
    }

    private void stopAnimation(RelativeLayout itemSection) {
        itemSection.clearAnimation();
    }
}
