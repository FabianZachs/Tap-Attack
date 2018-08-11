package com.thezs.fabianzachs.tapattack.MainMenu.Store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

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

public class CustomAdapter extends BaseAdapter {

    private ArrayList<BasicStoreItem> storeItemsToDisplay;
    private Context context;
    private LayoutInflater layoutInflater;
    private MyDBHandler dbHandler;
    private String category;

    public CustomAdapter(Context context, MyDBHandler dbHandler, String category) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.category = category;


        this.dbHandler = dbHandler;
        storeItemsToDisplay = dbHandler.getBasicStoreItemsFromCategory(category);

    }

    @Override
    public int getCount() {
        return storeItemsToDisplay.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view== null) {
            view = layoutInflater.inflate(R.layout.grid_item, viewGroup, false );

            ImageView itemImage = (ImageView) view.findViewById(R.id.item_image);
            android.view.ViewGroup.LayoutParams layoutParams = itemImage.getLayoutParams();
            layoutParams.width = Constants.SCREEN_WIDTH/6;
            layoutParams.height = Constants.SCREEN_WIDTH/6;
            itemImage.setLayoutParams(layoutParams);
            itemImage.setImageResource(helper.getResourceId(context, storeItemsToDisplay.get(position).get_file()));


            // todo use category to find the right holder shape
            ImageView itemImageHolder = (ImageView) view.findViewById(R.id.item_image_holder);
            android.view.ViewGroup.LayoutParams layoutParams2 = itemImageHolder.getLayoutParams();
            layoutParams2.width = layoutParams.width + 20;
            layoutParams2.height = layoutParams.height + 20;
            itemImageHolder.setLayoutParams(layoutParams2);

        }

        return view;
    }
}
