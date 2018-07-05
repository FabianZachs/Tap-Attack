package com.thezs.fabianzachs.tapattack;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by fabianzachs on 04/06/18.
 */

public class CustomListView extends ArrayAdapter<String> {

    private String[] itemNames;
    private Integer[] imgIDs;
    private Activity context;

    public CustomListView(Activity context, String[] itemNames, Integer[] imgIDs) {
        super(context, R.layout.store_item_skeleton, itemNames);

        this.context = context;
        this.itemNames = itemNames;
        this.imgIDs = imgIDs;

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
        viewHolder.itemNameSection.setText(itemNames[position].toUpperCase());
        viewHolder.itemImageSection.setImageResource(imgIDs[position]);
        viewHolder.itemLockedImage.setImageResource(R.drawable.lockeditem); // todo check with prefs if that item is locked/unlocked
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
