package com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;

public class PaidUnlockAdapter extends ArrayAdapter<PaidUnlockItem> {


    private Context context;
    private int resource;
    private String type;

    public PaidUnlockAdapter(String type, @NonNull Context context, int resource, ArrayList<PaidUnlockItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.type = type;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int price = getItem(position).getPrice();
        int quantity = getItem(position).getQuantity();
        int drawableId = getItem(position).getDrawableId();

        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            r= inflater.inflate(resource,parent,false);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) r.getTag();
        }

        switch (type) {
            case "powerups":
                viewHolder.priceText.setText(context.getString(R.string.price,price));
                viewHolder.itemImage.setImageResource(drawableId);
                viewHolder.quantityText.setText(context.getString(R.string.powerupsQuantity,quantity));
                viewHolder.itemHolder.setBackgroundResource(R.drawable.powerupitemholder);
                break;
            case "points":
                viewHolder.priceText.setText(context.getString(R.string.price,price));
                viewHolder.itemImage.setImageResource(drawableId);
                viewHolder.quantityText.setText(context.getString(R.string.pointsQuantity,quantity));
                viewHolder.itemHolder.setBackgroundResource(R.drawable.pointsitemholder);
                break;
            default: throw new IllegalArgumentException("unknown paid unlock section" + type);
        }


        return r;


    }

    private class ViewHolder {
        TextView quantityText;
        ImageView itemImage;
        TextView priceText;
        LinearLayout itemHolder;

        ViewHolder(View view) {
            quantityText = view.findViewById(R.id.item_quantity);
            itemImage = view.findViewById(R.id.item_image);
            priceText = view.findViewById(R.id.item_price);
            itemHolder = view.findViewById(R.id.main_layout);
        }
    }
}
