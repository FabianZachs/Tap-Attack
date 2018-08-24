package com.thezs.fabianzachs.tapattack.MainMenu.Menu.PaidUnlockSection;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thezs.fabianzachs.tapattack.ButtonOnTouchListener;
import com.thezs.fabianzachs.tapattack.R;

import java.util.ArrayList;

public class PaidUnlockAdapter extends ArrayAdapter<PaidUnlockItem> {


    private Activity activity;
    private int resource;
    private String type;
    private PaidUnlockAdapterListener listener;

    public PaidUnlockAdapter(String type, @NonNull Activity activity, int resource, ArrayList<PaidUnlockItem> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.resource = resource;
        this.type = type;
    }

    public interface PaidUnlockAdapterListener {
        void purchaseComplete();
    }

    public void setListener(PaidUnlockAdapterListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int price = getItem(position).getPrice();
        final int quantity = getItem(position).getQuantity();
        int drawableId = getItem(position).getDrawableId();

        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            r= inflater.inflate(resource,parent,false);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) r.getTag();
        }

        switch (type) {
            case "powerups":
                viewHolder.priceText.setText(activity.getString(R.string.price,price));
                viewHolder.itemImage.setImageResource(drawableId);
                viewHolder.quantityText.setText(activity.getString(R.string.powerupsQuantity,quantity));
                viewHolder.itemHolder.setBackgroundResource(R.drawable.powerupitemholder);
                break;
            case "points":
                viewHolder.priceText.setText(activity.getString(R.string.price,price));
                viewHolder.itemImage.setImageResource(drawableId);
                viewHolder.quantityText.setText(activity.getString(R.string.pointsQuantity,quantity/1000));
                viewHolder.itemHolder.setBackgroundResource(R.drawable.pointsitemholder);
                break;
            default: throw new IllegalArgumentException("unknown paid unlock section" + type);
        }

        r.setOnTouchListener(new ButtonOnTouchListener(activity, r, new ButtonOnTouchListener.ButtonExecuteListener() {
            @Override
            public void buttonAction() {
                SharedPreferences preferences = activity.getSharedPreferences("playerInfo", Context.MODE_PRIVATE);
                preferences.edit().putInt(type, preferences.getInt(type,0) + quantity).apply();
                listener.purchaseComplete();

            }
        }));


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
